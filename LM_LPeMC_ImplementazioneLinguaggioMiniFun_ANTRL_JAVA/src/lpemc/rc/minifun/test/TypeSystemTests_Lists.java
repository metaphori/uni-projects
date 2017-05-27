package lpemc.rc.minifun.test;

import static org.junit.Assert.*;
import static lpemc.rc.minifun.TypeSystem.*;
import static lpemc.rc.minifun.test.TestUtils.*;
import lpemc.rc.minifun.MiniFunLexer;
import lpemc.rc.minifun.MiniFunParser;
import lpemc.rc.minifun.Utils;
import lpemc.rc.minifun.Utils.LogBuffer;
import lpemc.rc.minifun.ast.Node;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.junit.Test;

public class TypeSystemTests_Lists {

	public TypeSystemTests_Lists(){
		//Utils.LEVEL = Utils.DEBUG_LEVEL; 
	}

	@Test
	public void test_typechecking_FIRST_on_lists(){	
		Node root;
		
		root = parse(letWithBody(
				"first( |1,2,3| )"
		));
		assertEquals(Tint, root.typeCheck());

		root = parse(letWithBody(
				"first( | |true,false|, |false,false,true| | )"
		));
		assertEquals(ListOf(Tbool), root.typeCheck());

		root = parse(letWithBody(
				"first( | empty, |true,true| | )"
		));
		assertEquals( ListOf(Tbool), root.typeCheck());

		root = parse(letWithBody(
				"first( | " +
						"	| |1,2|, empty |," +
						"	| empty, |1,2,4|, |7| | " +
						"| )"
		));
		assertEquals( ListOf(ListOf(Tint)), root.typeCheck());

	}
	
	@Test
	public void test_typechecking_REST_on_lists(){
		Node root;
		
		root = parse(letWithBody(
			"rest( |1,2,3| )"
		));
		assertEquals(ListOf(Tint), root.typeCheck());
		
		root = parse(letWithBody(
				"rest( empty )"
		));
		assertEquals(TlistOfAny, root.typeCheck());		
		
		root = parse(letWithBody(
			"rest( | |true,false|, |false,false,true| | )"
		));
		assertEquals(ListOf(ListOf(Tbool)), root.typeCheck());
		
		root = parse(letWithBody(
			"rest( | |false|, empty, |true,true| | )"
		));
		assertEquals( ListOf(ListOf(Tbool)), root.typeCheck());
		
		root = parse(letWithBody(
				"rest( | empty, |1,6,7|, |6,6| | )"
			));
		assertEquals( ListOf(ListOf(Tint)), root.typeCheck());
		
		root = parse(letWithBody(
				"rest( | empty, empty, empty | )"
		));
		assertEquals( TlistOfAny, root.typeCheck());
				
	}
	
	@Test
	public void test_typechecking_correct_listsoflists_literals() {
		Node root;
		
		// using list construction
		root = parse(letWithBody(
				"[ [1::[2::[3::empty]]] :: [ empty :: [ [1::[2::empty]] :: empty ] ] ]"
			));
		assertEquals( ListOf(ListOf(Tint)), root.typeCheck() );		
		
		// using list simplified syntax 
		
		root = parse(letWithBody(
				"| empty, |7,8,9|, |10|, empty |"
		));
		assertEquals( ListOf(ListOf(Tint)), root.typeCheck() );
		
		
		root = parse(letWithBody(
			"| " +
			"	| |false,true,false|, |true| |, " +
			"	| |false| |, " +
			"	empty, empty, empty, " +
			"	| |false,false| | " +
			"|"
		));
		assertEquals(ListOf(ListOf(ListOf(Tbool))), root.typeCheck());
		
	}
	
	@Test
	public void test_typechecking_erroneous_listsoflists_literals() {
		Node root;
		
		// there's an int in a list of booleans
		root = parse(letWithBody(
			"[false::[true::[7::empty]]]"
		));
		assertEquals(Terror, root.typeCheck());
		
		// there's a bool in a listoflistsofint
		root = parse(letWithBody(
			"| empty, |1,3,5,7|, |77|, |99,true,100| |"
		));
		assertEquals(Terror, root.typeCheck());
		
		// mixings listsOf different types
		root = parse(letWithBody(
				"| empty, |1,3,5,7|, |true,false,false| |"
			));
			assertEquals(Terror, root.typeCheck());
	}
	
	public String letWithBody(String str){
		return		"let		\n"+
					"in			\n"+
					"	"+str+";";
	}

}
