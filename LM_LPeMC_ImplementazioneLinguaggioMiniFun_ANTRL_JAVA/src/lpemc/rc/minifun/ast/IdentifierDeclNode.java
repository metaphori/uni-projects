package lpemc.rc.minifun.ast;

import java.util.ArrayList;
import java.util.HashMap;

import lpemc.rc.minifun.TypeSystem.TMappings;

public abstract class IdentifierDeclNode extends Node {
	protected String id;
	protected TypeNode type;
	
	public IdentifierDeclNode(String id, TypeNode type){
		this.id = id;
		this.type = type;
	}
	
	public String getName(){
		return id;
	}
	
	public TypeNode getType(){
		return type;
	}
	
	/**
	 * For a decl with generic types <A,..,Z>
	 * 		and an instantiation with concrete types <Ta,...,Tz>
	 * returns a hashmap with the mappings
	 * 		A ---> Ta
	 * 		.........
	 * 		Z ---> Tz
	 */
	public abstract TMappings createTypeMappings(ArrayList<TypeNode> concreteTypes);
		
}
