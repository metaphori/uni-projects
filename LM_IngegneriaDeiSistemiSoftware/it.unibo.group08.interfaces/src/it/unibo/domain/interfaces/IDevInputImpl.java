package it.unibo.domain.interfaces;
import it.unibo.iot.interfaces.IDeviceIot;
import it.unibo.is.interfaces.IObservable;
import it.unibo.is.interfaces.IObserver;

public interface IDevInputImpl extends IDeviceIot, IObserver, IObservable{
	public int getInput() throws Exception; 
}
