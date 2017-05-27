package lpemc.rc.minifun.ast;

import java.util.ArrayList;
import java.util.HashMap;

import lpemc.rc.minifun.TypeSystem;
import lpemc.rc.minifun.TypeSystem.TMappings;
import lpemc.rc.minifun.Utils;

public class FunTypeNode extends ConcreteTypeNode {
	protected ArrayList<TypeNode> typesOfArgs;
	protected TypeNode resultType;
	
	protected TMappings memMaps = null;
	
	public FunTypeNode(ArrayList<TypeNode> typesOfArgs, TypeNode resultType){
		this.typesOfArgs = typesOfArgs;
		this.resultType = resultType;
	}
	
	public ArrayList<TypeNode> getArgTypes(){ return typesOfArgs; }
	public TypeNode getResultType(){ return resultType; }
	
	@Override
	public String getStringRepr() {
		return TypeSystem.GetFunctionTypeRepr(typesOfArgs, resultType, null);
	}

	@Override
	public String typeCheckLogic(TMappings mappings) {
		this.memMaps = mappings;
		return TypeSystem.GetFunctionTypeRepr(typesOfArgs, resultType, mappings);
	}

	@Override
	public String genCode() {
		return "";
	}

}
