package lpemc.rc.minifun.ast;

import java.util.ArrayList;
import java.util.HashMap;

import lpemc.rc.minifun.TypeSystem;
import lpemc.rc.minifun.TypeSystem.TMappings;
import lpemc.rc.minifun.Utils;

public class VarDeclNode extends IdentifierDeclNode {
	protected Node exp;
	
	public VarDeclNode(String id, TypeNode type, Node exp){
		super(id, type);
		this.exp = exp;
	}
	
	@Override public String getStringRepr(){
		return "VAR_DECL["+id+", "+type.getStringRepr()+", "+exp.getStringRepr()+"]";
	}
	
	@Override public String typeCheckLogic(TMappings mappings){
		String expT = exp.typeCheck(mappings);
		String typeT = type.typeCheck(mappings);		
		
		if(TypeSystem.isCompatible(expT, typeT))
			return expT;
		
		Utils.log("ERROR: var " + id + " has type vs. value type mismatch: " + typeT + " AND " + expT);
		return TypeSystem.Terror;
	}

	@Override
	public String genCode() {
		return exp.genCode();
	}

	@Override
	public TMappings createTypeMappings(ArrayList<TypeNode> concreteTypes) {
		Utils.log("Cannot create mappings based on a variable declaration");
		System.exit(1);
		return null;
	}
	
}
