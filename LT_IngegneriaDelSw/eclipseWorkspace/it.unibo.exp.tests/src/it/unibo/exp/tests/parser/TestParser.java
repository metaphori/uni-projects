package it.unibo.exp.tests.parser;
 

import it.unibo.exp.interfaces.*;
import it.unibo.exp.interpreter.ExpEvaluator;
import it.unibo.exp.expr.impl.*;
import it.unibo.exp.parser.Parser;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
 
public class TestParser extends TestCase {
private String fixture;
private ISupportInput inpDev;
private IParser parser; 
private ILexer lexer; 
 
	public TestParser(String name) {
		super(name);
	}

	protected  void setUp() {
		parser	= new Parser(   );
	}

 
	protected  void tearDown() {
 	}

	public static Test suite() {
		TestSuite ts = new TestSuite();
 			ts.addTest( new TestParser("testFraseOk") );
			ts.addTest( new TestParser("testFraseKo") );
			ts.addTest( new TestParser("testEval") );
		return ts;
	}	
	
 
	public void testFraseOk(){
		System.out.println(" ====================== ");
		System.out.println("  testFraseOk     ");
 		System.out.println(" ====================== ");
  		//Create expected answer sequence
		try {
			 IExp resultExpected = 
				  new OpExp ("-" , 
					new OpExp("-", new NumExp(5), new NumExp(3) ),
				 	new NumExp(2) );
			IExp result = parser.parse( "5 - 3 - 2 " );
//			showExp(result);
			checkExp( result, resultExpected );
			System.out.println("");
		} catch (Exception e) {
 		}
	}
	
	
	public void testFraseKo(){
		System.out.println(" ====================== ");
		System.out.println("  testFraseKo     ");
 		System.out.println(" ====================== ");
  		//Create expected answer sequence
		try {
			IExp resultExpected = null;
			IExp result = parser.parse( "(1+2)*3 - )" );
			showExp(result);
			System.out.println();
			System.out.println("*** token not consumed="+ ((Parser)parser).getFirstTokenNonConsumed());
			checkExp( result, resultExpected );
		} catch (Exception e) {
//			System.out.println("*** FAIL="+ e.getMessage());
			fail("testFraseKo");
		}
	}

	
	public void testEval(){
		System.out.println(" ====================== ");
		System.out.println("  testEval     ");
 		System.out.println(" ====================== ");
  		//Create expected answer sequence
		try {
			IExp exp = parser.parse( "6 - 3 - 2 " );
 			ExpEvaluator exprEval = new ExpEvaluator();
			exp.accept(exprEval);
			double res = exprEval.getResult();
			System.out.println("res="+res);
			assertTrue("testEval", res == 1.0 );
		} catch (Exception e) {
			fail("testEval " + e);
 		}
	}

	protected void showExp( IExp e){
		if( e == null ) return;
		if( e instanceof INumExp )
			System.out.print(""+ ((INumExp)e).getVal());
		else{
			IComposedExp expr = (IComposedExp)e;
			showExp( expr.getLeft() );
			System.out.print(" "+ ((IComposedExp)e).getOpName() + " ");
			showExp( expr.getRight() );
		}
 	}
	
	protected void checkExp( IExp e, IExp expected){
		if( e == null  )
			assertTrue("checkExp null", e == expected );
		else if( e instanceof INumExp && expected instanceof INumExp ){
			System.out.print(""+ ((INumExp)e).getVal());
			assertTrue("checkExp atomic", 
					((INumExp)e).getVal() == ((INumExp)expected).getVal() );
		}else if( !( e instanceof INumExp ) && ! (expected instanceof INumExp)  ){
			IComposedExp expr = (IComposedExp)e;
			IComposedExp expectedExpr = (IComposedExp)expected;
			checkExp( expr.getLeft(), expectedExpr.getLeft() );
			assertTrue("checkExp composed", 
					((IComposedExp)e).getOpName().equals( ((IComposedExp)expected).getOpName() ));
			System.out.print(" "+ ((IComposedExp)e).getOpName() + " ");
			checkExp( expr.getRight(), expectedExpr.getRight() );
		}else
			fail("checkExp");
 	}
	
}
