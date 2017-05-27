package it.unibo.group08.buttonled.actorsystem.homogeneous;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.SwingUtilities;

import it.unibo.baseEnv.basicFrame.EnvFrame;
import it.unibo.is.interfaces.IActivityBase;
import it.unibo.is.interfaces.IBasicEnvAwt;
import it.unibo.is.interfaces.IOutputView;
import it.unibo.qactors.ActorContext;
import it.unibo.qactors.QActor;
import it.unibo.qactors.SenderObject;
 
public class CtxOfButton extends ActorContext{
 
	public QAButton qbutton;
	
	public CtxOfButton(String name, IOutputView outView, 
			InputStream inputStream, InputStream sysRulesStream ) throws Exception {
		super(name, outView, inputStream, sysRulesStream );
	}

	/*
	 * The context of the button includes the button actor
	 */
 	@Override 	
	public void configure()  {
 		try {
 			qbutton = new QAButton("qabutton", this, outView);
 		} catch (Exception e) {
			println(getName() + " ERROR " + e.getMessage() );
		}
	}	
 /*
  * A Main for Java
  */
 	public static void main(String[] args) throws Exception {
		final IBasicEnvAwt env = new EnvFrame("ctxofbutton", null, Color.cyan, Color.black);
		env.init();
		env.writeOnStatusBar("ctxofbutton" + " | working ... ", 14);
		
		InputStream sysKbStream = new FileInputStream(SysConfig.Sys);
		InputStream sysRulesStream = new FileInputStream("sysRules.pl");		
		final CtxOfButton ctx = new CtxOfButton("ctxofbutton", env.getOutputView(), sysKbStream, sysRulesStream );

		env.addCmdPanel("cmdpanel", new String[]{"press"},new IActivityBase(){

			@Override
			public void execAction(String cmd) {
				ctx.qbutton.press();
			}
			
		});
		
		ctx.configure();
	}
}
