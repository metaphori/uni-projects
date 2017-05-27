package lpemc.rc.minifun.ast;

import lpemc.rc.minifun.MiniFunLexer;
import lpemc.rc.minifun.TypeSystem;
import lpemc.rc.minifun.TypeSystem.TMappings;

public class BoolTypeNode extends ConcreteTypeNode {

	@Override
	public String getStringRepr() {
		return "bool";
	}
	
	@Override
	public String typeCheckLogic(TMappings mappings) {
		return TypeSystem.Tbool;
	}

	@Override
	public String genCode() {
		return "";
	}	
	
}
