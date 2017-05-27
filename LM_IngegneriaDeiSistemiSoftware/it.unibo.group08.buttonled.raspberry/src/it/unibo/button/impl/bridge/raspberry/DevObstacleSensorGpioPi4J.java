package it.unibo.button.impl.bridge.raspberry;
import it.unibo.buttonled.bridge.impl.DevButtonImpl;
import it.unibo.gpio.base.GpioOnPi4j;
import it.unibo.is.interfaces.IBasicUniboEnv;
import it.unibo.is.interfaces.IOutputEnvView;

import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
   
public class DevObstacleSensorGpioPi4J extends DevButtonImpl{
	protected GpioPinDigitalInput myButton;
  
	public DevObstacleSensorGpioPi4J( String name,IBasicUniboEnv env) {
		// provision gpio pin #07 as an input pin with its internal pull down resistor enabled
		super( name,env );
		System.out.println("DevObstacleSensorGpioPi4J CREATION "  );
		myButton = GpioOnPi4j.controller.provisionDigitalInputPin(RaspiPin.GPIO_08, PinPullResistance.PULL_DOWN);		
		System.out.println("DevObstacleSensorGpioPi4J register  Pi4jHandler "  );
 		myButton.addListener( new Pi4jHandler( ) );		
	}
  	//Adapter
	protected class Pi4jHandler implements GpioPinListenerDigital{
  		@Override
		public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
 			String curstate = ""+event.getState();  	
 			System.out.println("Pi4jHandler curstate=" + curstate );
			execAction( ""+curstate);		
		}	
	}
}