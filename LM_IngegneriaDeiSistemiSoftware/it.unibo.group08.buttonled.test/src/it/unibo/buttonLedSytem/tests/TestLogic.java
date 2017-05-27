package it.unibo.buttonLedSytem.tests;
import static org.junit.Assert.*;

import org.junit.*;

import it.unibo.buttonLed.interfaces.IBLSControl;
import it.unibo.buttonLed.interfaces.IButton;
import it.unibo.buttonLed.interfaces.ILed;
//import it.unibo.buttonLedSystem.BLSSysKb.LedColor;
import it.unibo.buttonled.impl.BLSColor;
import it.unibo.buttonled.impl.BLSControl;
import it.unibo.buttonled.impl.ButtonMock;
import it.unibo.buttonled.impl.LedMock;

public class TestLogic { 
protected ILed led;
private IButton button;
private IBLSControl buttonObserver;

@Before
	public void setUp() {
		try{
			configure();
		}catch(Exception e){
			fail("setUp");
		}		
	}
	protected void configure() throws Exception{
	/*
	 * SYSTEM INVARIANT: just one Button and one Led (green)
	 * ABSTRACTIUON GAP: it is difficult to express CONSTRAINTS AT SYSTEM LEVEL
	 */
 			led 	= new LedMock("led1", new BLSColor("GREEN"));
			button 	= new ButtonMock("bt1");
			buttonObserver = new BLSControl(null,led);
			button.addObserver(buttonObserver);
 	}
@After
	public void tearDown() throws Exception{
 	}
@Test
	public  void testCreation(){
		assertTrue("testCreation", ! button.isPressed() );
 	 	assertTrue("testCreation", ! led.isOn() );
	}	
@Test
	public  void testWork(){
 	 	button.high();
	 	assertTrue("testWork high 1 ", led.isOn() );
 	 	button.high();
	 	assertTrue("testWork high 2 ", led.isOn() );
	 	button.low();
	 	assertTrue("testWork low 1 ", led.isOn() );
	 	button.low();
	 	assertTrue("testWork low 2 ", led.isOn() );
	 	button.high();
	 	assertTrue("testWork high 3 ", ! led.isOn() );
 	 	button.high();
	 	assertTrue("testWork high 4 ", ! led.isOn() );
	 	button.low();
	 	assertTrue("testWork low 3 ", ! led.isOn() );
	 	button.high();
	 	assertTrue("testWork high 5 ", led.isOn() );
	}	
@Test
	public  void testRep(){
	String ledRep = led.getDefaultRep();
	String expectedLedRep = "device(led(led1),GREEN,false)";
	String buttonRep = button.getDefaultRep();
 	String expectedButtonRep = "sensor(button(bt1),0)";
 	//System.out.println("" + ledRep + " " + buttonRep);
		assertTrue("testRep 1a ", ledRep.equals(expectedLedRep) );
		assertTrue("testRep 1b ", buttonRep.equals(expectedButtonRep) );
		
	 	button.high();
	 	ledRep = led.getDefaultRep();
	 	buttonRep = button.getDefaultRep();
		expectedButtonRep = "sensor(button(bt1),1)";
	 	expectedLedRep    = "device(led(led1),GREEN,true)";
		assertTrue("testRep 2a ", ledRep.equals(expectedLedRep) );
		assertTrue("testRep 2b ", buttonRep.equals(expectedButtonRep) );
	}
}
