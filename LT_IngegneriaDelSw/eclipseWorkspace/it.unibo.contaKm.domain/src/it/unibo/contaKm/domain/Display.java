package it.unibo.contaKm.domain;

import it.unibo.system.IObservable;
import it.unibo.system.IObserver;
import it.unibo.system.SituatedPlainObject;
import it.unibo.is.interfaces.IBasicEnvAwt;

public abstract class Display extends SituatedPlainObject implements IObserver, IDisplay{
protected String curVal ;

	public Display (IBasicEnvAwt env){
		super(env);
	}
	
	public void update(String v) {
		curVal = v;
 		display();
	}

	protected abstract void display();
	
	public void update(IObservable arg0, Object v) {
		update( ""+v );
 	}	
}
