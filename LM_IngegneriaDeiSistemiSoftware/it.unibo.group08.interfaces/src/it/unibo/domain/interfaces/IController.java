package it.unibo.domain.interfaces;
import it.unibo.is.interfaces.IObserver;

public interface IController extends IObserver{	
	public void doJob(String cmd);
	public String getInfo();
	public void setLedGreen(IDevLed led);
	public void setLedRed(IDevLed led);
 	public void setCounter(ICounterController counterCtrl);
}