package it.unibo.exp.tests.interpreter;
import junit.framework.Test;
import junit.framework.TestSuite;
import it.unibo.exp.interfaces.*;
import it.unibo.exp.expr.impl.*;
import it.unibo.exp.interpreter.ExpEvaluator;
import it.unibo.exp.tests.expr.NumeExpTest;
import it.unibo.exp.tests.expr.ExpAllTest;
import it.unibo.exp.tests.expr.ExpTest;
import it.unibo.exp.tests.expr.OpExpTest;


/**
 * @author Antonio Natali
 */
public class EvalTest extends junit.framework.TestCase {
	private ExpEvaluator evaluator ;

	public EvalTest(){
	}

	public EvalTest(String arg0){
		super(arg0);
	}

	public static Test suite() {
		TestSuite suite = new TestSuite("EvalTest");
		suite.addTest( new EvalTest("testNum") );
		suite.addTest( new EvalTest("testOneOp") );
		suite.addTest( new EvalTest("testManyOp") );
		return suite;
	}

	protected void setUp() throws Exception{
		super.setUp();		
		evaluator = new ExpEvaluator();
//		System.out.println("*** EvalTest setup ***");
	}

	protected void tearDown()
	  throws Exception{
		super.tearDown();
//		System.out.println("*** EvalTest tearDown ***");
	}

	public void testNum() {
		INumExp exp = new NumExp(2);
  		try {
	 		exp.accept(evaluator);
			Double result =  evaluator.getResult();
			System.out.println( "The result of 2 is: " + result );
			assertTrue("testNum", result == 2.0 );
		} catch (Exception e) {
			fail("testNum " + e.getMessage());
		}
 	}
	

	public void testOneOp(){
		IExp exp = new OpExp( "*", new NumExp(2), new NumExp(3) );		
  		try {
			exp.accept(evaluator);
			Double result =  evaluator.getResult();
			System.out.println( "The result of 2*3 is: " + result );
			assertTrue("testOneOp", result == 6.0 );
		} catch (Exception e) {
			fail("testOneOp " + e.getMessage());
		}
	}

	public void testManyOp(){
		IExp e1 = new OpExp( "*", new NumExp(2), new NumExp(3) );		
		IExp e2 = new OpExp( "-", new NumExp(4), new NumExp(1) );
		IExp exp = new OpExp( "-", e1, e2 );	
 		try {
			exp.accept(evaluator);
			Double result =  evaluator.getResult();
			System.out.println( "The result of (2*3)-(4-1) is: " + result );
			assertTrue("testOneOp", result == 3.0 );
		} catch (Exception e) {
			fail("testManyOp " + e.getMessage());
 		}
	}

}