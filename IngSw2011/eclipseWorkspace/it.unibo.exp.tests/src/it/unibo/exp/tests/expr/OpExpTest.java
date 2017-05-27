package it.unibo.exp.tests.expr;
import it.unibo.exp.interfaces.*;
import it.unibo.exp.expr.impl.*;


/**
 * @author Antonio Natali
 */
public class OpExpTest extends junit.framework.TestCase {

	private IOpExp fixture;
	
	public OpExpTest(){

	}

	public OpExpTest(String arg0){
		super(arg0);
	}


	protected void setUp() throws Exception{
		super.setUp();		
		//10+20
		fixture = new OpExp( "+", new NumExp(10), new NumExp(20) );
		System.out.println("*** OpExpTest setup ***");
	}

	protected void tearDown()
	  throws Exception{
		super.tearDown();
		System.out.println("*** OpExpTest tearDown ***");
	}

	public final void testGetLeft(){
		assertTrue("testGetLeft", fixture.getLeft() != null);
	}

	public final void testGetOpName(){
		assertTrue("testGetOpName", fixture.getOpName().equals("+"));
	}

	public final void testGetRight(){
		assertTrue("testGetRight", fixture.getRight() != null);
	}

	public final void testEval(){
		try {
			assertTrue("testGetRight", fixture.eval() == 30.0 );
		} catch (Exception e) {
			fail("Error " + e);
 		}
	}

}