package it.unibo.group08.buttonled.actorsystem.homogeneous;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.InputStream;

import it.unibo.baseEnv.basicFrame.EnvFrame;
import it.unibo.buttonLed.interfaces.ILed;
import it.unibo.buttonled.impl.BLSColor;
import it.unibo.buttonled.impl.LedMock;
import it.unibo.group08.buttonled.arduino.LedArduino;
import it.unibo.is.interfaces.IBasicEnvAwt;
import it.unibo.is.interfaces.IOutputView;
import it.unibo.qactors.ActorContext;

public class CtxOfLed extends ActorContext{
	public CtxOfLed(String name, IOutputView outView, 
			InputStream inputStream, InputStream sysRulesStream ) throws Exception {
		super(name, outView, inputStream, sysRulesStream );
  	}
	/*
	 * The context of the sender includes the sender actor
	 */
 	@Override 	
	public void configure(){ 
		try {
			ILed led = new LedMock("led", new BLSColor("RED"));
			//System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/ttyACM1");
 			//ILed led = new LedArduino("/dev/ttyACM1");
			new QALed(led, "qaled", this, outView);
		} catch (Exception e) {
			println(getName() + " ERROR " + e.getMessage() );
		}
	}	
 	public static void main(String[] args) throws Exception {
		IBasicEnvAwt env = new EnvFrame("ctxofled", null, Color.orange, Color.blue);
		env.init();
		env.writeOnStatusBar("ctxofled" + " | working ... ", 14);
		InputStream sysKbStream = new FileInputStream(SysConfig.Sys);
		InputStream sysRulesStream = new FileInputStream("sysRules.pl");
		new CtxOfLed("ctxofled", env.getOutputView(), sysKbStream, sysRulesStream ).configure();
	}
}
