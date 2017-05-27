package lpemc.rc.minifun.ast;

import java.util.HashMap;

import lpemc.rc.minifun.MiniFunLexer;
import lpemc.rc.minifun.TypeSystem;
import lpemc.rc.minifun.TypeSystem.TMappings;
import lpemc.rc.minifun.Utils;

public class ListRestNode extends Node {
	protected Node exp;
	
	public ListRestNode(Node exp){
		this.exp = exp;
	}

	@Override
	public String getStringRepr() {
		return "LREST["+exp.getStringRepr()+"]";
	}
	
	@Override public String typeCheckLogic(TMappings mappings){

		if(TypeSystem.areCompatible(TypeSystem.TlistOfAny, exp.typeCheck(mappings)))
			return exp.typeCheck(mappings);
		return TypeSystem.Terror;
	}

	@Override
	public String genCode() {
		return 
			"/* rest(lst) */\npush 1\n" +
			exp.genCode() + // puntatore alla prima coppia
			"add\n" +
			"lw\n";
	}	

}
