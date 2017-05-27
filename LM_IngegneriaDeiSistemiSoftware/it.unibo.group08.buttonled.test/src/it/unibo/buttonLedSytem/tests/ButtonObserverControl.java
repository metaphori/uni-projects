package it.unibo.buttonLedSytem.tests;
import java.util.Observable;
import it.unibo.buttonLed.interfaces.IBLSControl;
import it.unibo.buttonLed.interfaces.ILed;
import it.unibo.is.interfaces.IOutputView;
import it.unibo.system.SituatedPlainObject;
 
public class ButtonObserverControl extends SituatedPlainObject implements IBLSControl{
private ILed led;
private boolean previousInput = false;
private boolean sodd = true;

	public ButtonObserverControl(IOutputView outView,ILed led){
		super(outView);
		this.led = led;
	}
	@Override
	public void update(Observable arg0, Object ishigh) {
		 boolean curInput = (boolean) ishigh;
		 println("ButtonObserverControl curInput= " + curInput);
		 if( curInput &&  ! previousInput ){
 	 		if( sodd ) led.turnOn(); else led.turnOff();
 	 		sodd = ! sodd;
 		 }
		 previousInput = curInput;
 	}//update
 }
