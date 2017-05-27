package lpemc.rc.minifun.ast;

import java.util.HashMap;

import lpemc.rc.minifun.TypeSystem;
import lpemc.rc.minifun.TypeSystem.TMappings;
import lpemc.rc.minifun.Utils;

public class ANDNode extends Node {

	protected Node left;
	protected Node right;

	public ANDNode(Node left, Node right){
		this.left = left;
		this.right = right;
	}

	@Override public String getStringRepr(){
		return "AND["+left.getStringRepr()+", "+right.getStringRepr()+"]";
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
		String lblFalse = Utils.genLabel("false");
		String lblEnd = Utils.genLabel("end");
		
		return  /* SHORT-CIRCUIT LOGICAL AND: first look at 'left' */
				left.genCode() + 
				"push 1\n" +
				"and\n" +
				"push 0\n" +
				"beq " + lblFalse + "\n" +
				
				// 'left' is True, check for 'right'
				right.genCode() + 
				"push 1\n" +
				"and\n" +
				"b " + lblEnd + "\n" +
				
				lblFalse+":\n"+
				"push 0\n"+
				
				lblEnd+":\n";
	}

}
