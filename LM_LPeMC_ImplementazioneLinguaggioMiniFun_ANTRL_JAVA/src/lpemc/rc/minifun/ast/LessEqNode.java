package lpemc.rc.minifun.ast;

import java.util.HashMap;

import lpemc.rc.minifun.TypeSystem;
import lpemc.rc.minifun.TypeSystem.TMappings;
import lpemc.rc.minifun.Utils;

public class LessEqNode extends Node {

	protected Node left;
	protected Node right;
	
	private static int count = 1;

	public LessEqNode(Node left, Node right){
		this.left = left;
		this.right = right;
	}

	@Override public String getStringRepr(){
		return "LESS_EQ["+left.getStringRepr()+", "+right.getStringRepr()+"]";
	}
	
	@Override public String typeCheckLogic(TMappings mappings){
		String left = this.left.typeCheck(mappings);
		String right = this.right.typeCheck(mappings);		
		
		if(TypeSystem.areCompatible(left,right))
			return TypeSystem.Tbool;
		return TypeSystem.Terror;
	}

	@Override
	public String genCode() {
		String lblTrue = Utils.genLabel("true");
		String lblEnd = Utils.genLabel("end");
		
		return  left.genCode() +
				right.genCode() +
				"bless " + lblTrue +"\n" +
				"push " + TypeSystem.FALSE_REPR + "\n" +
				"b " + lblEnd +"\n" +
				lblTrue+":\n" +
				"push " + TypeSystem.TRUE_REPR + "\n" +
				lblEnd+":\n";
	}

}
