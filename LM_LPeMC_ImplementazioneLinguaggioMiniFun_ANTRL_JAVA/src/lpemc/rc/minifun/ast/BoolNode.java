package lpemc.rc.minifun.ast;

import lpemc.rc.minifun.MiniFunLexer;
import lpemc.rc.minifun.TypeSystem;
import lpemc.rc.minifun.TypeSystem.TMappings;

public class BoolNode extends Node {
	protected boolean val;
	
	public BoolNode(boolean val){
		this.val = val;
	}
	
	@Override
	public String getStringRepr() {
		return "BOOL["+val+"]";
	}
	
	@Override public String typeCheckLogic(TMappings mappings){
		return TypeSystem.Tbool;
	}

	@Override
	public String genCode() {
		if(val) return "push 1 /* BOOL */\n";
		return "push 0 /* BOOL */\n";
	}	

}
