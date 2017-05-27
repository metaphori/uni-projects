package it.unibo.exp.tests.expr;
import it.unibo.exp.expr.impl.*;
import it.unibo.exp.interfaces.INumExp;
 

/**
 * @author Antonio Natali
 */
public class NumeExpTest extends junit.framework.TestCase {
	private INumExp fixture;
	
	public NumeExpTest(){
	}

	public NumeExpTest(String arg0){
		super(arg0);
	}

	protected void setUp()
	  throws Exception{
		super.setUp();
		System.out.println("*** AtomicExpTest setup ***");
		fixture = new NumExp(12);
	}

	protected void tearDown()
	  throws Exception{
		super.tearDown();
		System.out.println("*** AtomicExpTest tearDown ***");
	}

	public final void testGetVal(){
		assertTrue("testGetVal", fixture.getVal()==12 );
	}


//	public final void testIsAtomic(){
//		assertTrue("testGetVal", fixture.isAtomic() );
//	}

}