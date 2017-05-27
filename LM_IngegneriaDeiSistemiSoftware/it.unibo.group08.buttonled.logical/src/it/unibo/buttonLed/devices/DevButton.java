package it.unibo.buttonled.devices;
import java.util.Observable;
import it.unibo.domain.interfaces.IDevButton;
import it.unibo.domain.interfaces.IDevInputImpl;
import it.unibo.is.interfaces.IOutputView;
import it.unibo.system.SituatedPlainObject;

public class DevButton extends SituatedPlainObject implements IDevButton {    
protected IDevInputImpl  buttonImpl;

public DevButton( String name, IOutputView outView ){ 
	super(name,outView);
}
public void setDevImpl(IDevInputImpl  buttonImpl){
	this.buttonImpl = buttonImpl;
	buttonImpl.addObserver(this);
}
	@Override
	public int getInput() throws Exception {
		return buttonImpl.getInput();
	}
	@Override
	public boolean isPressed()   {
 		try {
			return buttonImpl.getInput() == 1;
		} catch (Exception e) {
 			e.printStackTrace();
 			return false;
		}
	}
	@Override
	public synchronized void update(Observable arg0, Object arg1) {
 		this.setChanged();	//!!!!
		this.notifyObservers( arg1 );
	}
	@Override
	public String getDefaultRep() {
 		return buttonImpl.getDefaultRep();
	}	
}