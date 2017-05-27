package it.unibo.ccc.vehicle;

import it.unibo.ccc.domain.SysKb;
import it.unibo.is.interfaces.IActivity;
import it.unibo.is.interfaces.IBasicEnvAwt;
import it.unibo.is.interfaces.IIntent;

public class SetIpControl implements IActivity {
	private  IBasicEnvAwt env;
	
	public SetIpControl(IBasicEnvAwt env){
		this.env = env;
	}
	
	@Override
	public void execAction() {
		// TODO Auto-generated method stub

	}

	@Override
	public void execAction(String cmd) {
 		SysKb.hostName=SysKb.getCmdContent(cmd);
 		env.println("Host IP=" + SysKb.hostName);
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
