package it.unibo.button.impl.bridge.raspberry;
import it.unibo.buttonled.bridge.impl.DevButtonImpl;
import it.unibo.domain.interfaces.IDevInputImpl;
import it.unibo.gpio.base.GpioOnPi4j;
import it.unibo.is.interfaces.IBasicUniboEnv;
import it.unibo.is.interfaces.IOutputEnvView;

import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
   
public class DevButtonPi4J extends DevButtonImpl implements IDevInputImpl {
protected GpioPinDigitalInput device;
	public DevButtonPi4J(String name,  com.pi4j.io.gpio.Pin gpioPinNum, IBasicUniboEnv env ) {
 		super(name,env);
		println("DevButtonGpioPi4J CREATION "  );
		device = GpioOnPi4j.controller.provisionDigitalInputPin(gpioPinNum, PinPullResistance.PULL_DOWN);		
		println("DevButtonGpioPi4J register  Pi4jHandler "  );
 		device.addListener( new Pi4jHandler( ) );		
	}
	/*
	 * Pi4jHandler class (adapter) 
	 */
 	protected class Pi4jHandler implements GpioPinListenerDigital{
  		@Override
		public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
			execAction( ""+event.getState() );		
		}	
	}
}