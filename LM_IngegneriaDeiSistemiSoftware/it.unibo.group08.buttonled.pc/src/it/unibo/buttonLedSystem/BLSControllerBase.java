package it.unibo.buttonLedSystem;
import java.util.Observable;

import it.unibo.buttonLed.interfaces.ILed;
import it.unibo.domain.interfaces.IController;
import it.unibo.domain.interfaces.ICounterController;
import it.unibo.domain.interfaces.IDevLed;
import it.unibo.is.interfaces.IOutputView;
import it.unibo.system.SituatedPlainObject;

public abstract class BLSControllerBase extends SituatedPlainObject
		implements IController {
	protected int nOfEdges = 0;
	protected IDevLed ledGreen;
	protected IDevLed ledRed;
	protected ICounterController counterCtrl;

	public BLSControllerBase(IOutputView outView) {
		super(outView);
	}
	@Override
	public void setLedGreen(IDevLed led) {
		this.ledGreen = led;
	}
	@Override
	public void setLedRed(IDevLed led) {
		this.ledRed = led;
	}
	@Override
	public void setCounter(ICounterController counterCtrl) {
		this.counterCtrl = counterCtrl;
	}
	@Override
	public void update(Observable arg0, Object isOn) {
		nOfEdges++;
		doJob("" + isOn);  
	}
	@Override
	public String getInfo() {
		return "" + nOfEdges;
	}
}
