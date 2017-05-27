package it.unibo.system;

import it.unibo.is.interfaces.IBasicEnvAwt;

public class SituatedPlainObject {
	protected IBasicEnvAwt env;
	protected boolean debug = false;
 	
	public SituatedPlainObject( IBasicEnvAwt env ){
		this.env = env;
		if( System.getProperty("debugOn" ) != null )
			debug = System.getProperty("debugOn" ).equals("set");
	}
	
	protected void println(String msg){
		env.println(msg);
	}
	
	protected void showMsg(String msg){
		if( debug ) env.println(msg);
	}
 	 
}
