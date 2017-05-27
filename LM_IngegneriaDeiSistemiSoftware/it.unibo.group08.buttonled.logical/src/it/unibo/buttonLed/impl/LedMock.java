package it.unibo.buttonled.impl;

import it.unibo.buttonLed.interfaces.IColor;
import it.unibo.buttonLed.interfaces.ILed;

public class LedMock  implements ILed{
private boolean on = false;
private String name;
protected IColor color;

	public LedMock( String name, IColor  color) throws Exception{
		this.name = "led("+name+")";
		if( color.getStringRepr().equals("RED") || 
				color.getStringRepr().equals("GREEN"))
			this.color=color;
		else throw new Exception("a led can be only RED or GREEN");		
	}
	public LedMock( String defaltRep ) throws Exception{
		throw new Exception("Not yet implemented");
	}
	@Override
	public void doSwitch() {
		 if( on ) turnOff();
		 else turnOn();
	}
	@Override
	public void turnOn() {
		on = true;		
	}
	@Override
	public void turnOff() {
		on = false;		
	}
	@Override
	public IColor getLedColor() {
		return color;
	}
	@Override
	public boolean isOn() {
		return on;
	}
	@Override
 	public String getDefaultRep() { 		
 		String ledLedColor = color.getStringRepr();
   		return "device("+this.name+"," + ledLedColor + ","+ isOn() +")";
 	}
	@Override
	public String getName() {
		return name;
	}
}
