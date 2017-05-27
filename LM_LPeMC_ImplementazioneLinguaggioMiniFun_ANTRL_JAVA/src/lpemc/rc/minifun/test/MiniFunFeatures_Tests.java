package lpemc.rc.minifun.test;

import static org.junit.Assert.*;
import lpemc.rc.minifun.TypeSystem;

import org.junit.Test;

public class MiniFunFeatures_Tests {
	String F, T, pre, post;
	
	public MiniFunFeatures_Tests(){
		F = TypeSystem.FALSE_REPR;
		T = TypeSystem.TRUE_REPR;
		
		pre = "let in print(";
		post = ");";		
		//Utils.LEVEL = Utils.DEBUG_LEVEL;
	}
	
	/********************************************/
	/**************** Functions *****************/
	/********************************************/
	
	@Test public void test_declaration_and_invocation(){
		String output = TestUtils.output( 
				"let											"+
				"	fun f:int (b:bool, x:int, y:int){			"+
				"		if(b) then { x+y }						"+
				"		else { (0-x)*y }						"+
				"	};											"+
				"in												"+
				"	print( f(true , (0-5), 7 ) ) +				"+
				"	print( f(false,     6, 10) );				");
		assertEquals("2\n-60", output);
	}
	
	@Test public void test_function_references(){
		String output = TestUtils.output(
			"let											"+
			"	fun f:int (b:bool, x:int, y:int){			"+
			"		if(b) then { x+y }						"+
			"		else { (0-x)*y }						"+
			"	};											"+
			"	var fref:(bool,int,int)->int = f;			"+
			"in												"+
			"	print( fref(true , (0-5), 7 ) ) +			"+
			"	print( fref(false,     6, 10) );			");
		assertEquals("2\n-60", output);	
		
		/**
		 * For the next program to work we need to fully implement closures
		 */
		/*
		output = TestUtils.output(
				"let											"+
				"	var one:int = 1;							"+
				"	fun g:(int,int)->bool (){					"+
				"		let										"+
				"			fun less:bool (x:int, y:int) {		"+
				"				x<=(y-one)						"+
				"			};									"+
				"		in										"+
				"			less;								"+
				"	};											"+
				"	var less:(int,int)->bool = g();				"+
				"in												"+
				"	print(less((0-9),8)) 	&& 					"+
				"	print(less(7,8)) 		&& 					"+
				"	print(not(less(8,8))) 	&&					"+
				"	print(not(less(9,8)));");
		assertEquals(T+"\n"+T+"\n"+T+"\n"+T, output);	*/		
	}
	
	/*********************************************/
	/******************* Lists *******************/ 
	/*********************************************/

	@Test public void test_list_primitives_CONS_FIRST_REST(){
		String output = TestUtils.output(pre + "first( |7,8| )" + post);
		assertEquals("7", output);
		
		output = TestUtils.output(pre + "first(rest( |7,8| ))" + post);
		assertEquals("8", output);
		
		/* dealing with lists of lists */
		output = TestUtils.output(pre + "rest(rest( | |7,8|, |10,11| | )) == empty" + post);
		assertEquals(T, output);
		
		output = TestUtils.output(pre + "first(first(rest( | |44,55|, |13,17| | )))" + post);
		assertEquals("13", output);
		
		output = TestUtils.output(pre + "first(first(first( | | | 787, 0, 1 | | | )))" + post);
		assertEquals("787", output);
		
	}
	
	/********************************************/
	/************** Math operators **************/
	/********************************************/
	
	@Test public void test_comparison_operators(){
		String output = TestUtils.output(pre + "99<=100" + post);
		assertEquals(T, output);		
		
		output = TestUtils.output(pre + "100<=100" + post);
		assertEquals(T, output);		
		
		output = TestUtils.output(pre + "100>=100" + post);
		assertEquals(T, output);

		output = TestUtils.output(pre + "99>=100" + post);
		assertEquals(F, output);
		
		output = TestUtils.output(pre + "99==100" + post);
		assertEquals(F, output);	
		
		output = TestUtils.output(pre + "100==100" + post);
		assertEquals(T, output);			
		
		output = TestUtils.output(pre + "false==false" + post);
		assertEquals(T, output);			
		
		output = TestUtils.output(pre + "(0-10)>=9" + post);
		assertEquals(F, output);			
		
		output = TestUtils.output(pre + "(0-3<=2)" + post);
		assertEquals(T, output);			
		
		/**********************/
		/** Particular cases **/
		/**********************/
		// Testing two lists is false because we're testing their addresses
		// 		We can change this behavior by modifying == to work with list *values*
		output = TestUtils.output(pre + "|1,2,3| == |1,2,3|" + post);
		assertEquals(F, output);
		// Testing if a variable is the same should be true also with references
		//		as we're comparing the same address
		output = TestUtils.output("let var l:list[int]=| |1,2,3| |; in print(l==l);");
		assertEquals(T, output);
		// but at the present time it does not work with functions
		//		because functions are represented with two cells (address + access_link)
		//		so with the current behavior == checks the address of 2nd operand
		//		against the access link of 1st operand
		output = TestUtils.output("let fun f:int(){0}; in print(f==f);");
		assertEquals(F, output);		
		
	}
	
	@Test public void test_arithmetic(){
		/* testing precedence of *,/ over +,- */
		String output = TestUtils.output(pre + "100-50*2+4/4-1" + post);
		assertEquals("0", output);
		
		output = TestUtils.output(pre + "0-8*2+8/2" + post);
		assertEquals("-12", output);			
	}
	
	@Test public void test_PLUS_MINUS_operators(){
		String output = TestUtils.output(pre + "100+50" + post);
		assertEquals("150", output);
		
		output = TestUtils.output(pre + "100-60" + post);
		assertEquals("40", output);	
		
		output = TestUtils.output(pre + "1+2+3-3-2-1" + post);
		assertEquals("0", output);	
		
		output = TestUtils.output(pre + "0-90+80+0-0" + post);
		assertEquals("-10", output);			
	}
	
	@Test public void test_TIMES_DIV_MOD_operators(){
		/* MUL */
		String output = TestUtils.output(pre + "100*50" + post);
		assertEquals("5000", output);	
		
		output = TestUtils.output(pre + "2*3*(0-4)" + post);
		assertEquals("-24", output);	
		
		/* INTEGER DIV */
		output = TestUtils.output(pre + "100/50" + post);
		assertEquals("2", output);		
		
		output = TestUtils.output(pre + "100/5/2/1/2" + post);
		assertEquals("5", output);

		output = TestUtils.output(pre + "100/(5/2)/(2/2)" + post);
		assertEquals("50", output);		
		
		/* MIXING DIV and MUL */
		output = TestUtils.output(pre + "50*3/25*(0-2)/4" + post);
		assertEquals("-3", output);
		
		/* MODULO */
		output = TestUtils.output(pre + "100%5" + post);
		assertEquals("0", output);		
		
		output = TestUtils.output(pre + "100%3" + post);
		assertEquals("1", output);
		
		output = TestUtils.output(pre + "100%500" + post);
		assertEquals("100", output);	
		
		output = TestUtils.output(pre + "20%8" + post);
		assertEquals("4", output);	
		
		/* MIXING MODULO, DIV, MUL */
		output = TestUtils.output(pre + "7%8*3%8*(10%7/2)" + post);
		assertEquals("5", output);		
	}
	
	/*********************************************/
	/************* Logical operators *************/
	/*********************************************/

	@Test public void test_NOT_logical_operator(){
		String output = TestUtils.output(pre + "not(false)" + post);
		assertEquals(T, output);
		
		output = TestUtils.output(pre + "not(true)" + post);
		assertEquals(F, output);
		
		output = TestUtils.output(pre + "not(not(true))" + post);
		assertEquals(T, output);		
		
		output = TestUtils.output(pre + "not(not(not(true)))" + post);
		assertEquals(F, output);		
	}
	
	@Test public void test_AND_logical_operator(){		
		String output = TestUtils.output(pre + "false && false" + post);
		assertEquals(F, output);
		output = TestUtils.output(pre + "true && false" + post);
		assertEquals(F, output);
		output = TestUtils.output(pre + "false && true" + post);
		assertEquals(F, output);
		output = TestUtils.output(pre + "true && true" + post);
		assertEquals(T, output);
		
		// testing short-circuited-ness
		output = TestUtils.output(pre + "true && print(true)" + post);
		assertEquals(T+"\n"+T, output);
		
		output = TestUtils.output(pre + "true && print(false)" + post);
		assertEquals(F+"\n"+F, output);
		
		output = TestUtils.output(pre + "false && print(true)" + post);
		assertEquals(F, output);		
		
		// testing multiple ORs
		output = TestUtils.output(pre + "true && true && true && false" + post);
		assertEquals(F, output);	
		
		output = TestUtils.output(pre + "true && true && true && true && true" + post);
		assertEquals(T, output);
	}
	
	@Test public void test_OR_logical_operator(){
		String output = TestUtils.output(pre + "false || false" + post);
		assertEquals(F, output);
		output = TestUtils.output(pre + "true || false" + post);
		assertEquals(T, output);
		output = TestUtils.output(pre + "false || true" + post);
		assertEquals(T, output);
		output = TestUtils.output(pre + "true || true" + post);
		assertEquals(T, output);
		
		// testing short-circuited-ness
		output = TestUtils.output(pre + "true || print(true)" + post);
		assertEquals(T, output);
		
		output = TestUtils.output(pre + "false || print(false)" + post);
		assertEquals(F+"\n"+F, output);
		
		output = TestUtils.output(pre + "false || print(true)" + post);
		assertEquals(T+"\n"+T, output);		
		
		// testing multiple ORs
		output = TestUtils.output(pre + "false || false || true || false" + post);
		assertEquals(T, output);	
		
		output = TestUtils.output(pre + "false || false || false || false || false || false" + post);
		assertEquals(F, output);
	}	

}
