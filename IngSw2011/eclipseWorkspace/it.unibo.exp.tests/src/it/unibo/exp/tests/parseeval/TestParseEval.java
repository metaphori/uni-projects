package it.unibo.exp.tests.parseeval;

import it.unibo.exp.interfaces.IExp;
import it.unibo.exp.interfaces.INumExp;
import it.unibo.exp.interfaces.IOpExp;
import it.unibo.exp.interfaces.IParser;
import it.unibo.exp.interpreter.ExpEvaluator;
import it.unibo.exp.parser.Parser;
import junit.framework.TestCase;

public class TestParseEval extends TestCase {
	private IParser parser;
	
	
	public void setUp(){
		parser = new Parser();
	}
	
	public void test(){
		
		parser = new Parser();
		
		try{
			
			IExp e2 = parser.parse("111");
			assertTrue("The parsing must generate an INumExp", e2 instanceof INumExp);
			
			IExp e1 = parser.parse("   120    / 60");
			assertTrue("The parsing must generate an IOpExp", e1 instanceof IOpExp );
			
			ExpEvaluator ev = new ExpEvaluator();
			e1.accept(ev);
			double d = ev.getResult();
			assertTrue("Result must be 2.", d==2);
			
		} catch(Exception exc){
			fail();
		}
		
	}
	
	
}
