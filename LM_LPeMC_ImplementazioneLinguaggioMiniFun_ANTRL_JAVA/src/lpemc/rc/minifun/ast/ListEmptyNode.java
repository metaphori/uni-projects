package lpemc.rc.minifun.ast;

import lpemc.rc.minifun.TypeSystem;
import lpemc.rc.minifun.TypeSystem.TMappings;
import lpemc.rc.minifun.Utils;

public class ListEmptyNode extends Node {

	@Override
	public String getStringRepr() {
		return "EMPTY";
	}

	@Override
	public String typeCheckLogic(TMappings mappings) {
		return TypeSystem.TlistOfAny;
	}

	@Override
	public String genCode() {
		return "/* EMPTY LIST */\npush " + Utils.NULL_POINTER + "\n";
	}

}
