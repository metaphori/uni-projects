package it.unibo.buttonLed.impl.raspberry;
import it.unibo.buttonLed.interfaces.IColor;
import it.unibo.buttonled.impl.Led;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;

import it.unibo.gpio.base.GpioOnPi4j;

public class LedPi4j extends Led  {
protected GpioPinDigitalOutput ledpi4j; 

	public LedPi4j( String name,  IColor LedColor, int pinNum ) throws Exception{
		super(name, LedColor);
		ledpi4j = GpioOnPi4j.controller.provisionDigitalOutputPin( GpioOnPi4j.getPin(pinNum) );
 	}
	@Override
	public void turnOn() {
		ledpi4j.high();
		super.turnOn();
	}
	@Override
	public void turnOff() {
 		ledpi4j.setState(PinState.LOW);
		super.turnOff();
	}
}
