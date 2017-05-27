package it.unibo.buttonled.bridge.impl;
import it.unibo.buttonLed.interfaces.IDevice;
import it.unibo.is.interfaces.IOutputView;
import it.unibo.system.SituatedPlainObject;

public abstract class DevImpl extends SituatedPlainObject implements IDevice  {
 
	public DevImpl(String name, IOutputView outView) {
		super( name, outView );
	}
	protected abstract String getValRep();
 	@Override
	public String getDefaultRep() {
 		try {
			return "device("+this.name +"," + getValRep() +")";
		} catch (Exception e) { return "sensor("+this.name+",null)"; }
	}
	@Override
	public String getName() {
 		return name;
	}
 }
