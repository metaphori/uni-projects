package it.unibo.ccc.domain.interfaces;

import it.unibo.ccc.exceptions.CannotPerformException;
import it.unibo.ccc.exceptions.InvalidArgumentException;

public interface IConvoy extends IBasicConvoy {

	public double getConvoySpeed();
	public void setConvoySpeed(double speed) throws InvalidArgumentException;
	
	public void startConvoy()  throws CannotPerformException;
	public void stopConvoy();
	
	// public void accept(IConvoyVisitor cv);
	
}
