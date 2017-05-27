package it.unibo.buttonLedSystemDistributed;
//import it.unibo.buttonLedSystem.BLSSysKb.LedColor;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import it.unibo.baseEnv.basicFrame.EnvFrame;
//import it.unibo.buttonLed.impl.LedPi4j;
import it.unibo.buttonLed.impl.LedWithGui;
import it.unibo.buttonLed.interfaces.ILed;
import it.unibo.buttonLedSystem.BLSSysKb;
import it.unibo.buttonled.devices.SysKbBridge;
import it.unibo.buttonled.impl.BLSColor;
//import it.unibo.gpio.base.IGpioConfig;
import it.unibo.is.interfaces.IBasicEnvAwt;
import it.unibo.is.interfaces.protocols.IConnInteraction;
import it.unibo.supports.FactoryProtocol;
import it.unibo.system.SituatedActiveObject;
import it.unibo.system.SituatedSysKb;

public class DevLedServerConfigurableMain extends SituatedActiveObject{
//DEFINITIONS
public static final int portNum = 8055;
//Vars
protected String configuration = "";
protected IConnInteraction conn; 	
protected ILed ledGreen ;
 
	@Override
	protected void startWork() throws Exception {
		loadConfiguration();
		//Led (concrete)
			if( configuration.equalsIgnoreCase(BLSSysKb.distibutedrMock)){
				IBasicEnvAwt env = new EnvFrame("LedGuiRemote", Color.yellow, Color.black);
				env.init();
				this.outView = env.getOutputView();
				env.writeOnStatusBar("LedSerever", 14);
				ledGreen = new LedWithGui("ledGreen", (IBasicEnvAwt)env , new BLSColor("GREEN"));
			}
			else{
				//ledGreen   = new LedPi4j( "ledRasp",  new BLSColor("GREEN"), IGpioConfig.pinOutLed );  
			}
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
 	protected void loadConfiguration() {
		try {
	 		Properties configProperties = new Properties();
			configProperties.load(new FileInputStream("blsConfig.properties"));
			configuration = configProperties.getProperty("blsysConfig");	
			System.out.println("configuration= " + configuration );
		} catch (IOException e) {
			//Ignore: we will use the "all GUI" configuration
			this.println("SORRY " + e.getMessage());
 		}
 	}
	
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
  		new DevLedServerConfigurableMain().activate(SituatedSysKb.executor4Thread);
 	}

}
