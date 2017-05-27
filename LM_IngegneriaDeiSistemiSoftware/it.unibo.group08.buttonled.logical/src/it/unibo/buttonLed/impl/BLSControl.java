package it.unibo.buttonled.impl;
import java.util.Observable;

import it.unibo.buttonLed.interfaces.IBLSControl;
import it.unibo.buttonLed.interfaces.ILed;
import it.unibo.buttonled.devices.SysKbBridge;
import it.unibo.is.interfaces.IBasicUniboEnv;
import it.unibo.is.interfaces.IOutputView;
import it.unibo.system.SituatedPlainObject;
 
public class BLSControl extends SituatedPlainObject implements IBLSControl{
private ILed led;
private boolean previousInput = false;
private boolean sodd = true;

	public BLSControl(IBasicUniboEnv env, ILed led){
		super(env);
		this.led = led;
	}
	@Override
	public void update(Observable arg0, Object input) {
		//println("BLSControl ishigh= " + input + " class " + input.getClass().getName());
		boolean curInput = false;
		if( input instanceof String )
			curInput =  input.equals( SysKbBridge.repHigh );
		else if( input instanceof Boolean )
			curInput = (Boolean) input;		
 		 if( curInput &&  ! previousInput ){
 	 		if( sodd ) led.turnOn(); 
 	 		else led.turnOff();
 	 		sodd = ! sodd;
 		 }
 		 previousInput = curInput;
 	}//update
 }
