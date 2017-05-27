package lpemc.rc.minifun.ast;

import java.util.HashMap;

import lpemc.rc.minifun.TypeSystem;
import lpemc.rc.minifun.TypeSystem.TMappings;
import lpemc.rc.minifun.Utils;

public class ListTypeNode extends ConcreteTypeNode {
	protected TypeNode type;
	
	public ListTypeNode(TypeNode t){
		this.type = t;
	}

	@Override
	public String getStringRepr() {
		return TypeSystem.ListOf(type.typeCheck(null));
	}

	@Override
	public String typeCheckLogic(TMappings mappings) {
		String typeOfElems = type.typeCheck(mappings);
		
		if(		//TypeSystem.IsFunctionType(typeOfElems) ||
				typeOfElems.equals(TypeSystem.Tgeneric) ||
				typeOfElems.equals(TypeSystem.Tbool) ||
				typeOfElems.equals(TypeSystem.Tint) ||
				typeOfElems.startsWith("listOf"))
			return TypeSystem.ListOf(typeOfElems);
		Utils.log("ERROR: bad type of list " + type.typeCheck(mappings));
		return TypeSystem.Terror;
	}

	@Override
	public String genCode() {
		return "";
	}


}
