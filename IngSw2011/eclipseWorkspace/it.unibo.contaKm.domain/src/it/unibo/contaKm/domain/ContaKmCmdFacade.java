package it.unibo.contaKm.domain;

import it.unibo.contaKm.domain.IContaKm;
import it.unibo.contaKm.domain.SysKb;
import it.unibo.system.SituatedPlainObject;
import it.unibo.is.interfaces.IActivity;
import it.unibo.is.interfaces.IBasicEnvAwt;
import it.unibo.is.interfaces.IIntent;

public class ContaKmCmdFacade extends SituatedPlainObject implements IActivity{
private IContaKm ckm;

	public ContaKmCmdFacade( IBasicEnvAwt env, IContaKm ckm ){
		super(env);
 		this.ckm = ckm;
	}
	
	public void execAction() {}
	public void execAction(IIntent input) {}
	
	public void execAction(String cmd) {
		String cmdScheme = SysKb.getCmdscheme(cmd);
		String cmdContent = SysKb.getCmdContent(cmd);
 		try {
//		  	println("*** ContaKmCmdFacade " + cmdScheme + " " + cmdContent);
			if( cmdScheme.equals(SysKb.command) && cmdContent.equals(  SysKb.incS) ){
		 				ckm.inc();
			} else if( cmdScheme.equals(SysKb.command) && cmdContent.equals(  SysKb.getValS) ){
				String answer = execActionWithAnswer(  cmd );
				showAnswer(answer);
			}
		 	else println("Sorry, error " + cmdScheme ); 
		} catch (Exception e) {
	}
	}
	
	public String execActionWithAnswer(String cmd) {
		String cmdScheme = SysKb.getCmdscheme(cmd);
		String cmdContent = SysKb.getCmdContent(cmd);
 		try {
//		  	println("*** ContaKmCmdFacade " + cmdScheme + " " + cmdContent);
			if( cmdScheme.equals(SysKb.command) && cmdContent.equals(  SysKb.getValS) ){
		 		int v = ckm.getVal();
		 		return ""+v;
			}
 		} catch (Exception e) {
		}
 		return("Exception: command not found " ); 
	}
	
	protected void showAnswer(String answer){
		println(""+ answer);
	}
}
