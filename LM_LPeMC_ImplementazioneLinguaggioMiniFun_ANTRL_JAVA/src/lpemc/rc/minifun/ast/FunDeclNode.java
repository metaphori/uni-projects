package lpemc.rc.minifun.ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import lpemc.rc.minifun.STEntry;
import lpemc.rc.minifun.TypeSystem;
import lpemc.rc.minifun.TypeSystem.TMappings;
import lpemc.rc.minifun.Utils;

/**
 * This node represents the declaration of a function
 * 	- it may include type parameters
 * 
 * @author Roberto Casadei
 *
 */
public class FunDeclNode extends IdentifierDeclNode {
	protected Node exp;
	protected ArrayList<FunArgNode> args;
	protected ArrayList<TypeNode> argTypes = new ArrayList<TypeNode>();
	protected ArrayList<IdentifierDeclNode> locals = new ArrayList<IdentifierDeclNode>();
	protected TypeNode retType;
	
	protected ArrayList<GenericTypeNode> genTypes = null;
	protected String genTypesRepr = "";
	protected HashMap<ArrayList<TypeNode>,String> instantiations = new HashMap<ArrayList<TypeNode>,String>();
	protected ArrayList<TypeNode> lastInstantiation = null;
	
	public FunDeclNode(String id, TypeNode retType){
		super(id, null);
		this.retType = retType;
	}
	
	public void setGenericTypes(ArrayList<GenericTypeNode> genTypes){
		if(genTypes.size()>0){
			this.genTypes = genTypes;
			this.generic = true;
		}
	}
	
	/**
	 * For a FunDecl with generic types <A,..,Z>
	 * 		and an instantiation with concrete types <Ta,...,Tz>
	 * returns a hashmap with the mappings
	 * 		A ---> Ta
	 * 		.........
	 * 		Z ---> Tz
	 */
	public TMappings createTypeMappings(ArrayList<TypeNode> concreteTypes){
		if(genTypes==null && concreteTypes!=null && concreteTypes.size()>0){
			Utils.log("ERROR: Providing type instantiations for non-generic function declaration");
			return null;
		}
		
		TMappings mappings = new TMappings();
		if(genTypes.size()!=concreteTypes.size()){
			Utils.log("ERROR: The number of generic types and concrete types does not match!");
			return null;
		}
		
		for(int i=0; i<genTypes.size(); i++){
			if(concreteTypes.get(i) instanceof GenericTypeNode)
				mappings.put(genTypes.get(i), null);
			else
				mappings.put(genTypes.get(i), (ConcreteTypeNode)concreteTypes.get(i));
		}

		return mappings;
	}

	public void setParams(ArrayList<FunArgNode> args){
		this.args = args;
		
		argTypes.clear();
		for(FunArgNode arg : args){
			argTypes.add(arg.getType());
		}
		
		this.type = new FunTypeNode(argTypes,  retType);
	}
	
	public void setBody(Node exp){
		this.exp = exp;
		if(exp instanceof LetBlockNode){
			LetBlockNode let = (LetBlockNode)exp;
			addLocals( let.getDeclarations() );
		}
	}
	
	public int addLocals(ArrayList<IdentifierDeclNode> decls){
		int adds = 0;
		for(IdentifierDeclNode idn : decls){
			int i = locals.indexOf(idn);
			if(i!=-1){
				locals.remove(i);
				locals.add(i,idn);
				adds++;
			} else{
				locals.add(idn);
				adds++;
			}
		}
		return adds;
	}
	
	@Override
	public String getStringRepr() {
		String argsrepr = "{ ";
		for(FunArgNode arg : args)
			argsrepr += arg.getStringRepr()+"; ";
		argsrepr += "}";

		return "FUN_DECL"+TypeSystem.TypesInBracketsRepr(genTypes)+"["+id+", "+type.getStringRepr()+", "+argsrepr+", "+exp.getStringRepr()+"]";
	}

	@Override
	public String typeCheckLogic(TMappings mappings) {
		String ftype = null;
		String fretType = null;
		String expT = null;
		
		if(!generic || mappings==null){
			ftype = TypeSystem.GetFunctionTypeRepr(argTypes, retType, null);
			fretType = TypeSystem.GetFunctionResultType(ftype);
			
			expT = exp.typeCheck(null); 
		} else{
			ArrayList<TypeNode> concreteArgTypes = new ArrayList<TypeNode>();
			for(TypeNode tn : argTypes){
				ConcreteTypeNode ctn = mappings.get(tn);
				if(ctn!=null){
					concreteArgTypes.add(ctn);
				} else{
					concreteArgTypes.add((ConcreteTypeNode)tn);
				}
			}
			
			TypeNode concreteRetType = mappings.get(retType);
			if(concreteRetType==null)
				concreteRetType = (ConcreteTypeNode)retType;
			
			ftype = TypeSystem.GetFunctionTypeRepr(concreteArgTypes, concreteRetType, mappings);
			fretType = TypeSystem.GetFunctionResultType(ftype);		
			expT = exp.typeCheck(mappings); 
		}
		
		
		if(!TypeSystem.isCompatible(expT, fretType)){
			Utils.log("The type of the body " + expT + " does NOT conform with the fun's return type " + fretType);
			return TypeSystem.Terror;
		}

		return ftype;		
	}
	
	@Override
	public String genCode() {
		//Utils.log(">>> generating code for " + getName() + " (fun decl)");
		String fnLabel = Utils.genLabel("FUNCTION"+this.id);
		
		// need to clean the stack by popping the args
		String popArgs = "";
		for(int i=0; i<this.argTypes.size(); i++){
			popArgs += "pop\n";
			// for arguments that have function type we need to do two pops
			//	(as function references include address to code and AL)
			if(argTypes.get(i) instanceof FunTypeNode){
				popArgs += "pop\n";
			}
		}

		// need to clean the stack by popping local vars
		String popLocals = "";
		if(this.locals!=null){
			for(int i=0; i<this.locals.size(); i++){
				popLocals += "pop\n";
				IdentifierDeclNode nd = this.locals.get(i);
				if(nd.getType() instanceof FunTypeNode){
					// if it is of a function type (i.e. the local is a fun decl or a fun ref)
					//		let's make an additional pop
					popLocals += "pop\n";
				}
			}
		}
		
		Utils.addFunctionCode(
			fnLabel + ":\n" +
			"cfp\n"+ /* copia valore SP dentro FP (i.e. setta il frame pointer a questo punto) */
			"lra\n"+ /* put return address on stack */
			"/* function body */\n" +
			// if the body is a LET..IN block, it will first generate the code for local declarations/vars 
			//on the stack and finally it will generate the code for its expression
			this.exp.genCode()+
			// se il valore ritornato è una funzione, allora bisogna ritornare 2 valori: indirizzo e chiusura
			//  i quali sono il risultato di exp.genCode();
			// l'indirizzo della chiusura è memorizzato nel registro 'temp' 
			//  e dopo le varie pop è posizionato dopo il return value (ivi collocato via 'lrv')
			(this.retType instanceof FunTypeNode ? "stemp\n" : "") +
			
			"srv\n"+
			"/* pop locals */\n" +
			popLocals+
			"sra\n"+
			"/* pop access link */\n" +
			"pop\n"+ // pops access link
			"/* pop args */\n" +
			popArgs+
			"sfp\n"+ // move control link from stack to FP (i.e. restore old FP)
			"lrv\n"+
			(retType instanceof FunTypeNode ? "ltemp\n" : "") +
			"lra\n"+
			"js\n"
		);
	
		return "push " + fnLabel + "\n" +
			   "lfp\n"; // LFP allows to save current FP (i.e. the closure for this function)		
	}
	
	public ArrayList<FunArgNode> argList(){
		return this.args;
	}

	/*
	public void copyToLocals(ArrayList<HashMap<String, STEntry>> symbolTable,
			int nesting_level, Stack<Integer> voffs) {
		Utils.log("COPY TO LOCALS to " + id);
		HashMap<String,STEntry> currTable = symbolTable.get(nesting_level);
		
		// scorro la symbol table per a partire dal top-level poiché addLocals() sovrascrive le dichiarazioni
		for(int k=0; k<=nesting_level-1; k++){
			HashMap<String,STEntry> map = symbolTable.get(k);
			ArrayList<IdentifierDeclNode> identifiers = new ArrayList<IdentifierDeclNode>();
			for(String vid : map.keySet()){
				Utils.log("\tAdding identifier " + vid + " to curr table");
				currTable.put(vid, map.get(vid));
				identifiers.add(map.get(vid).getIdentifierNode());
			}
			addLocals(identifiers);
		}
	}
	*/
	
}
