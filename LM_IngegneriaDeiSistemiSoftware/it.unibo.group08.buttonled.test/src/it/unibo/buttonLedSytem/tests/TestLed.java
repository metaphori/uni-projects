package it.unibo.buttonLedSytem.tests;
import static org.junit.Assert.*;
import it.unibo.buttonLed.interfaces.ILed;
//import it.unibo.buttonLedSystem.BLSSysKb.LedColor;

import it.unibo.buttonled.impl.BLSColor;
import it.unibo.buttonled.impl.LedMock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

public class TestLed { 
protected ILed led;

@Before
	public void setUp() {
		System.out.println(" *** setUp "  );
		try{
			led = new LedMock("led1", new BLSColor("GREEN"));
		}catch(Exception e){
			fail("setUp");
		}
 	}
@After
	public void tearDown() throws Exception{
  		System.out.println(" *** tearDown "  );
	}
@Test
	public  void testCreation(){
		System.out.println("	testCreation ... " );
	 	assertTrue("testCreation", ! led.isOn() );
  	}	
@Test
public  void testTurnOn(){
	System.out.println("	testTurnOn ... " );
	led.turnOn();
 	assertTrue("testTurnOn", led.isOn() );
 }
@Test
public  void testTurnOff(){
	System.out.println("	testTurnOff ... " );
	led.turnOff();
 	assertTrue("testTurnOf", ! led.isOn() );
}
@Test
public  void testSwitch(){
	System.out.println("	testSwitch ... " );
	led.doSwitch();
 	assertTrue("testSwitch",  led.isOn() );
 	led.doSwitch();
 	assertTrue("testSwitch",  ! led.isOn() );
} 
@Test
public  void testRep(){
	System.out.println("	testrep ... " );
	String rep = led.getDefaultRep();
	String color = led.getLedColor().getStringRepr().equals("RED") ? "RED" : "GREEN";
	String repExpected="device("+led.getName() +","+color+",false)";
	System.out.println("rep="+ rep + " repExpected=" + repExpected);
  	assertTrue("testrep", rep.equals(repExpected)  );
} 
}