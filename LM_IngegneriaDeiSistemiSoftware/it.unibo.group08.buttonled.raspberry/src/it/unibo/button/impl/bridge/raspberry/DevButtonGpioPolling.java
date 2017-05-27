package it.unibo.button.impl.bridge.raspberry;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import it.unibo.buttonled.bridge.impl.DevButtonImpl;
import it.unibo.gpio.base.GpioOnSys;
import it.unibo.gpio.base.IGpioConfig;
import it.unibo.is.interfaces.IBasicUniboEnv;
import it.unibo.is.interfaces.IOutputEnvView;

public class DevButtonGpioPolling extends DevButtonImpl {
public static int maxSim = 10;
protected Vector<String> gpioLikeValues = new Vector<String>();
protected int head = 0;
protected String lastVal = "0";
protected GpioOnSys gpio ;
protected FileWriter fwrLed;
protected FileWriter fwrBuzzer;

	public DevButtonGpioPolling(String name,  IBasicUniboEnv env) {
		super(name,env);
		System.out.println("DevButtonGpioPolling CREATED " );
		gpio = new GpioOnSys();
		try {
			configureGpio();
		} catch (IOException e) {
 			e.printStackTrace();
		}
 	}	
	protected void configureGpio() throws IOException{
		gpio.prepareGpio(IGpioConfig.gpioInButton);
		gpio.openInputDirection(IGpioConfig.gpioInButton);		
		new DevButtonGpioPollingControl().start();
	}
	@Override
	public int getInput() throws Exception {
		String inps = gpio.readGPio( IGpioConfig.gpioInButton );
		if( inps.equals("1")) return 1;
		else return 0;
	}	
 	protected void emit(String msg){
		System.out.println("DevButtonGpioPolling emit -> " + msg  );
		this.setChanged(); //!!!
		this.notifyObservers(msg);		
	}	
	protected class DevButtonGpioPollingControl extends Thread{
		public void run(){
			try {
				for( int i = 1; i<=100; i++) {
					String inps = gpio.readGPio( IGpioConfig.gpioInButton );
					System.out.println("DevButtonGpioPollingControl -> " + inps + "/" + i);
					emit(""+inps.equals("1"));
					java.lang.Thread.sleep(200);
				}
				emit( "0" );
			} catch (Exception e) {
 				e.printStackTrace();
			}
		}
	}
 }