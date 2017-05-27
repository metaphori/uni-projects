package it.unibo.buttonLedSystem;
import it.unibo.baseEnv.basicFrame.EnvFrame;
import it.unibo.button.bridge.impl.DevButtonGui;
import it.unibo.buttonLed.impl.LedWithGui;
import it.unibo.buttonLed.interfaces.IBLSControl;
import it.unibo.buttonLed.interfaces.ILed;
import it.unibo.buttonled.devices.SysKbBridge;
import it.unibo.buttonled.impl.BLSColor;
import it.unibo.buttonled.impl.BLSControl;
import it.unibo.domain.interfaces.IDevInputImpl;
import it.unibo.is.interfaces.IBasicEnvAwt;
import it.unibo.is.interfaces.IBasicUniboEnv;
import it.unibo.is.interfaces.IOutputEnvView;
import it.unibo.system.SituatedPlainObject;
//import it.unibo.buttonLedSystem.BLSSysKb.LedColor;
/*
 * =======================================================================
 * GOAL: define a first prototype based on mock devices (Button and Led)
 * by using the custom application framework uniboEnvAwt
 * NEXT-STEP: substitute the mock objects with a physical device
 * by exploiting the pattern bridge of GOF
 * =======================================================================
 */
public class BLSWithGuiMain extends SituatedPlainObject{
protected ILed ledGreen;
protected IBLSControl controller;
protected IDevInputImpl button;	
	public BLSWithGuiMain(IBasicEnvAwt env) {
		super(env);
	}
	public void doJob() throws Exception {
		System.out.println("BLSWithGuiMain STARTS");
		init();
		configure();
	}
	protected void init() throws Exception {
 	// Button (concrete)
		String[] commands = new String[] { SysKbBridge.repLow,SysKbBridge.repHigh };
		button 	 = new DevButtonGui( "bGui",(IBasicEnvAwt)env, commands);
	// Led (concrete)
		ledGreen = new LedWithGui("ledGreen", (IBasicEnvAwt)env, new BLSColor("GREEN"));
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
		IBasicEnvAwt env = new EnvFrame("BLSWithGui");
		env.init();
		BLSWithGuiMain system = new BLSWithGuiMain( env );
		system.doJob();
	}
}
