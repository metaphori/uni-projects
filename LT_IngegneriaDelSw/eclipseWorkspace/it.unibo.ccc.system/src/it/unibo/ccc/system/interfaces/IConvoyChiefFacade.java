package it.unibo.ccc.system.interfaces;

import it.unibo.ccc.exceptions.CannotPerformException;
import it.unibo.ccc.exceptions.InvalidArgumentException;
import it.unibo.exp.interfaces.IExp;

public interface IConvoyChiefFacade {

	public void startConvoy() throws CannotPerformException;
	
	public void stopConvoy();
	
	public void setConvoySpeed(double speed) throws InvalidArgumentException;
	// public void setConvoySpeed(IExp speed) throws InvalidArgumentException;
	
	//public void registerAsChiefToConvoy(IConvoyVehicle chief);
	
}
