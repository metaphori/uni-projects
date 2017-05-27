/*
 In the context named ctx1 work two actors (instances  of QActor1)
 that print a sequence of message on the same output port.
 
 If the registerActor in the class ActorContext activates the actors with
 the  SituatedSysKb.executorOneThread, then the output of each instance
 is executed as an atomic action.
 
 The configuration is in testKb.pl

context( ctx1, "192.168.43.229", "TCP", "8010" ).
qactor( qa1a, ctx1 ).
qactor( qa1b, ctx1 ).

 */
package it.unibo.qactors.example.schedule;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.InputStream;

import it.unibo.baseEnv.basicFrame.EnvFrame;
import it.unibo.is.interfaces.IBasicEnvAwt;
import it.unibo.is.interfaces.IOutputView;
import it.unibo.qactors.ActorContext;
import it.unibo.system.SituatedSysKb;
 

public class Ctx1 extends ActorContext{
 	
	public Ctx1(String name, IOutputView outView, InputStream inputStream, InputStream sysRulesStream ) throws Exception {
		super(name, outView, inputStream, sysRulesStream );
  	}
 	@Override 	
	public void configure(){ 
		try {
 			new QActor1("qa1a", this, outView);
			new QActor1("qa1b", this, outView);
		} catch (Exception e) {
			println(getName() + " ERROR " + e.getMessage() );
		}
	}	
	public static void main(String[] args) throws Exception {
		IBasicEnvAwt env = new EnvFrame("ctx2", null, Color.cyan, Color.black);
		env.init();
		env.writeOnStatusBar("ctx1" + " | working ... ", 14);
		InputStream sysKbStream    = new FileInputStream("testKb.pl");
		InputStream sysRulesStream = new FileInputStream("sysRules.pl");
		new Ctx1("ctx1", env.getOutputView(), sysKbStream, sysRulesStream ).configure();
	}
}
