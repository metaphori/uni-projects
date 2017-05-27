package lpemc.rc.minifun.test;

import static org.junit.Assert.*;
import static lpemc.rc.minifun.test.TestUtils.*;
import static lpemc.rc.minifun.TypeSystem.*;

import java.util.ArrayList;
import java.util.Arrays;

import lpemc.rc.minifun.TypeSystem;
import lpemc.rc.minifun.Utils;
import lpemc.rc.minifun.ast.GenericTypeNode;
import lpemc.rc.minifun.ast.IntTypeNode;
import lpemc.rc.minifun.ast.Node;

import org.junit.Test;

public class TypeSystemTests_Functions {
	String F, T, pre, post;
	
	public TypeSystemTests_Functions(){
		F = TypeSystem.FALSE_REPR;
		T = TypeSystem.TRUE_REPR;
		
		pre = "let in print(";
		post = ");";		
		
		//Utils.LEVEL = Utils.DEBUG_LEVEL;
	}

	@Test
	public void test_function_types() {
		/* The following is a function accepting:
		 * 		- a list of ints
		 * 		- a function which accepts a bool, a list of bools, and returns an int
		 * and returning a list of lists of bools
		 */
		Node root = parse(
			"let "+
			"	fun f:list[list[bool]] (l:list[int], g:(bool,list[bool])->int) { | |false| | };	"+
			"in  "+
			"	f;"
		);
		String expected_type = 
				GetFunctionTypeRepr(
						alist( // ARGS
								ListOf(Tint),
								GetFunctionTypeRepr(alist(Tbool, ListOf(Tbool)), Tint)
						), 
						ListOf(ListOf(Tbool)) // RETURN TYPE
				);
		assertEquals(expected_type, root.typeCheck());
		
		/* The following function accepts a bool
		 * and returns a function which accepts an int and returns a bool
		 */
		root = parse(
			"let 	"+
			"	fun f:(int)->bool (b:bool){					"+
			"		let										"+
			"			fun f1:bool (x:int) { x>=10 };	 	"+
			"			fun f2:bool (x:int) { x<=10 };		"+
			"		in										"+
			"			if(b) then {f1} else {f2};			"+
			"		};										"+
			"in  	"+
			"	f;	"
		);
		expected_type = GetFunctionTypeRepr(  
							alist(Tbool), // ARGS
							GetFunctionTypeRepr(alist(Tint), Tbool) // RETURN TYPE
						);
		assertEquals(expected_type, root.typeCheck());
		
	}
	
	@Test public void test_generic_function_types(){
		Node root = parse(
				"let				\n" + 
				"					\n" + 
				"	fun makelist<N>:list[N] (start:N, end:N, succ:(N)->N, equal:(N,N)->bool) {		\n" + 
				"		if(equal(start,end)) then { [start::empty] }								\n" + 
				"		else { [start :: makelist<N>(succ(start), end, succ, equal) ] }			\n" + 
				"	};																				\n" + 
				"																					\n" + 
				"	fun succLst:list[int](kkk:list[int]){ [ first(kkk)-1 :: kkk ] };				\n" + 
				"																					\n" + 
				"	fun eqList<E>:bool (l1:list[E], l2:list[E]){									\n" + 
				"		if((l1==empty) && (l2==empty)) then { true }								\n" + 
				"		else {																		\n" + 
				"			if((l1==empty) || (l2==empty)) then { false } else {					\n" + 
				"				if(not(first(l1)==first(l2))) then { false } else { eqList<E>(rest(l1), rest(l2)) }\n" + 
				"			}																		\n" + 
				"		}																			\n" + 
				"	};																				\n" + 
				"																					\n" + 
				"in																					\n" + 
				"																					\n" + 
				"  makelist<list[int]>;"				
		);
		String expected_type = GetFunctionTypeRepr(  
				alist( // ARGS
					ListOf(Tint), 
					ListOf(Tint),
					GetFunctionTypeRepr(alist(ListOf(Tint)), ListOf(Tint)),
					GetFunctionTypeRepr(alist(ListOf(Tint), ListOf(Tint)), Tbool)
				),
				ListOf(ListOf(Tint)) // RETURN TYPE
			);

		assertEquals(expected_type, root.typeCheck());		
	}
	
	@Test public void test_type_list(){
		Node node = parse(pre + "|1,2,3|" + post);
		assertEquals(ListOf(Tint), node.typeCheck());
		
		node = parse(pre + "|1,2,3,false,4|" + post);
		assertEquals(Terror, node.typeCheck());
		
		node = parse(pre + "|false,true,true|" + post);
		assertEquals(ListOf(Tbool), node.typeCheck());
		
		node = parse(pre + "|true,0,1|" + post);
		assertEquals(Terror, node.typeCheck());
		
		/* lists of lists */
		
		node = parse(pre + "| |1,1,2|, empty, |9,9,8| |" + post);
		assertEquals(ListOf(ListOf(Tint)), node.typeCheck());
		
		node = parse(pre + "| empty, | |true| | |" + post);
		assertEquals(ListOf(ListOf(ListOf(Tbool))), node.typeCheck());	
		
		node = parse(pre + "| |false,true|, |0,1,0| |" + post);
		assertEquals(Terror, node.typeCheck());
		
		
		node = parse(pre + "| empty |" + post);
		assertEquals(TlistOfAny, node.typeCheck());		
		
		
		node = parse(pre + "| 1,2, |3| |" + post);
		assertEquals(Terror, node.typeCheck());			
		
		node = parse(pre + "| empty, 0, 1 |" + post);
		assertEquals(Terror, node.typeCheck());	
		
	}

}
