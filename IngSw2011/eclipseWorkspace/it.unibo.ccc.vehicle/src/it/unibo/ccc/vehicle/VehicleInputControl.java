package it.unibo.ccc.vehicle;

import it.unibo.ccc.domain.SysKb;
import it.unibo.ccc.domain.interfaces.IConvoyVehicle;
import it.unibo.is.interfaces.IActivity;
import it.unibo.is.interfaces.IBasicEnvAwt;
import it.unibo.is.interfaces.IIntent;

public class VehicleInputControl implements IActivity {
	IConvoyVehicle v;
	IBasicEnvAwt env;
	
	public VehicleInputControl(IBasicEnvAwt env, IConvoyVehicle v){
		this.v = v;
		this.env = env;
	}
	
	
	@Override
	public void execAction() {
	}

	@Override
	public void execAction(String cmd) {
		String scheme = SysKb.getCmdScheme(cmd);
		String content = SysKb.getCmdContent(cmd);
		
		if( scheme.equals(SysKb.command)){
			try{
				if( content.equals(SysKb.incSpeed)){
					v.setSpeed( v.getSpeed()+1 );
				} else if( content.equals(SysKb.decSpeed)){
					v.setSpeed( v.getSpeed()-1 );
				}
				else if(content.equals(SysKb.stop)){
					v.doStop();
				}
			} catch(Exception exc){ env.println(exc.getMessage() ); }
		}
	}

	@Override
	public void execAction(IIntent input) {
	}

	@Override
	public String execActionWithAnswer(String cmd) {
		return null;
	}

}
