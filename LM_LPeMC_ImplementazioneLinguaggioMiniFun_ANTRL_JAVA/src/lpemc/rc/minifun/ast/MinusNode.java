package lpemc.rc.minifun.ast;

import java.util.HashMap;

import lpemc.rc.minifun.TypeSystem;
import lpemc.rc.minifun.TypeSystem.TMappings;

public class MinusNode extends Node {
	protected Node left;
	protected Node right;

	public MinusNode(Node left, Node right){
		this.left = left;
		this.right = right;
	}

	@Override public String getStringRepr(){
		return "MINUS["+left.getStringRepr()+", "+right.getStringRepr()+"]";
	}

	@Override public String typeCheckLogic(TMappings mappings){
		String left = this.left.typeCheck(mappings);
		String right = this.right.typeCheck(mappings);		
		
		if(left.equals(TypeSystem.Tint) && TypeSystem.areCompatible(left, right))
			return TypeSystem.Tint;
		return TypeSystem.Terror;
	}

	@Override
	public String genCode() {
		return  left.genCode() + 
				right.genCode() + 
				"sub\n";
	}
}
