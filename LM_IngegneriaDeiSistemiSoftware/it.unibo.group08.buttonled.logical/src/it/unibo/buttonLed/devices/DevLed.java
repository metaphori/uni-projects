package it.unibo.buttonled.devices;
import it.unibo.buttonLed.interfaces.ILed;
import it.unibo.domain.interfaces.IDevLed;
import it.unibo.is.interfaces.IOutputView;
import it.unibo.system.SituatedPlainObject;
 
  
public class DevLed  extends SituatedPlainObject implements IDevLed{
protected ILed  ledImpl;

	public DevLed( String name, IOutputView outView  ){
		super(name,outView);
 	}
	public void setDevImpl( ILed  ledImpl ) throws Exception{
		this.ledImpl = ledImpl;
  	}	
	@Override
	public void turnOn() {
		ledImpl.turnOn();
 	}
	@Override
	public void turnOff() {
		ledImpl.turnOff();
 	}
	@Override
	public boolean isOn() {
 		return ledImpl.isOn();
	}
	@Override
	public void doSwitch() {
		ledImpl.doSwitch();		
	} 	 
	@Override
	public String getDefaultRep() {
 		return ledImpl.getDefaultRep();
	}
 }
