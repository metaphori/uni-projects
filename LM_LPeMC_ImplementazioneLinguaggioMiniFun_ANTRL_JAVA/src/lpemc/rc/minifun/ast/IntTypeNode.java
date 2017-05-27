package lpemc.rc.minifun.ast;

import lpemc.rc.minifun.MiniFunLexer;
import lpemc.rc.minifun.TypeSystem;
import lpemc.rc.minifun.TypeSystem.TMappings;

public class IntTypeNode extends ConcreteTypeNode {

	@Override
	public String getStringRepr() {
		return "int";
	}

	@Override
	public String typeCheckLogic(TMappings mappings) {
		return TypeSystem.Tint;
	}

	@Override
	public String genCode() {
		return "";
	}

}
