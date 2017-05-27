package it.unibo.buttonLedSytem.tests;
import static org.junit.Assert.*;
import it.unibo.buttonLed.interfaces.IButton;
import it.unibo.buttonled.impl.ButtonMock;

import org.junit.Before;
import org.junit.Test;

public class TestButton  {  
private IButton button;
@Before
	public void setUp() { 		
		try{
			button = new ButtonMock("button1");
		}catch(Exception e){
			fail("setUp");
		}
   	}
@Test
	public  void testCreation(){
 		assertTrue("	testCreation", ! button.isPressed() );
  	}	
@Test
public  void testPress(){
 	button.high();
 	assertTrue("testPress", button.isPressed() );
	button.high();
	assertTrue("testPress", button.isPressed() );
 }
@Test
public  void testRelease(){
 	button.low();
 	assertTrue("testRelease", ! button.isPressed() );
	button.high();
 	assertTrue("testPress", button.isPressed() );
	button.low();
	assertTrue("testPress",  ! button.isPressed() );
}
@Test
public  void testRep(){
 	String rep = button.getDefaultRep();
 	String repExpected="sensor("+ button.getName() +",0)";
	System.out.println("rep="+ rep + " repExpected=" + repExpected);
  	assertTrue("testrep", rep.equals(repExpected)  );
	button.high();
	rep = button.getDefaultRep();
	repExpected="sensor("+ button.getName() +",1)";
 	assertTrue("testrep", rep.equals(repExpected)  );
}  
@Test
public void testObserver(){
	ButtonObserverNaive buttonObserver = new ButtonObserverNaive();
	button.addObserver(buttonObserver);
	button.high();
	assertTrue("testObserver",  buttonObserver.getCurVal() ==   true );
	button.low();
	assertTrue("testObserver",  buttonObserver.getCurVal() ==   false );
 } 
}