package lpemc.rc.minifun.ast;

import java.util.ArrayList;
import java.util.HashMap;

import lpemc.rc.minifun.TypeSystem;
import lpemc.rc.minifun.TypeSystem.TMappings;
import lpemc.rc.minifun.Utils;

public class LetBlockNode extends Node {

	protected ArrayList<IdentifierDeclNode> declist;
	protected Node exp;

	public LetBlockNode(ArrayList<IdentifierDeclNode> declist, Node exp){
		this.declist = declist;
		this.exp = exp;
	}
	
	public ArrayList<IdentifierDeclNode> getDeclarations(){ return declist; }
	public Node getExp(){ return exp; }

	@Override public String getStringRepr(){
		String decls = "";
		for(Node n : declist){
			decls += n.getStringRepr() + ", ";
		}
		return "LET_BLOCK["+decls+" "+exp.getStringRepr()+"]";
	}
	
	@Override public String typeCheckLogic(TMappings mappings){
		for(Node n : declist){
			if(n.typeCheck(mappings).equals(TypeSystem.Terror)) return TypeSystem.Terror;
		}
		return exp.typeCheck(mappings);
		
	}

	@Override
	public String genCode() {
		String code = "/* store variables local to a LET-BLOCK */\n";
		
		for(Node n : declist){
			code += n.genCode();
		}
		
		return  "/* LET BLOCK */\n" +
				code + 
				exp.genCode();
	}


}
