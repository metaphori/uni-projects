package it.unibo.ccc.system.interfaces;

import it.unibo.ccc.domain.interfaces.IConvoyVehicle;

public interface IConvoyVehicleFacade {

	public int registerToConvoy(IConvoyVehicle vehicle);
	
	public void notifySpeed(int vehicle, double speed);
	
	public void notifyStatus(int vehicle, boolean status);	
	
}
