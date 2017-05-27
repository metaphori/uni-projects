package it.unibo.buttonLedSystemDistributed;
//import it.unibo.buttonLedSystem.BLSSysKb.LedColor;

import java.awt.Color;

import it.unibo.baseEnv.basicFrame.EnvFrame;
import it.unibo.buttonLed.impl.LedWithGui;
import it.unibo.buttonLed.interfaces.ILed;
import it.unibo.buttonled.devices.SysKbBridge;
import it.unibo.buttonled.impl.BLSColor;
import it.unibo.is.interfaces.IBasicEnvAwt;
import it.unibo.is.interfaces.protocols.IConnInteraction;
import it.unibo.supports.FactoryProtocol;
import it.unibo.system.SituatedActiveObject;
import it.unibo.system.SituatedSysKb;

public class DevLedServerMain extends SituatedActiveObject{
//DEFINITIONS
public static final int portNum = 8055;
//Vars
protected String configuration = "";
protected IConnInteraction conn; 	
protected ILed ledGreen ;
protected IBasicEnvAwt myEnv; 
	@Override
	protected void startWork() throws Exception {
		createTheEnv();
 		//Led (concrete)
		ledGreen = new LedWithGui("ledGreen",  myEnv , new BLSColor("GREEN"));
		initServer();
	}  	
	protected void createTheEnv() throws Exception{
		myEnv = new EnvFrame("LedGuiRemote", Color.yellow, Color.black);
		env = myEnv;
		env.init();
		this.outView = env.getOutputView();
		myEnv.writeOnStatusBar("LedSerever", 14);
	}
	protected void initServer(){
		//init the server
		try {
			System.setProperty("inputTimeOut","60000");
			FactoryProtocol factoryP = new FactoryProtocol(this.outView, "TCP", "LedServer");
			//waitForAConnection
			println("LedSerever WAITING FOR A CONNECTION on " + portNum);
			conn = factoryP.createServerProtocolSupport( portNum );
		} catch (Exception e) {
			System.out.println("Config ERROR " + e.getMessage());
			//e.printStackTrace();
		}		
	}
	/*
	 * The server waits for a command
	 * and then it executes the command
	 */
	@Override
	protected void doJob() throws Exception {
 		while(true){
			this.println("LedSerever RECEIVING ...");
	 		String cmd = conn.receiveALine();
			this.println("LedSerever RECEIVED: " + cmd);
			execTheCommand(cmd);
 		}
	}	
	protected void execTheCommand(String cmd ){
		if( cmd.equalsIgnoreCase(SysKbBridge.repHigh)) ledGreen.turnOn();
		else if( cmd.equalsIgnoreCase(SysKbBridge.repLow)) ledGreen.turnOff();
		else if( cmd.equalsIgnoreCase(SysKbBridge.doSwitch)) ledGreen.doSwitch();
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
  		new DevLedServerMain().activate(SituatedSysKb.executor4Thread);
 	}

}
