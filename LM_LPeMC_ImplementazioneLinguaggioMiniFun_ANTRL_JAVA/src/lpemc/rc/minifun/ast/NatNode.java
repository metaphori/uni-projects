package lpemc.rc.minifun.ast;

import lpemc.rc.minifun.MiniFunLexer;
import lpemc.rc.minifun.TypeSystem;
import lpemc.rc.minifun.TypeSystem.TMappings;

public class NatNode extends Node{
	protected int val;
	
	public NatNode(int val){
		this.val = val;
	}
	
	@Override
	public String getStringRepr() {
		return "NAT["+val+"]";
	}

	@Override
	public String typeCheckLogic(TMappings mappings) {
		return TypeSystem.Tint;
	}

	@Override
	public String genCode() {
		return "push " + val + " /* NAT */\n";
	}

}
