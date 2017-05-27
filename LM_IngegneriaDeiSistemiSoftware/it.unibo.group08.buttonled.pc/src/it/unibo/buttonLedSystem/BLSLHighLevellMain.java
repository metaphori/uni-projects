package it.unibo.buttonLedSystem;
import it.unibo.baseEnv.basicFrame.EnvFrame;
//import it.unibo.button.bridge.impl.DevButtonArduino;
//import it.unibo.button.bridge.impl.DevButtonGpioPi4J;
import it.unibo.button.bridge.impl.DevButtonGui;
//import it.unibo.button.bridge.impl.DevButtonRaspServerMain;
//import it.unibo.buttonLed.impl.LedPi4j;
import it.unibo.buttonLed.impl.LedWithGui;
import it.unibo.buttonLed.interfaces.IBLSControl;
import it.unibo.buttonLed.interfaces.ILed;
import it.unibo.domain.interfaces.IController;
import it.unibo.domain.interfaces.IDevInput;
import it.unibo.domain.interfaces.IDevInputImpl;
import it.unibo.domain.interfaces.IDevButtonPi4J;
import it.unibo.domain.interfaces.IDevLed;
//import it.unibo.gpio.base.IGpioConfig;
import it.unibo.is.interfaces.IBasicEnvAwt;
import it.unibo.is.interfaces.IBasicUniboEnv;
import it.unibo.is.interfaces.IOutputEnvView;
import it.unibo.system.SituatedPlainObject;
import it.unibo.system.SituatedSysKb;
//import it.unibo.buttonLedSystem.BLSSysKb.LedColor;
import it.unibo.buttonLedSystemDistributed.DevLedProxyMain;
import it.unibo.buttonLedSystemDistributed.DevLedServerMain;
import it.unibo.buttonled.devices.DevButton;
import it.unibo.buttonled.devices.DevLed;
import it.unibo.buttonled.devices.SysKbBridge;
import it.unibo.buttonled.impl.BLSColor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
/*
 * =======================================================================
 * GOAL: define a first prototype based on real devices (Button and Led)
 * in a technology-independent way
 * =======================================================================
 */
public class BLSLHighLevellMain extends SituatedPlainObject{
protected String configuration = "";
protected IDevInput button ;
protected IDevLed ledGreen ;
protected IDevInputImpl buttonConcrete ;
protected ILed ledGreenConcrete ;	 
protected IBLSControl controller;
protected IDevButtonPi4J basicbuttonPi4j ; 
protected String hostName="127.0.0.1"; 
protected int ledPort = DevLedServerMain.portNum;

public BLSLHighLevellMain(IBasicEnvAwt env) {
	super(env);
	outView = env.getOutputView();
}
 	public void doJob(   ) throws Exception{
 		loadConfiguration();
  		init();
		configure( );
	}	
 	/*
 	 * The configuration is defined in the file blsConfig.properties. See BLSSysKb
   	 */
 	protected void loadConfiguration() {
		try {
	 		Properties configProperties = new Properties();
			configProperties.load(new FileInputStream("blsConfig.properties"));
			configuration = configProperties.getProperty("blsysConfig");		
		} catch (IOException e) {
			//Ignore: we will use the "all GUI" configuration
			this.println("SORRY " + e.getMessage());
 		}
 	}
	protected void init() throws Exception{
		/*
		if( configuration.equalsIgnoreCase(BLSSysKb.raspberry)){
			configAllOnRaspPi4j();
		}else if( configuration.equalsIgnoreCase(BLSSysKb.distibutedrMock)){
			configDistibutedrMock();
 		}else if( configuration.equalsIgnoreCase(BLSSysKb.buttonArduino)){
			configConcreteButtonArduino();
 		}else if( configuration.equalsIgnoreCase(BLSSysKb.ledRasp)){
 			configConcreteLedRaspPi4j();
	 	}else if( configuration.equalsIgnoreCase(BLSSysKb.buttonRasp)){
	 		configConcreteButtonRaspPi4J();
	 	}else if( configuration.equalsIgnoreCase(BLSSysKb.devsRemote)){
	 		configConcreteDevsRemote();
		}
		//mock
		else 
		*/
		configConcreteDevsGui();
		configLogicalElements();
		
	}
	protected void configLogicalElements() throws Exception{
		button = new DevButton("button",null);
		button.setDevImpl(buttonConcrete);		
		ledGreen = new DevLed("led",null );
		ledGreen.setDevImpl( ledGreenConcrete );	 
 		controller = new ButtonLedSystemController( env, ledGreen ); 		
	}
	protected void configure( ){
 		button.addObserver(controller);
   	}
	/*
	 * ------------------------------------------------------------
	 * TODO: introduce a factory
	 * ------------------------------------------------------------
	 */
	protected void configDistibutedrMock() throws Exception{
	  //Button concrete mock
		String[] commands  = new String[] { SysKbBridge.repLow,SysKbBridge.repHigh };
		buttonConcrete 	   = new DevButtonGui( "bGui",(IBasicEnvAwt)env, commands);   		
    //Led concrete proxy-server
 		new DevLedServerMain(   ).activate(SituatedSysKb.executorNumberOfCores);  
 		Thread.sleep(1000);
    	ledGreenConcrete = new DevLedProxyMain("ledPxy",new BLSColor("GREEN"),hostName,ledPort,this.outView);
  	}
	/*
	protected void configAllOnRaspPi4j() throws Exception{
  	//Button concrete on Raspberry
    	buttonConcrete 	   = new DevButtonGpioPi4J( "button", BLSSysKb.buttonPinPi4j, null  );   		
    //Led concrete on Raspberry
 		ledGreenConcrete   = new LedPi4j( "ledRasp", LedColor.GREEN, IGpioConfig.pinOutLed );  
 	}
 	*/
	protected void configConcreteDevsGui() throws Exception{
	//Button concrete on PC (GUI)
		String[] commands  = new String[] { SysKbBridge.repLow,SysKbBridge.repHigh };
		buttonConcrete 	   = new DevButtonGui( "bGui",(IBasicEnvAwt)env, commands);   		
	//Led concrete on PC (GUI)
		ledGreenConcrete   = new LedWithGui("ledGreen",(IBasicEnvAwt)env, new BLSColor("GREEN"));
	}
	/*
	protected void configConcreteButtonArduino() throws Exception{
	//Led concrete on PC (GUI)
    	ledGreenConcrete   = new LedWithGui("ledGreen",(IBasicEnvAwt)env, LedColor.GREEN);  		
	//Button concrete on Arduino. It could take some time ...
     	buttonConcrete 	   = new DevButtonArduino( "button",  null, BLSSysKb.serialPortWindows  );   		
	}
	protected void configConcreteButtonRaspPi4J() throws Exception{
		//TODO
	}
	protected void configConcreteLedRaspPi4j() throws Exception{
	//Led concrete on Raspberry
		ledGreenConcrete = new DevLedProxyMain(
				"ledPxy",DevButtonRaspServerMain.ledColor,"192.168.43.224", DevButtonRaspServerMain.portNum,this.outView);		
	//Button concrete on PC (GUI)
		String[] commands  = new String[] { SysKbBridge.repLow,SysKbBridge.repHigh };
		buttonConcrete 	   = new DevButtonGui( "bGui",(IBasicEnvAwt)env, commands);   		
	}
	protected void configConcreteDevsRemote() throws Exception{
	//Led concrete on Raspberry
		ledGreenConcrete = new DevLedProxyMain(
				"ledPxy",DevButtonRaspServerMain.ledColor,"192.168.43.224", DevButtonRaspServerMain.portNum,this.outView);
	//Button concrete on Arduino. It could take some time ...
 		buttonConcrete  =  new DevButtonArduino( "button",  null, BLSSysKb.serialPortWindows  );    		
	}
	*/
/*
 * -------------------------------------
 * MAIN  
 * -------------------------------------
*/
	public static void main(String args[]) throws Exception {
		IBasicEnvAwt env = new EnvFrame("BLSWithGui");
		env.init();
		env.writeOnStatusBar("BLSLHighLevellMain", 14);
		BLSLHighLevellMain system = new BLSLHighLevellMain( env);
	 	system.doJob( );
	 }
}