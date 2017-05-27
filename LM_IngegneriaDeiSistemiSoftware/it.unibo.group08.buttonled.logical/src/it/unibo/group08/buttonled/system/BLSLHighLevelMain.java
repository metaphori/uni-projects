package it.unibo.group08.buttonled.system;
import it.unibo.buttonLed.interfaces.*;
import it.unibo.buttonled.devices.DevButton;
import it.unibo.buttonled.devices.DevLed;
import it.unibo.domain.interfaces.IController;
import it.unibo.domain.interfaces.IDevInput;
import it.unibo.domain.interfaces.IDevInputImpl;
import it.unibo.domain.interfaces.IDevButtonPi4J;
import it.unibo.domain.interfaces.IDevLed;
import it.unibo.is.interfaces.IBasicEnvAwt;
import it.unibo.is.interfaces.IBasicUniboEnv;
import it.unibo.is.interfaces.IOutputEnvView;
import it.unibo.system.SituatedPlainObject;
import it.unibo.system.SituatedSysKb;





import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
/*
 * =======================================================================
 * GOAL: define a first prototype based on real devices (Button and Led)
 * in a technology-independent way
 * =======================================================================
 */
public abstract class BLSLHighLevelMain extends SituatedPlainObject{
protected String configuration = "";
protected IDevInput button ;
protected IDevLed ledGreen ;
protected IDevInputImpl buttonConcrete ;
protected ILed ledGreenConcrete ;	 
protected IBLSControl controller;
protected IDevButtonPi4J basicbuttonPi4j ; 
protected String hostName="127.0.0.1"; 
//protected int ledPort = DevLedServerMain.portNum;

public BLSLHighLevelMain(IBasicEnvAwt env) {
	super(env);
	outView = env.getOutputView();
}
 	public void doJob( ) throws Exception{
  		init();
		configure();
		setup();
	}	
 	
 	protected void setup() throws Exception {}

	protected void init() throws Exception{
		configConcreteElements();
		configLogicalElements();
	}
	protected void configLogicalElements() throws Exception{
		button = new DevButton("button",null);
		//button.setDevImpl(buttonConcrete);		
		ledGreen = new DevLed("led",null );
		ledGreen.setDevImpl( ledGreenConcrete );	 
 		controller = new ButtonLedSystemController( env, ledGreen ); 		
	}
	protected void configure( ){
 		button.addObserver(controller);
   	}

	protected void configConcreteElements() throws Exception{ 
		buttonConcrete = buildConcreteButton();
		ledGreenConcrete = buildConcreteLed();
	}
	
	protected abstract IDevInputImpl  buildConcreteButton() throws Exception;
	protected abstract ILed buildConcreteLed() throws Exception;
	
	/*
	public static void main(String args[]) throws Exception {
		IBasicEnvAwt env = new EnvFrame("BLSWithGui");
		env.init();
		env.writeOnStatusBar("BLSLHighLevellMain", 14);
		BLSLHighLevelMain system = new BLSLHighLevelMain( env);
	 	system.doJob( );
	 }*/
	 
}