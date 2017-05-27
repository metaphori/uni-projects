package it.unibo.domain.interfaces;
import it.unibo.iot.interfaces.IDeviceIot;
import it.unibo.is.interfaces.IObservable;
import it.unibo.is.interfaces.IObserver;
 
public interface IDevInput extends IDeviceIot, IObserver, IObservable{
	public void setDevImpl(IDevInputImpl  buttonImpl);	//injector
	public int getInput() throws Exception; 
}
