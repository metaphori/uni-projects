package lpemc.rc.minifun.ast;

import java.util.HashMap;

import lpemc.rc.minifun.TypeSystem;
import lpemc.rc.minifun.TypeSystem.TMappings;
import lpemc.rc.minifun.Utils;

public class ORNode extends Node {

	protected Node left;
	protected Node right;

	public ORNode(Node left, Node right){
		this.left = left;
		this.right = right;
	}
	
	@Override public String getStringRepr(){
		return "OR["+left.getStringRepr()+", "+right.getStringRepr()+"]";
	}

	@Override public String typeCheckLogic(TMappings mappings){
		String left = this.left.typeCheck(mappings);
		String right = this.right.typeCheck(mappings);
		
		if(TypeSystem.areCompatible(left, right))
			return TypeSystem.Tbool;
		return TypeSystem.Terror;
	}

	@Override
	public String genCode() {
		String lblTrue = Utils.genLabel("true");
		String lblEnd = Utils.genLabel("end");
		// SHORT CIRCUIT
		return  "/* LOGICAL OR (short-circuit) */" +
				// now we AND-bitmask 'left' with 1 to see if it's True (which represented as 1)
				left.genCode() + 
				"push 1\n" +
				"and\n" +
				
				// if true, jump to label
				"push 1\n"+
				"beq " + lblTrue + "\n" +
				
				// if not true, AND-bitmask 'right' with 1
				right.genCode() + 
				"push 1\n" +
				"and\n" +
				
				// the result of that operation is True (1) or False (0)
				// so we can jump to the end
				"b " + lblEnd + "\n"+
				
				// if 'left' was true, we need to add True (1) onto stack
				//  as 'beq' made two pops
				lblTrue+":\n"+
				"push 1\n"+
				
				lblEnd+":\n";
	}
	
	
}
