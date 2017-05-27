package it.unibo.buttonLed.interfaces;
//import it.unibo.buttonLedSystem.BLSSysKb.LedColor;
/*
* -----------------------------------------------------------
* This is a first model of the Led:
* a Led is a Device with a color and a internal binary state
* -----------------------------------------------------------
*/
public interface ILed  extends IDevice{	
  	public void turnOn(); 			// modifier
	public void turnOff(); 			// modifier
	public void doSwitch(); 		//non-primitive
	
	public IColor getLedColor();	//property
	public boolean isOn(); 			//property	
}