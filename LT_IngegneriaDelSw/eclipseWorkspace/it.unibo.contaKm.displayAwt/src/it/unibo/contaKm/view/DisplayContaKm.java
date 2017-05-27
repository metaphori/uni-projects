package it.unibo.contaKm.view;
 
import java.util.Observable;

import it.unibo.contaKm.domain.Display;
import it.unibo.contaKm.domain.IDisplay;
import it.unibo.is.interfaces.IBasicEnvAwt;
import it.unibo.is.interfaces.IOutputView;

/*
 * BRIDGE pattern: 
 * DisplayContaKm is the abstraction
 * IOutputView is the implementation interface
 */
public class DisplayContaKm extends Display implements IDisplay{
protected IOutputView view;

	public DisplayContaKm(IBasicEnvAwt env, IOutputView view){
		super(env);
		this.view = view;
 	}
  
	// Template method 
	@Override
	protected void display() {
//		env.getOutputView().addOutput( curVal );
  		view.addOutput( curVal );
	}
	
	
}
