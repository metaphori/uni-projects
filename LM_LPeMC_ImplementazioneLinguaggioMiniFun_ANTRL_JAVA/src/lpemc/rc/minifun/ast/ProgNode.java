package lpemc.rc.minifun.ast;

import java.util.ArrayList;

import lpemc.rc.minifun.TypeSystem;
import lpemc.rc.minifun.TypeSystem.TMappings;
import lpemc.rc.minifun.Utils;


public class ProgNode extends Node {
	protected ArrayList<IdentifierDeclNode> declist;
	protected Node exp;
	
	public ProgNode(Node letBlock){
		if(letBlock instanceof LetBlockNode){
			LetBlockNode let = (LetBlockNode) letBlock;
			this.declist = let.getDeclarations();
			this.exp = let.getExp();
		} else {
			System.out.println("ERROR: the program must start with a LET-BLOCK\n ");
			System.exit(1);
		}
	}
	
	@Override public String getStringRepr(){
		String decls = "";
		for(Node n : declist){
			decls += n.getStringRepr() + ", \n\t";
		}
		return "PROG[\n\t"+decls+exp.getStringRepr()+"\n]";
	}
	
	@Override public String typeCheckLogic(TMappings mappings){
		for(Node n : declist){
			
			if(n.generic==true){
				continue; // delay typechecking for generic decl
			}
			
			if(n.typeCheck(mappings)==TypeSystem.Terror) return TypeSystem.Terror;
		}
		return exp.typeCheck(mappings);
		
	}

	@Override
	public String genCode() {
		String code = 	"/*******************************************************/\n"+
						"/********* store top-level (program) variables *********/\n"+
						"/*******************************************************/\n";
		
		for(Node n : declist){
			code += n.genCode();
		}
		
		return  code + "\n\n" +
				"/****************************************/\n"+
				"/************* PROGRAM BODY *************/\n"+
				"/****************************************/\n\n" +
				exp.genCode() +
				"halt\n" +
				Utils.getFunctionCode();
	}

}