package it.unibo.qactors.example.buttonandroid;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.InputStream;
import it.unibo.baseEnv.basicFrame.EnvFrame;
import it.unibo.is.interfaces.IBasicEnvAwt;
import it.unibo.is.interfaces.IOutputView;
import it.unibo.qactors.ActorContext;
import it.unibo.supports.FactoryProtocol;
 
public class CtxOfButtonReceiver extends ActorContext{
 
	public CtxOfButtonReceiver(String name, IOutputView outView, 
			InputStream inputStream, InputStream sysRulesStream ) throws Exception {
		super(name, outView, inputStream, sysRulesStream );
	}

	/*
	 * The context of the receiver includes the receiver actor
	 */
 	@Override 	
	public void configure()  {
 		try {
 			new QActorButtonReceiver("qareceiver", this, outView);
 		} catch (Exception e) {
			println(getName() + " ERROR " + e.getMessage() );
		}
	}	
 /*
  * A Main for Java
  */
 	public static void main(String[] args) throws Exception {
		IBasicEnvAwt env = new EnvFrame("ctxOfReceiverFrame", null, Color.cyan, Color.black);
		env.init();
		env.writeOnStatusBar("ctxofreceiver" + " | working ... ", 14);
		InputStream sysKbStream = new FileInputStream("sysButtonAndroidKb.pl");
		InputStream sysRulesStream = new FileInputStream("sysRules.pl");
		new CtxOfButtonReceiver("ctxofreceiver", env.getOutputView(), sysKbStream, sysRulesStream ).configure();
	}
}
