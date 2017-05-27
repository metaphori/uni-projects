package it.unibo.gpio.base;
import com.pi4j.io.gpio.GpioController;

public interface IGpioPi4j {
	public  final String GPIO_OUT = "out";
	public  final String GPIO_IN = "in";
	public  final String GPIO_ON = "1";
	public  final String GPIO_OFF = "0";
	public GpioController getGpioPi4j();
}
