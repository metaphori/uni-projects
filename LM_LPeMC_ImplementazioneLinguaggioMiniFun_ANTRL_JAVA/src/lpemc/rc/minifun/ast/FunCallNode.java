package lpemc.rc.minifun.ast;

import java.util.ArrayList;

import lpemc.rc.minifun.TypeSystem;
import lpemc.rc.minifun.TypeSystem.TMappings;
import lpemc.rc.minifun.Utils;


public class FunCallNode extends Node {
	protected IdentifierDeclNode decl;
	protected FunRefNode funRef;
	protected int nesting_steps;
	protected ArrayList<Node> argExps;
	
	public FunCallNode(FunRefNode funRef, int nesting_steps, ArrayList<Node> argExps){
		this.funRef = funRef;
		this.decl = funRef.entry.getIdentifierNode();
		this.nesting_steps = nesting_steps;
		this.argExps = argExps;
	}

	@Override
	public String getStringRepr() {
		String actualArgsRepr = "{";
		for(Node e : argExps){
			actualArgsRepr += e.getStringRepr() + "; ";
		}
		actualArgsRepr += "}";
		return "FUN_CALL["+funRef.getStringRepr()+", "+actualArgsRepr+"]";
	}

	@Override
	public String typeCheckLogic(TMappings mappings) {
		
		FunTypeNode ftn = (FunTypeNode)decl.getType();
		
		TMappings updatedMappings = TypeSystem.GenMappings(mappings, funRef.theseMappings);
		
		ArrayList<TypeNode> formalArgs = ftn.getArgTypes();
		
		if(formalArgs.size()!=argExps.size()){
			Utils.log("ERROR: Number of actual args ("+argExps.size()+") in " + decl.getName() + 
					" are does not match number of formal args ("+
					formalArgs.size()+") for function " + decl.getName());
			return TypeSystem.Terror;
		}
		
		for(int i=0; i<formalArgs.size(); i++){
			if(!TypeSystem.isCompatible(argExps.get(i).typeCheck(updatedMappings), formalArgs.get(i).typeCheck(updatedMappings))){
				Utils.log("ERROR: Actual arg (n="+(i+1)+") of " + decl.getName() + 
						" is not compatible with formal args: '" + argExps.get(i).typeCheck(updatedMappings) + 
						"' vs '" + formalArgs.get(i).typeCheck(updatedMappings)+"'");
				return TypeSystem.Terror;
			}
		}
		
		return TypeSystem.GetFunctionResultType(decl.getType().typeCheck(updatedMappings));
	}

	@Override
	public String genCode() {
		// code to put params on stack
		String parCode = "";
		for(int k=argExps.size()-1; k>=0; k--){
			Node arg = argExps.get(k);
			parCode += (argExps.get(k)).genCode();
		}
			
		IdentifierDeclNode callee = decl;
		
		// code to follow the chain of access links
		String lookupAccessLink = "lfp\n";
		for(int k=0; k<this.nesting_steps; k++)
			lookupAccessLink += "lw\n";
		
		// get function reference's offset so to be able to find its address and closure
		int offset = funRef.entry.getOffset();
		
		// caller sets first part of AR(Activation Record)
		// Remember		- access_link = FP of access link of AR of callee's syntactic father
		//				- control_link = caller's FP
		return "/******** '"+decl.getName()+"' function call ********/\n" +
		"lfp\n" +
		"/* actual parameters */\n" +
		parCode +
		"/* lookup access link */\n"	+
		lookupAccessLink +
		"push " + (offset+1) + "\n" + // la chiusura è sempre messa sopra l'indirizzo della funz nello stack
		"sub\n"+
		"lw\n" +				
		// now we need to obtain the function address to its code by following the access links
		"/* lookup fun '"+callee.getName()+"' address */\n"+
		lookupAccessLink + 
		// from there, move to offset and load the address so that we can jump to it
		"push " + offset + "\n" +
		"sub\n"+
		"lw\n"+
		// now jump to invoked function's code
		"js\n";
		
	}

}
