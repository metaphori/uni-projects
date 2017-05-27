package it.unibo.system;

public interface IObserver {

	public void update(IObservable o, Object val);
	
}
