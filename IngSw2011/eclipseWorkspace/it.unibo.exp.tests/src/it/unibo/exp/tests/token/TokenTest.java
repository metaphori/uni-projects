package it.unibo.exp.tests.token;
  
import it.unibo.exp.token.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TokenTest extends TestCase {

	public TokenTest(String arg0){
		super(arg0);
	}

	protected void setUp()
	  throws Exception{
		super.setUp();
	}
	
	protected void tearDown()
	  throws Exception{
		super.tearDown();
	}
	
	
	public static Test suite() {
		TestSuite ts = new TestSuite();
			ts.addTest( new TokenTest("testOpToken") );
			ts.addTest( new TokenTest("testNumToken") );
		return ts;
	}	
	
	public final void testOpToken(){
		System.out.println("");
		System.out.println("==========================================");
		System.out.println("testOpToken");
		System.out.println("==========================================");
		WordToken fixture = new MulToken("*");
			assertEquals("testOpToken", fixture.getName(), "*" );	
	}

	public final void testNumToken(){
		System.out.println("");
		System.out.println("==========================================");
		System.out.println("testNumToken");
		System.out.println("==========================================");
		NumToken fixture = new NumToken(12);
			assertTrue("testNumToken", fixture.getVal()==12 );	
	}
	
}
