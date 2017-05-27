package it.unibo.system;

import it.unibo.is.interfaces.IBasicEnvAwt;

public abstract class SituatedActiveObject extends Thread{
	protected IBasicEnvAwt env;
	protected boolean debug = false;
	
	public SituatedActiveObject( IBasicEnvAwt env, String name ){
		this.env = env;
		setName(name);
	}
	
	public void run(){
		try {
			println("*** " + getName() + " starts ");
			doJob();
			println("*** " + getName() + " ends ");
		} catch (Exception e) {
			println("*** " + getName() + " ERROR " + e.getMessage());
 		}
	}
	
	protected void println(String msg){
		env.println(msg);
	}
	
	protected void showMsg(String msg){
		if( debug ) env.println(msg);
	}
	
	protected abstract void doJob() throws Exception;
	public abstract void startWork() throws Exception;
	public abstract void endWork() throws Exception;
}
