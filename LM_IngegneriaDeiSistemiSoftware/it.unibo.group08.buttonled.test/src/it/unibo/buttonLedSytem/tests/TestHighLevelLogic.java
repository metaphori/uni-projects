package it.unibo.buttonLedSytem.tests;
import static org.junit.Assert.*;

import org.junit.*;

import it.unibo.buttonLed.interfaces.IBLSControl;
import it.unibo.buttonLed.interfaces.ILed;
import it.unibo.buttonled.devices.DevButton;
import it.unibo.buttonled.devices.DevLed;
import it.unibo.buttonled.impl.BLSColor;
import it.unibo.buttonled.impl.BLSControl;
import it.unibo.buttonled.impl.ButtonMock;
import it.unibo.buttonled.impl.LedMock;
//import it.unibo.buttonLedSystem.BLSSysKb.LedColor;
import it.unibo.domain.interfaces.IDevButton;
import it.unibo.domain.interfaces.IDevButtonPi4J;
import it.unibo.domain.interfaces.IDevInputImpl;
import it.unibo.domain.interfaces.IDevLed;

public class TestHighLevelLogic { 
	protected IDevButton button ;
	protected IDevLed ledGreen ;
	protected IDevInputImpl buttonConcrete ;
	protected ILed ledGreenConcrete ;
	protected IBLSControl controller;
	protected IDevButtonPi4J basicbuttonPi4j ; 

@Before
	public void setUp() {
		try{
			init();
			configure();
		}catch(Exception e){
			fail("setUp");
		}		
	}
	protected void init() throws Exception{
		initTheConcretedevices();
		button = new DevButton("bt1",null);
		button.setDevImpl(buttonConcrete);
		
		ledGreen = new DevLed("led1", null );
		ledGreen.setDevImpl(ledGreenConcrete);
		//Controller
		controller = new BLSControl(null,ledGreenConcrete); 
	}
	protected void initTheConcretedevices() throws Exception{
	//Button (concrete)
 	   		buttonConcrete 	   =  new ButtonMock("bt1");
	//Led (concrete)
			ledGreenConcrete   = new LedMock("led1", new BLSColor("GREEN"));
	 }
	protected void configure( ){
		button.addObserver(controller);
	}
@After
	public void tearDown() throws Exception{
 	}
@Test
	public  void testCreation(){
		assertTrue("testCreation", ! button.isPressed() );
 	 	assertTrue("testCreation", ! ledGreen.isOn() );
	}	
 	
@Test
	public  void testRep(){
	String ledRep = ledGreen.getDefaultRep();
	String expectedLedRep = "device(led(led1),GREEN,false)";
	String buttonRep = button.getDefaultRep();
 	String expectedButtonRep = "sensor(button(bt1),0)";
 	System.out.println(ledRep);
	System.out.println(buttonRep);
		assertTrue("testRep 1a ", ledRep.equals(expectedLedRep) );
		assertTrue("testRep 1b ", buttonRep.equals(expectedButtonRep) );
	}
}