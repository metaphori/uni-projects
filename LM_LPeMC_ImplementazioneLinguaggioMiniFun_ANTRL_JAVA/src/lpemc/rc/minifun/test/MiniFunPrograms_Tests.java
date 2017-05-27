package lpemc.rc.minifun.test;

import static org.junit.Assert.*;

import org.junit.Test;

import lpemc.rc.minifun.TypeSystem;
import lpemc.rc.minifun.Utils;

public class MiniFunPrograms_Tests {

	public MiniFunPrograms_Tests(){
		Utils.LEVEL = Utils.NONE_LEVEL; // we don't want output to video
	}	
	
	@Test public void test_generic_list_reverse_methodset(){
		String program_code =
				"let		" +
				"  	fun makelist:list[int] (start:int, end:int) { 							\n" + 
				"  		if (start==end) then { [start::empty] } 							\n" + 
				"  		else { [start :: makelist(start+1, end)]  }							\n" + 
				"  	};																		\n" + 
				"	fun foldleft<T,U>:U (f:(T,U)->U, acc:U, l:list[T]){						\n" + 
				"		if(l==empty) then { acc }											\n" + 
				"		else { foldleft<T,U>(f, f(first(l),acc), rest(l)) }					\n" + 
				"	};																		\n" + 
				"	fun cons<C>:list[C] (head:C, tail:list[C]){ [head::tail] };				\n" + 
				"	fun reverse<W>:list[W] (l:list[W]){										\n" + 
				"		foldleft<W,list[W]>(cons<W>, empty, l)								\n" + 
				"	};																		\n" + 
				"	fun printlist<P>:list[P] (l:list[P]){									\n" + 
				"		if(l==empty) then { empty }											\n" + 
				"		else { [print(first(l)) :: printlist<P>(rest(l))] }					\n" + 
				"	};																		\n" +  
				"in																			\n" + 
				"  printlist<int>( 															\n" + 
				"    reverse<int>(makelist(4,8)) 											\n" + 
				" 	);";
		String output = TestUtils.output(program_code);
		String expected_output = "8\n7\n6\n5\n4";
		assertEquals(expected_output, output);
		
	}
	
	@Test public void test_complex_program(){
		String program_declarations = 
			"let													"+
				"fun makelist:list[int] (start:int, end:int) { 		"+
			  	"	if (start==end) then { [start::empty] } 			"+
			  	"	else { [start :: makelist(start+1, end)]  }			"+
			  	"};  "+
			  	
			  	"fun double:int (x:int) { x*2 };	"+

			  	"fun printlist:int (l:list[int]) { 	"+
			  	"	if(l==empty) then { 0 } else { print(first(l)) + printlist(rest(l)) } 	"+
			  	"}; 																		"+
			  	
			  	"fun map:list[int]( lst:list[int], argf:(int)->int ) { 						"+
			  	"	if (lst==empty) then { empty }											"+
			  	"	else { [ argf(first(lst)) :: map(rest(lst), argf) ] }					"+
			  	"};																			"+

				"fun filter:list[int](lst:list[int], predicate:(int)->bool) {	"+
				"	let 														"+
				"		fun getRest:list[int] (l:list[int]){					"+
				"			if(l==empty) then { empty }							"+
				"			else { rest(l) }									"+
				"		};														"+
				"		fun getHead:int (l:list[int]){							"+
				"			if(l==empty) then { 0 } 							"+
				"			else { first(l) }									"+
				"		};														"+
				"		var rl:list[int] = getRest(lst);						"+
				"		var hd:int = getHead(lst);								"+
				"		var myfref:(int)->bool = predicate;						"+
				"		fun myfref2:(int)->bool(){ myfref };					"+
				"	in															"+
				"		if(lst==empty) then { empty }							"+
				"		else {													"+
				"			if(predicate(first(lst))) then { [hd :: filter(rest(lst),myfref2())] }	"+
				"			else { filter(rl,myfref) }												"+
				"		};														"+
				"};																"+

				"fun mypred:bool (x:int) { x>=5 };		"+

			"in											";
		
		String program_body = "printlist(map(filter(makelist(3,7), mypred), double));	";
		String output = TestUtils.output(program_declarations + program_body);
		String expected_output = "10\n12\n14";
		assertEquals(expected_output, output);
	}
	
	@Test public void test_complex_program_with_generic_declarations(){
		String program_code = 
			"let																				"+
			"	fun nextInt:int (i:int) { i+1 };												"+
				
			"	fun printlist<P>:list[P] (ppp:list[P]) { 										"+
			"	  if(ppp==empty) then { empty } 												"+
			"	  else { [print(first(ppp)) :: printlist<P>(rest(ppp))] } 						"+
			"	};																				"+

			"	fun makelist<X>:list[X] (start:X, end:X, succ:(X)->X) {							"+
			"		if(start==end) then { [start::empty] }										"+
			"		else { [start :: makelist<X>(succ(start), end, succ) ] }					"+
			"	};																					"+
			
			"	fun makelist2<N>:list[X] (start:N, end:N, succ:(N)->N, equal:(N,N)->bool) {		"+
			"		if(equal(start,end)) then { [start::empty] }								"+
			"		else { [start :: makelist2<N>(succ(start), end, succ, equal) ] }			"+
			"	};																				"+
				
			"	fun succLst:list[int](kkk:list[int]){ [ first(kkk)-1 :: kkk ] };				"+
				
			"	fun eqList<E>:bool (l1:list[E], l2:list[E]){									"+
			"		if((l1==empty) && (l2==empty)) then { true }								"+
			"		else {																		"+
			"			if((l1==empty) || (l2==empty)) then { false } else {					"+
			"				if(not(first(l1)==first(l2))) then { false } 						"+
			"				else { eqList<E>(rest(l1), rest(l2)) }								"+
			"			}																		"+
			"		}																			"+
			"	};																				"+
				
			"	fun foreach<F>:int (lst:list[F], do:(F)->F) {									"+
			"		if(lst==empty) then { 0 }													"+
			"		else { do(first(lst))+print(print(print(9999)))+foreach<F>(rest(lst),do) }	"+
			"	};																				"+
			
			"	fun printint:int(x:int){ print(x) };											"+

			"	var lst1:list[int] = makelist<int>(5,9,nextInt);								"+
			"	var lst2:list[int] = makelist<int>(0,9,nextInt);								"+			
			
			"in																					"+
			
			"	foreach<list[int]>( 															"+
			"	  makelist2<list[int]>(lst1,lst2,succLst,eqList<int>),							"+
			"	  printlist<int>																"+
			"	);																				";
		
		String output = TestUtils.output(program_code);
		String expected_output = 
				"5\n6\n7\n8\n9\n9999\n9999\n9999\n"							+
				"4\n5\n6\n7\n8\n9\n9999\n9999\n9999\n"						+
				"3\n4\n5\n6\n7\n8\n9\n9999\n9999\n9999\n"					+
				"2\n3\n4\n5\n6\n7\n8\n9\n9999\n9999\n9999\n"				+
				"1\n2\n3\n4\n5\n6\n7\n8\n9\n9999\n9999\n9999\n"				+
				"0\n1\n2\n3\n4\n5\n6\n7\n8\n9\n9999\n9999\n9999"			;
		assertEquals(expected_output, output);		
	}
	
	
}
