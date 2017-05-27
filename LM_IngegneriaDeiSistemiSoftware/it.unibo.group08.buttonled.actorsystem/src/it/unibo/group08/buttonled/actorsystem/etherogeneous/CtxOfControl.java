package it.unibo.group08.buttonled.actorsystem.etherogeneous;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.InputStream;

import it.unibo.baseEnv.basicFrame.EnvFrame;
import it.unibo.is.interfaces.IBasicEnvAwt;
import it.unibo.is.interfaces.IOutputView;
import it.unibo.qactors.ActorContext;

public class CtxOfControl extends ActorContext{
	public CtxOfControl(String name, IOutputView outView, 
			InputStream inputStream, InputStream sysRulesStream ) throws Exception {
		super(name, outView, inputStream, sysRulesStream );
  	}
	/*
	 * The context of the sender includes the sender actor
	 */
 	@Override 	
	public void configure(){ 
		try {
 			new QAControl("qacontrol", this, outView);
		} catch (Exception e) {
			println(getName() + " ERROR " + e.getMessage() );
		}
	}	
 	public static void main(String[] args) throws Exception {
		IBasicEnvAwt env = new EnvFrame("ctxofcontrol", null, Color.yellow, Color.blue);
		env.init();
		env.writeOnStatusBar("ctxofcontrol" + " | working ... ", 14);
		InputStream sysKbStream = new FileInputStream(SysConfig.Sys);
		InputStream sysRulesStream = new FileInputStream("sysRules.pl");
		new CtxOfControl("ctxofcontrol", env.getOutputView(), sysKbStream, sysRulesStream ).configure();
	}
}
