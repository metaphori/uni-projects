package lpemc.rc.minifun.ast;

import java.util.HashMap;

import lpemc.rc.minifun.MiniFunLexer;
import lpemc.rc.minifun.TypeSystem;
import lpemc.rc.minifun.TypeSystem.TMappings;
import lpemc.rc.minifun.Utils;

public class ListFirstNode extends Node {
	protected Node exp;
	
	public ListFirstNode(Node exp){
		this.exp = exp;
	}

	@Override
	public String getStringRepr() {
		return "LFIRST["+exp.getStringRepr()+"]";
	}
	
	@Override public String typeCheckLogic(TMappings mappings){
		String expT = exp.typeCheck(mappings);
		
		if(TypeSystem.ListOfListsLevels(expT)>=1){
			return TypeSystem.ListElemType(expT);
		}
		if(expT.equals(TypeSystem.TlistOfBool))
			return TypeSystem.Tbool;
		if(expT.equals(TypeSystem.TlistOfInt))
			return TypeSystem.Tint;
		
		return TypeSystem.Terror;
	}

	@Override
	public String genCode() {
		return "/* first(lst) */\n"+exp.genCode() + // puntatore alla prima coppia
			"lw\n";
	}	

}
