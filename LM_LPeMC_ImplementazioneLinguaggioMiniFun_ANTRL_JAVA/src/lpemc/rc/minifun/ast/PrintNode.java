package lpemc.rc.minifun.ast;

import java.util.HashMap;

import lpemc.rc.minifun.TypeSystem;
import lpemc.rc.minifun.TypeSystem.TMappings;
import lpemc.rc.minifun.Utils;

public class PrintNode extends Node {
	protected Node node;
	
	public PrintNode(Node node){
		this.node = node;
	}
	
	@Override public String getStringRepr() {
		return "PRINT["+node.getStringRepr()+"]";
	}
	
	@Override public String typeCheckLogic(TMappings mappings){
		return node.typeCheck(mappings);
	}

	@Override
	public String genCode() {
		return  node.genCode() +
				"print\n";
	}


}
