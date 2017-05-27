package it.unibo.qactors.example;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.InputStream;
import it.unibo.baseEnv.basicFrame.EnvFrame;
import it.unibo.is.interfaces.IBasicEnvAwt;
import it.unibo.is.interfaces.IOutputView;
import it.unibo.qactors.ActorContext;
 
public class CtxOfReceiver extends ActorContext{
 
	public CtxOfReceiver(String name, IOutputView outView, 
			InputStream inputStream, InputStream sysRulesStream ) throws Exception {
		super(name, outView, inputStream, sysRulesStream );
	}

	/*
	 * The context of the receiver includes the receiver actor
	 */
 	@Override 	
	public void configure()  {
 		try {
 			new QActorReceiver("qareceiver", this, outView);
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
		InputStream sysKbStream = new FileInputStream("sysKb.pl");
		InputStream sysRulesStream = new FileInputStream("sysRules.pl");
		new CtxOfReceiver("ctxofreceiver", env.getOutputView(), sysKbStream, sysRulesStream ).configure();
	}
}
