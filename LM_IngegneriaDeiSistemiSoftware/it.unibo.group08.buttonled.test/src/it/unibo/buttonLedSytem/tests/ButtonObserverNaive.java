package it.unibo.buttonLedSytem.tests;
import java.util.Observable;
import it.unibo.is.interfaces.IObserver;


public class ButtonObserverNaive implements IObserver{
private boolean mirror = false;
	@Override
	public void update(Observable arg0, Object arg1) {
		 System.out.println("ButtonObserverNaive update " + arg1);		
		 mirror = (boolean) arg1;
	}

	public boolean getCurVal(){
		return mirror;
	}
}
