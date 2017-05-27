package it.unibo.group08.buttonled.systemTechnoDependent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import it.unibo.baseEnv.basicFrame.EnvFrame;
import it.unibo.buttonLed.interfaces.ILed;
import it.unibo.buttonled.proxy.DevButtonProxy;
import it.unibo.domain.interfaces.IDevInputImpl;
import it.unibo.domain.interfaces.IDevLed;
import it.unibo.group08.buttonled.*;
import it.unibo.group08.buttonled.system.BLSLHighLevelMain;
import it.unibo.is.interfaces.IActivity;
import it.unibo.is.interfaces.IBasicEnvAwt;
import it.unibo.is.interfaces.IBasicUniboEnv;
import it.unibo.is.interfaces.IIntent;
import it.unibo.is.interfaces.IOutputEnvView;
import it.unibo.system.SituatedPlainObject;
/*
 * =======================================================================
 * GOAL: define a first prototype based on mock devices (Button and Led)
 * by using the custom application framework uniboEnvAwt
 * NEXT-STEP: substitute the mock objects with a physical device
 * by exploiting the pattern bridge of GOF
 * =======================================================================
 */
public class PCMain extends BLSLHighLevelMain implements IActivity{

	public PCMain(IBasicEnvAwt env) {
		super(env);
		this.env = env;
		try {
	 		Properties configProperties = new Properties();
			configProperties.load(new FileInputStream("pcConfig.properties"));
			configuration = configProperties.getProperty("pcConfig");		
		} catch (IOException e) {
			//Ignore: we will use the "all GUI" configuration
			this.println("SORRY " + e.getMessage());
 		}
	}
	
	@Override
	protected IDevInputImpl buildConcreteButton() throws Exception {
		return new DevButtonProxy("button", "0.0.0.0", 8055);
	}
	@Override
	protected ILed buildConcreteLed() throws Exception {
		return null;
	}	
	
	
 /*
 * -----------------------------------------
 * MAIN
 * -----------------------------------------
 */
	public static void main(String args[]) throws Exception {
		IBasicEnvAwt env = new EnvFrame("BLSWithGui");
		env.init();
		PCMain system = new PCMain( env );
		
		env.addCmdPanel("Button", new String[]{"switch"}, system);
		system.doJob();
	}

	@Override
	public void execAction(String cmd) {
		DevButtonProxy b = (DevButtonProxy)buttonConcrete;
		if(cmd.equals("switch")){
			if(b.isPressed())
				b.low();
			else
				b.high();
		}
	}

	@Override
	public void execAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execAction(IIntent input) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String execActionWithAnswer(String cmd) {
		// TODO Auto-generated method stub
		return null;
	}
}
