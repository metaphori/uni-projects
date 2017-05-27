package lpemc.rc.minifun.ast;

import java.util.HashMap;

import lpemc.rc.minifun.MiniFunLexer;
import lpemc.rc.minifun.TypeSystem;
import lpemc.rc.minifun.TypeSystem.TMappings;
import lpemc.rc.minifun.Utils;

public class ListConsNode extends Node {
	protected Node head;
	protected Node tail;
	
	public ListConsNode(Node head, Node tail){
		this.head = head;
		this.tail = tail;
	}

	@Override
	public String getStringRepr() {
		return "LIST["+head.getStringRepr()+", " + tail.getStringRepr() +"]";
	}
	
	@Override public String typeCheckLogic(TMappings mappings){
		return TypeSystem.CheckListType(head, tail, mappings);
	}

	@Override
	public String genCode() {
		return 
			"/* list construction */\n/* head */\n" +
			head.genCode() +  
			"/* tail */\n" +
			tail.genCode() + 
			/* NOTE: l'heap è agli indirizzi bassi della stessa struttura che ospita lo stack */
			"/* put pointer to address of tail on the next-of-next free cell (HP+1) on heap */\n" +
			"push 1\n"+"lhp\n"+"add\n"+"sw\n"+ 
			"/* put head value on the next free cell (HP) on heap */\n"+
			"lhp\n"+"sw\n"+
			"lhp\n"+ // il "vecchio" HP (che punta alla coppia che abbiamo costruito) viene lasciato sullo stack
			"push 2\n"+"lhp\n"+"add\n"+"shp\n"; // aggiorno HP (per farlo puntare alla prima cella libera dell'heap)
	}	

}
