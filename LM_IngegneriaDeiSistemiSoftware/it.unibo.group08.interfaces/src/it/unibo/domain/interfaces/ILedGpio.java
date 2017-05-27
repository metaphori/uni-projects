package it.unibo.domain.interfaces;

import it.unibo.buttonLed.interfaces.IColor;
//import it.unibo.buttonLedSystem.BLSSysKb.LedColor;

public interface ILedGpio {
	public void turnOn(); // modifier
	public void turnOff(); // modifier
	public IColor getLedColor(); // property
	public boolean isOn(); // property
}
