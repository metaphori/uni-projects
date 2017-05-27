package it.unibo.button.impl.bridge.raspberry;
import it.unibo.buttonLed.impl.raspberry.LedPi4j;
import it.unibo.buttonLed.interfaces.IBLSControl;
import it.unibo.buttonLed.interfaces.IColor;
import it.unibo.buttonLed.interfaces.ILed;
import it.unibo.buttonled.devices.SysKbBridge;
import it.unibo.buttonled.impl.BLSColor;
import it.unibo.buttonled.impl.BLSControl;
import it.unibo.gpio.base.IGpioConfig;
import it.unibo.is.interfaces.protocols.IConnInteraction;
import it.unibo.supports.FactoryProtocol;
import it.unibo.system.SituatedActiveObject;
import it.unibo.system.SituatedSysKb;

public class DevButtonRaspServerMain extends SituatedActiveObject{
//DEFINITIONS
public static final int portNum = 8033;
public static final IColor ledColor = new BLSColor("GREEN");

//Vars
protected IConnInteraction conn; 	
protected ILed ledGreen ;
protected IBLSControl controller;

	@Override
	protected void startWork() throws Exception {
		//Led (concrete)
			ledGreen   = new LedPi4j( "ledRasp",  ledColor, IGpioConfig.pinOutLed );  
 		//Controller
		 	controller = new BLSControl(null,ledGreen); 	
		//init the server
		try {
			FactoryProtocol factoryP = new FactoryProtocol(null, "TCP", "LedServer");
			//waitForAConnection
			this.println("WAITING FOR A CONNECTION ...");
			conn = factoryP.createServerProtocolSupport( portNum );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doJob() throws Exception {
 		while(true){
			this.println("RECEIVING ...");
	 		String cmd = conn.receiveALine();
			this.println("RECEIVED: " + cmd);
			execTheCommand(cmd);
 		}
	}	
	protected void execTheCommand(String cmd ){
		if( cmd.equalsIgnoreCase(SysKbBridge.repHigh)) controller.update(null, SysKbBridge.repHigh);
		else controller.update(null, SysKbBridge.repLow);
	}

	@Override
	protected void endWork() throws Exception {
 	}
/*
 * -----------------------------------------
 * MAIN
 * -----------------------------------------
 */
 	public static void main(String[] args) {
  		new DevButtonRaspServerMain().activate(SituatedSysKb.executor4Thread);
 	}

}
