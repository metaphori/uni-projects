package lpemc.rc.minifun.ast;

import java.util.HashMap;

import lpemc.rc.minifun.TypeSystem;
import lpemc.rc.minifun.TypeSystem.TMappings;

/**
 * Logical NOT
 * It takes a boolean value and makes its complement.
 *
 */
public class NOTNode extends Node {

	protected Node exp;

	public NOTNode(Node exp){
		this.exp = exp;
	}
	
	@Override public String getStringRepr(){
		return "NOT["+exp.getStringRepr()+"]";
	}

	@Override public String typeCheckLogic(TMappings mappings){	
		if(exp.typeCheck(mappings).equals(TypeSystem.Tbool))
			return TypeSystem.Tbool;
		return TypeSystem.Terror;
	}

	/**
	 * Assumptions:
	 * 	- TRUE is represented as '1' (i.e. ...0001)
	 *  - FALSE is represented as '0' (i.e. ...0000)
	 */
	@Override
	public String genCode() {
		return  exp.genCode() +	// e.g.	110111 (positive)		00100 (negative)
				"push 1\n"+ 	// 		000001 					00001
				"xor\n"+		// 		110110					00101
				"push 1\n"+		//		000001					00001
				"and\n";		//		000000 (negative)		00001 (positive)
	}

}
