package it.unibo.button.impl.bridge.raspberry;
import it.unibo.buttonled.bridge.impl.DevButtonImpl;
import it.unibo.domain.interfaces.IDevButtonPi4J;
import it.unibo.gpio.base.GpioOnPi4j;
import it.unibo.is.interfaces.IBasicUniboEnv;
import it.unibo.is.interfaces.IOutputEnvView;

import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
   
public class DevButtonGpioPi4J extends DevButtonImpl implements IDevButtonPi4J {
	protected GpioPinDigitalInput myButton;
 	
	public DevButtonGpioPi4J(String name,  com.pi4j.io.gpio.Pin gpioPinNum, IBasicUniboEnv env ) {
		// provision gpio pin #05 as an input pin with its internal pull down resistor enabled
		super(name,env);
		println("DevButtonGpioPi4J CREATION "  );
		myButton = GpioOnPi4j.controller.provisionDigitalInputPin(gpioPinNum, PinPullResistance.PULL_DOWN);		
		println("GpioButtonPi4J register  Pi4jHandler "  );
 		myButton.addListener( new Pi4jHandler( ) );		
	}
 	//Adapter
	protected class Pi4jHandler implements GpioPinListenerDigital{
  		@Override
		public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
			boolean curstate = (""+event.getState()).equals("HIGH");		
 			System.out.println("Pi4jHandler curstate=" + curstate );
			execAction( ""+curstate);		
		}	
	}
}