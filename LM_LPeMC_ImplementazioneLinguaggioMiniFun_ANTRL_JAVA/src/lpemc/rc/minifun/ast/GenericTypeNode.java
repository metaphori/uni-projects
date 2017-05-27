package lpemc.rc.minifun.ast;

import java.util.HashMap;

import lpemc.rc.minifun.TypeSystem;
import lpemc.rc.minifun.TypeSystem.TMappings;
import lpemc.rc.minifun.Utils;

public class GenericTypeNode extends TypeNode {
	protected String id;
	
	public GenericTypeNode(String id){
		this.id = id;
		//this.generic = true;
	}	
	
	
	@Override
	public String getStringRepr() {
		return id;
	}

	@Override
	public int hashCode(){
		return this.getStringRepr().hashCode();
	}

	@Override
	public boolean equals(Object n){
		if(!(n instanceof Node)) return false;
		Node node = (Node)n;
		return this.getStringRepr().equals(node.getStringRepr());
	}	
	
	@Override
	public String typeCheckLogic(TMappings mappings) {
		if(mappings!=null){
			ConcreteTypeNode ctn = mappings.get(this);
			if(ctn!=null){
				return ctn.typeCheck(mappings);
			}
		}
		return TypeSystem.Tgeneric;
	}

	@Override
	public String genCode() {
		return "";
	}

}
