package it.unibo.buttonled.impl;

import it.unibo.buttonLed.interfaces.IColor;

public class BLSColor implements IColor {
	private String repr;
	
	public BLSColor(String repr){
		this.repr = repr;
	}
	
	@Override
	public String getStringRepr() {
		return this.repr;
	}

}
