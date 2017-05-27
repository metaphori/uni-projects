package it.unibo.buttonLed.interfaces;

public interface IButton extends IDeviceInput{
	public boolean isPressed() ;	//property

	public void high();		//modifier
	public void low();		//modifier
}
