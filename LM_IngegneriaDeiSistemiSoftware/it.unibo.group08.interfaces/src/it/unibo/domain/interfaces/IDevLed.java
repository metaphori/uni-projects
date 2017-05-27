package it.unibo.domain.interfaces;
import it.unibo.buttonLed.interfaces.ILed;
import it.unibo.iot.interfaces.IDeviceIot;
/*
* -----------------------------------------------------------
* This is the high-level model of the Led:
* a Iot Device with a LedColor and a internal binary state
* -----------------------------------------------------------
*/
public interface IDevLed  extends  IDeviceIot{	
  	public void doSwitch(); //non-primitive
  	public void turnOn(); // modifier
	public void turnOff(); // modifier
 	public boolean isOn(); //property	
	public void setDevImpl(ILed ledImpl) throws Exception;	//injector 
}
