package lpemc.rc.minifun.ast;

import lpemc.rc.minifun.STEntry;
import lpemc.rc.minifun.TypeSystem;
import lpemc.rc.minifun.TypeSystem.TMappings;
import lpemc.rc.minifun.Utils;

/**
 * It represents a function reference.
 * 	The relative symbol table entry can be:
 * 		- a function declaration
 * 		- a formal parameter declaration (because we can pass functions to other functions)
 */
public class FunRefNode extends Node {

	protected STEntry entry;
	protected int nesting_steps;
	protected TMappings theseMappings = null;
	
	protected java.util.ArrayList<TypeNode> typesInBrackets = null;
	

	public FunRefNode(STEntry entry, int nesting_steps){
		this.entry = entry;
		this.nesting_steps = nesting_steps;
	}
	
	/* For a function reference such as
	 * 		myFunRef<T1,...,Tn>
	 * we save the list T1,...,Tn
	 */
	public void setTypeParams(java.util.ArrayList<TypeNode> typesInBrackets){
		this.typesInBrackets = typesInBrackets;
		
		theseMappings = ((FunDeclNode)entry.getIdentifierNode()).createTypeMappings(typesInBrackets);
	}

	@Override
	public String getStringRepr() {
		return "FUN_REF"+TypeSystem.TypesInBracketsRepr(typesInBrackets)+"[to: " + entry.getIdentifierNode().getName() +"]";
	}
	
	@Override
	public String typeCheckLogic(TMappings mappings) {
		TMappings newMappings = TypeSystem.GenMappings(mappings, this.theseMappings);
		
		String decltype = entry.getIdentifierNode().typeCheck(newMappings);		
		
		return decltype;
	}
	
	@Override
	public String genCode() {
		int offset = entry.getOffset();

		// code to follow the chain of access links
		String lookupAccessLink = "lfp\n";
		for(int k=0; k<this.nesting_steps; k++)
			lookupAccessLink += "lw\n";

		/* if it's local to a function, 
		 * we need to consider and skip the first cell after the AR pointer, which is the return address */
		String inside_function = entry.getProperty("local_to_function");
		int inf = inside_function==null ? 0 : 1;
		offset+=inf;

		/* 
		 * Retrieve function ref at position (correct scope FP)-offset on the stack
		 *   and also its closure (FP of its declaration)
		 */
		return	"/* retrieve function ref " + entry.getIdentifierNode().getName() + "(nesting_steps="+nesting_steps+")*/\n"+ 
		lookupAccessLink +
		"push " + offset + "\n" +
		"sub\n" +
		"lw\n/* end of function ref retrieval, now retrieve the closure */\n" +
		lookupAccessLink + 
		"push " + (offset+1) + "\n" +
		"sub\n" +
		"lw\n/* end of function closure retrieval */\n";
				
	}

}
