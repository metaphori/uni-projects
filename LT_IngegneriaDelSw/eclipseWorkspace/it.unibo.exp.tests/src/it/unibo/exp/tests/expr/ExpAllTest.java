package it.unibo.exp.tests.expr;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;


public class ExpAllTest extends TestSuite{
	
	public ExpAllTest(String name) {
		super(name);
	}

	public static Test suite() {
		TestSuite suite = new ExpAllTest("Expr Tests");
		suite.addTestSuite(NumeExpTest.class);
		suite.addTestSuite(OpExpTest.class);
		suite.addTestSuite(ExpTest.class);
		return suite;
	}

	public static void main(String[] args) {
		TestRunner.run(suite());
	}

}
