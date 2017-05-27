package lpemc.rc.minifun;

import java.util.HashMap;

import lpemc.rc.minifun.ast.IdentifierDeclNode;

public class STEntry {
	
	protected IdentifierDeclNode node;
	protected int offset;
	
	protected HashMap<String,String> props = new HashMap<String,String>();
	
	public STEntry(IdentifierDeclNode node, int offset){
		this.node = node;
		this.offset = offset;
	}
	
	public void setProperty(String p, String v){
		props.put(p, v);
	}
	public String getProperty(String p){
		return props.get(p);
	}
	
	public IdentifierDeclNode getIdentifierNode(){
		return this.node;
	}
	
	public int getOffset(){
		return this.offset;
	}

}
