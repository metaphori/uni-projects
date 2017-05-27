package it.unibo.group08.buttonled.systemTechnoDependent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import it.unibo.baseEnv.basicFrame.EnvFrame;
import it.unibo.buttonLed.interfaces.IBLSControl;
import it.unibo.buttonLed.interfaces.IButton;
import it.unibo.buttonLed.interfaces.ILed;
import it.unibo.buttonLedSystem.ButtonLedSystemController;
import it.unibo.buttonled.devices.DevButton;
import it.unibo.buttonled.devices.DevLed;
import it.unibo.buttonled.impl.BLSColor;
import it.unibo.buttonled.impl.ButtonMock;
import it.unibo.buttonled.impl.LedMock;
import it.unibo.buttonled.proxy.DevButtonProxyServer;
import it.unibo.buttonled.proxy.LedProxyClient;
import it.unibo.buttonled.proxy.LedProxyServer;
import it.unibo.domain.interfaces.IDevButton;
import it.unibo.domain.interfaces.IDevInputImpl;
import it.unibo.domain.interfaces.IDevLed;
import it.unibo.group08.buttonled.arduino.LedArduino;
import it.unibo.is.interfaces.IBasicEnvAwt;
import it.unibo.is.interfaces.IBasicUniboEnv;
import it.unibo.is.interfaces.IOutputEnvView;
import it.unibo.is.interfaces.IOutputView;
/*
 * =======================================================================
 * GOAL: define a first prototype based on mock devices (Button and Led)
 * by using the custom application framework uniboEnvAwt
 * NEXT-STEP: substitute the mock objects with a physical device
 * by exploiting the pattern bridge of GOF
 * =======================================================================
 */
public class RaspberryMain {

	protected IButton button;
	protected IDevLed ledGreen;
	protected IBLSControl controller;
	protected IBasicEnvAwt env;
	protected ILed ledGreenConcrete;
	
	public RaspberryMain(IBasicEnvAwt env){
		this.env = env;
	}
	
	
	 	public void start( ) throws Exception{
	  		init();
			configure();
		}	
	 	
		protected void init() throws Exception{
			configConcreteElements();
			configLogicalElements();
		}
		protected void configLogicalElements() throws Exception{
			button = new ButtonMock("button");
			//button.setDevImpl(buttonConcrete);		
			ledGreen = new DevLed("led", env.getOutputView() );
			ledGreen.setDevImpl( ledGreenConcrete );	 
	 		controller = new ButtonLedSystemController( env, ledGreen ); 		
		}
		protected void configure( ) throws Exception{
	 		button.addObserver(controller);
			new DevButtonProxyServer(button, 8055, env).activate(new ScheduledThreadPoolExecutor(1));;
			//ledGreen.turnOn();
	   	}

		protected void configConcreteElements() throws Exception{ 
			// buttonConcrete = new Button
			
			System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/ttyACM2");
			
			/*
			new LedProxyServer(new LedArduino("/dev/ttyACM2"), 8057, new EnvFrame() ).activate(new ScheduledThreadPoolExecutor(1));;
			
			ledGreenConcrete = new LedProxyClient("ledGreen", 
					new BLSColor("GREEN"), 
					"0.0.0.0", 8057, new SimpleView());	
			*/
			
			ledGreenConcrete = new LedArduino("/dev/ttyACM2");
		}
		
		public class SimpleView implements IOutputView {

			@Override
			public String getCurVal() {
				return "";
			}

			@Override
			public void addOutput(String msg) {
				System.out.println(msg);
			}

			@Override
			public void setOutput(String msg) {
				addOutput(msg);
			}
			
		}
		
	
 /*
 * -----------------------------------------
 * MAIN
 * -----------------------------------------
 */
	public static void main(String args[]) throws Exception {
		EnvFrame env = new EnvFrame("BLSWithGui");
		env.init();
		RaspberryMain system = new RaspberryMain( env );
		system.start();
	}
}
