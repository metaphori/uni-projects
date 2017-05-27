package lpemc.rc.minifun.ast;

import java.util.HashMap;

import lpemc.rc.minifun.MiniFunLexer;
import lpemc.rc.minifun.TypeSystem;
import lpemc.rc.minifun.TypeSystem.TMappings;
import lpemc.rc.minifun.Utils;

public class IfNode extends Node {
	protected Node cond;
	protected Node thenBody;
	protected Node elseBody;
	
	public IfNode(Node cond, Node thenBody, Node elseBody){
		this.cond = cond;
		this.thenBody = thenBody;
		this.elseBody = elseBody;
	}

	@Override
	public String getStringRepr() {
		return "IF["+cond.getStringRepr()+", "+thenBody.getStringRepr()+", "+elseBody.getStringRepr()+"]";
	}

	@Override
	public String typeCheckLogic(TMappings mappings){
		String condT = cond.typeCheck(mappings);
		String thenT = thenBody.typeCheck(mappings);
		String elseT = elseBody.typeCheck(mappings);
		
		if(!TypeSystem.isCompatible(condT, new BoolTypeNode().typeCheck(mappings))) return TypeSystem.Terror;
		
		if(TypeSystem.isCompatible(thenT, elseT))
			return thenT;
		if(TypeSystem.isCompatible(elseT, thenT))
			return elseT;
	
		return TypeSystem.Terror;
	}


	@Override
	public String genCode() {
		String lblThen = Utils.genLabel("then");
		String lblEnd = Utils.genLabel("end");
		
		return  "/* if */\n"+
				cond.genCode() +
				"push " + TypeSystem.TRUE_REPR + "\n" + 
				"beq " + lblThen + "\n" +
				"/* else */\n"+
				elseBody.genCode() +
				"b " + lblEnd + "\n" +
				lblThen+":\n" +
				thenBody.genCode() +
				lblEnd+":\n";
	}

	
}
