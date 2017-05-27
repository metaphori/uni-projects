package lpemc.rc.minifun.ast;

import java.util.HashMap;

import lpemc.rc.minifun.MiniFunLexer;
import lpemc.rc.minifun.STEntry;
import lpemc.rc.minifun.TypeSystem.TMappings;
import lpemc.rc.minifun.Utils;

public class VarNode extends Node {
	protected STEntry entry;
	protected int nesting_steps;
	
	public VarNode(STEntry entry, int nesting_steps){
		this.entry = entry;
		this.nesting_steps = nesting_steps;
	}

	@Override
	public String getStringRepr() {
		return "VAR("+entry.getIdentifierNode().getName()+")";
	}
	
	@Override
	public String typeCheckLogic(TMappings mappings) {
		return entry.getIdentifierNode().typeCheck(mappings);
	}

	@Override
	public String genCode() {
		int offset = entry.getOffset();

		// code to follow the chain of access links
		String lookupAccessLink = "";
		for(int k=0; k<this.nesting_steps; k++)
			lookupAccessLink += "lw\n";
		
		/* 
		 * Retrieve var at position (correct scope FP)-offset on the stack
		 * NOTE: 	- the offset is negative if the var is an argument
		 * 			- the offset is positive if the var is a local var
		 */
		return	"/* retrieve var " + entry.getIdentifierNode().getName() + " (nesting_steps="+nesting_steps+")*/\n"+ 
				"lfp\n" +
				lookupAccessLink +
				"push " + offset + "\n" +
				"sub\n" +
				"lw\n/* end of var retrieval */\n";
				
	}

}
