package it.unibo.buttonLed.devices;
 
//import it.unibo.button.bridge.impl.DevButtonPi4J;
//import it.unibo.button.bridge.impl.DevButtonGpioPolling;
import it.unibo.button.bridge.impl.DevButtonGui;
import it.unibo.button.bridge.impl.DevButtonStdin;
import it.unibo.button.bridge.impl.DevButtonStdinGui;
import it.unibo.buttonled.devices.DevButton;
import it.unibo.domain.interfaces.IDevButton;
import it.unibo.domain.interfaces.IDevInputImpl;
import it.unibo.is.interfaces.IBasicEnvAwt;
import it.unibo.is.interfaces.IBasicUniboEnv;
import it.unibo.is.interfaces.IOutputEnvView;

public class DevButtonFactory {
//IMPLEMENTATION
	public static IDevInputImpl createButtonGui(String name,IBasicEnvAwt env,String[] cmd){
		return new DevButtonGui( name,env, cmd);
	}
	/*
	public static IDevInputImpl createButtonGpioPolling( String name,IBasicUniboEnv env ){
		return new DevButtonGpioPolling( name, env );
	}
	*/
	/*
	public static IDevInputImpl createButtonPi4j(String name,com.pi4j.io.gpio.Pin gpioPinNum,IBasicUniboEnv env  ){
		// provision gpio pin #05 as an input pin with its internal pull down resistor enabled
		return new DevButtonPi4J( name, gpioPinNum, env  ); 
	}
	*/
 	public static IDevInputImpl createButtonStdin( String name ){
		return new DevButtonStdin(  name );
	}
	public static IDevInputImpl createButtonStdin(String name,IBasicEnvAwt env ){
		return new DevButtonStdinGui( name,env );
	}
//HIGH LEVEL  
	/*
	 * The button is implemented by a GUI
	 */
	public static IDevButton create(String name,IBasicEnvAwt env,String[] cmd){
		IDevInputImpl buttonImpl = createButtonGui(name, env,cmd );
		IDevButton logicalButton = createLogicalButton( name, env, buttonImpl);
		//buttonImpl.addObserver(logicalButton);
		 return logicalButton;
	}
	/*
	 * The button is implemented by the standard input
	 */
	public static IDevButton createStdin(  String name ){
		IDevInputImpl buttonImpl = createButtonStdin(  name  );
		IDevButton logicalButton = createLogicalButton(name, null, buttonImpl);
		//buttonImpl.addObserver(logicalButton);
		return logicalButton;
	}
	public static IDevButton createStdin( String name,IBasicEnvAwt env ){
		IDevInputImpl buttonImpl = createButtonStdin( name,env  );
		IDevButton logicalButton = createLogicalButton( name, env, buttonImpl);
		//buttonImpl.addObserver(logicalButton);
		return logicalButton;
	}
	/*
	 * The button is implemented by a GPIO
	 */
	public static IDevButton createGpioPolling(String name,IBasicUniboEnv env,String[] cmd){
		//IDevInputImpl buttonImpl = createButtonGpioPolling( name,env );
		IDevButton logicalButton = null; //createLogicalButton( name, env, buttonImpl);
		return logicalButton;
	}
	/*
	public static IDevButton createGpioPi4j( String name,com.pi4j.io.gpio.Pin gpioPinNum,IBasicUniboEnv env ){
		IDevInputImpl buttonImpl = createButtonPi4j( name,gpioPinNum,env );//RaspiPin.GPIO_05
		 IDevButton logicalButton = createLogicalButton( name, env, buttonImpl);
		 return logicalButton;
	}	
	*/
//------------------------------------------
	protected static IDevButton createLogicalButton(String name,IBasicUniboEnv env, IDevInputImpl devImpl){
		IDevButton button;
		if( env == null ) button =  new DevButton(name, null );
		else button =  new DevButton(name, env.getOutputView() );
		//Injection of the implementation (observed by button)
		button.setDevImpl(devImpl);
		return button;		
	}
 }
