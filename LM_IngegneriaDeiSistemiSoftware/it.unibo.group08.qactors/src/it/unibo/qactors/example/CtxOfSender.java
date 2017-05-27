package it.unibo.qactors.example;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.InputStream;
import it.unibo.baseEnv.basicFrame.EnvFrame;
import it.unibo.is.interfaces.IBasicEnvAwt;
import it.unibo.is.interfaces.IOutputView;
import it.unibo.qactors.ActorContext;

public class CtxOfSender extends ActorContext{
	public CtxOfSender(String name, IOutputView outView, 
			InputStream inputStream, InputStream sysRulesStream ) throws Exception {
		super(name, outView, inputStream, sysRulesStream );
  	}
	/*
	 * The context of the sender includes the sender actor
	 */
 	@Override 	
	public void configure(){ 
		try {
 			new QActorSender("qasender", this, outView);
		} catch (Exception e) {
			println(getName() + " ERROR " + e.getMessage() );
		}
	}	
 	public static void main(String[] args) throws Exception {
		IBasicEnvAwt env = new EnvFrame("ctxOfSenderFrame", null, Color.yellow, Color.blue);
		env.init();
		env.writeOnStatusBar("ctxofsender" + " | working ... ", 14);
		InputStream sysKbStream = new FileInputStream("sysKb.pl");
		InputStream sysRulesStream = new FileInputStream("sysRules.pl");
		new CtxOfSender("ctxofsender", env.getOutputView(), sysKbStream, sysRulesStream ).configure();
	}
}
