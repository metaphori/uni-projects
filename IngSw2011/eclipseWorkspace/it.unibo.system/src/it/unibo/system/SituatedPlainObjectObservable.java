package it.unibo.system;

import java.util.ArrayList;
import java.util.Observable;
import it.unibo.is.interfaces.IBasicEnvAwt;

public class SituatedPlainObjectObservable implements IObservable{
	protected IBasicEnvAwt env;
	protected boolean debug = false;
	
	ArrayList<IObserver> observers;
	
	public SituatedPlainObjectObservable( IBasicEnvAwt env ){
		this.env = env;
		observers = new ArrayList<IObserver>();
	}
	
	protected void println(String msg){
		env.println(msg);
	}
	
	/**
	 * 
	 */
	protected void raiseObservableEvent(String msg){
	  	//setChanged();
	  	try{
	  		notifyObservers( msg );
	  	}catch(Exception e){
	  		println("SituatedObservablePlainObject error "  + e);
	  	}
	}
	
	public void notifyObservers(String msg){
		for(IObserver o : observers)
			o.update(this, msg);
	}

	protected void showMsg(String msg){
		if( debug ) env.println(msg);
	}

	@Override
	public void addObserver(IObserver arg0) {
		observers.add(arg0);
	}

}
