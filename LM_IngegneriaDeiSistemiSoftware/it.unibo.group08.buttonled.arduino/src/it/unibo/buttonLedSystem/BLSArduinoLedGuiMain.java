package it.unibo.buttonLedSystem;
import it.unibo.baseEnv.basicFrame.EnvFrame;
import it.unibo.button.bridge.impl.DevButtonArduino;
import it.unibo.buttonLed.impl.BLSControl;
import it.unibo.buttonLed.impl.LedWithGui;
import it.unibo.buttonLed.interfaces.IBLSControl;
import it.unibo.buttonLed.interfaces.ILed;
import it.unibo.domain.interfaces.IDevInputImpl;
import it.unibo.is.interfaces.IBasicEnvAwt;
import it.unibo.is.interfaces.IBasicUniboEnv;
import it.unibo.is.interfaces.IOutputEnvView;
import it.unibo.system.SituatedPlainObject;
import it.unibo.buttonLedSystem.BLSSysKb.LedColor;
/*
 * =======================================================================
 * GOAL: define a first prototype with the LED on a GUI
 * and the button on Arduino
 * =======================================================================
 */
public class BLSArduinoLedGuiMain extends SituatedPlainObject{
protected ILed ledGreen;
protected IBLSControl controller;
protected IDevInputImpl button; 	
	public BLSArduinoLedGuiMain(IBasicEnvAwt env) {
		super(env);
	}
	public void doJob() throws Exception {
		System.out.println("BLSArduinoLedGuiMain STARTS");
		init();
		configure();
	}
	protected void init() throws Exception {
 	// Button (concrete)
		System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/ttyACM2");
		button 	 = new DevButtonArduino( "bArd",env, "/dev/ttyACM2" );
	// Led (concrete)
		ledGreen = new LedWithGui("ledGreen", (IBasicEnvAwt)env, LedColor.GREEN);
 	// Controller
		controller = new BLSControl(env, ledGreen);
	}
 	protected void configure() {
 		button.addObserver(controller);
  	}	
 /*
 * -----------------------------------------
 * MAIN
 * -----------------------------------------
 */
	public static void main(String args[]) throws Exception {
		IBasicEnvAwt env = new EnvFrame("BLSArduinoLedGuiMain");
		env.init();
		BLSArduinoLedGuiMain system = new BLSArduinoLedGuiMain( env );
		system.doJob();
	}
}
