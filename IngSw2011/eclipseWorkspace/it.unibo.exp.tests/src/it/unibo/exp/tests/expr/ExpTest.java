package it.unibo.exp.tests.expr;
import it.unibo.exp.interfaces.*;
import it.unibo.exp.expr.impl.*;
 

/**
 * @author Antonio Natali
 */
public class ExpTest extends junit.framework.TestCase {
	private IOpExp fixture;
    private IExp eLeft;
    private IExp eRight;
    
	public ExpTest(){

	}

	public ExpTest(String arg0){
		super(arg0);
	}

	protected void setUp()
	  throws Exception{
		super.setUp();
		eLeft   = new OpExp( "*", new NumExp(2), new NumExp(3) );		
		eRight  = new OpExp( "+", new NumExp(4), new NumExp(1) );
 		fixture	= new OpExp( "-", eLeft, eRight );
		System.out.println("*** ExpTest setup ***");
	}

	protected void tearDown()
	  throws Exception{
		super.tearDown();
		System.out.println("*** ExpTest tearDown ***");
	}

	public final void testExp(){
		IExp leftExp  = fixture.getLeft();
		IExp rightExp = fixture.getRight();		
		assertTrue("testGetLeft", leftExp != null && rightExp != null);
	}

}