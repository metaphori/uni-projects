package it.unibo.ccc.domain.interfaces;

import it.unibo.ccc.exceptions.CannotPerformException;
import it.unibo.ccc.exceptions.InvalidArgumentException;

public interface IVehicle {

	public void setSpeed(double speed) throws InvalidArgumentException;
	
	public double getSpeed();
	
	public boolean isWorking();
	
	public void doStart() throws CannotPerformException;
	
	public void doStop();
	
}
