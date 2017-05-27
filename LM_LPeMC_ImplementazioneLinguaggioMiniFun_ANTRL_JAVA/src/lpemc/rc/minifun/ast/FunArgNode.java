package lpemc.rc.minifun.ast;

import java.util.ArrayList;
import java.util.HashMap;

import lpemc.rc.minifun.TypeSystem.TMappings;
import lpemc.rc.minifun.Utils;

public class FunArgNode extends IdentifierDeclNode {
	protected FunDeclNode funDecl;
	
	public FunArgNode(String id, TypeNode type, FunDeclNode funDecl){
		super(id, type);
		this.funDecl = funDecl;
	}

	@Override
	public String getStringRepr() {
		return "FUN_ARG["+id+", "+type.getStringRepr()+"]";
	}

	@Override
	public String typeCheckLogic(TMappings mappings) {
		return type.typeCheck(mappings);
	}

	@Override
	public String genCode() {
		return "";
	}

	@Override
	public TMappings createTypeMappings(ArrayList<TypeNode> concreteTypes) {
		return this.funDecl.createTypeMappings(concreteTypes);
	}

}
