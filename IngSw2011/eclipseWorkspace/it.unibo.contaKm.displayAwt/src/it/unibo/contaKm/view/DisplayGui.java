package it.unibo.contaKm.view;

import java.awt.Panel;
import java.awt.TextField;
import it.unibo.is.interfaces.IBasicEnvAwt;
import it.unibo.is.interfaces.IOutputView;

/**
 * Implemantation part of the bridge pattern
 * @author Antonio Natali
 *
 */
public class DisplayGui extends Panel implements IOutputView{
	protected IBasicEnvAwt env;
	protected TextField tarea;
	private String curVal;
	
	public DisplayGui( IBasicEnvAwt env ){
 		tarea = new TextField(12);
		tarea.setFont(new java.awt.Font( "Arial", java.awt.Font.BOLD, 24));
 		add(tarea);
 		env.addPanel(this);
 	}
	
	protected void display(){
		tarea.setText(curVal);
		tarea.validate();
	}

 	@Override
	public String getCurVal() {
 		return curVal;
	}

	@Override
	public void addOutput(String msg) {
		curVal = msg;
		display();
 	}
	
	
}
