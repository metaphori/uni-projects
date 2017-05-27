package it.unibo.ccc.domain.impl;

import it.unibo.ccc.system.interfaces.IConvoyVehicleFacade;
import it.unibo.is.interfaces.IBasicEnvAwt;

public class ChiefVehicle extends ConvoyVehicle {

	public ChiefVehicle(IBasicEnvAwt env, IConvoyVehicleFacade convoy){
		super(env, convoy, "Chief Vehicle");
	}
	
}
