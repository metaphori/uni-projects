package it.unibo.domain.interfaces;
import it.unibo.is.interfaces.IObservable;

/*
 * An object that implements IButtonObservable is an
 * active entity that updates all its registered observers
 * each time it is pressed.
 * The entity starts its job as soon as it is created
 * (isRunning return true)
 * and terminates when the stop operation is called
 * (isRunning return false).
 */
public interface IButtonActiveObservable extends IObservable{
	public void start();	//modifier
 	public void stop();	//modifier
 	public boolean isRunning();	//property
}