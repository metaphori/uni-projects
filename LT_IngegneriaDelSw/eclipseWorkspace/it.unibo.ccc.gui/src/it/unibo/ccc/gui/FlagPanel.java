package it.unibo.ccc.gui;

import it.unibo.ccc.domain.impl.ConvoyCruiseControl.VehicleEntry;
import it.unibo.system.IObservable;
import it.unibo.system.IObserver;

import java.awt.Color;
import java.awt.Panel;
import java.awt.TextField;
import java.util.Hashtable;

public class FlagPanel extends Panel implements IObserver {
	private Hashtable<Integer, Boolean> ht;
	private Hashtable<Integer, TextField> htf;
	
	public FlagPanel(){
		super();
		ht = new Hashtable<Integer, Boolean>();
		htf = new Hashtable<Integer, TextField>();
	}
	
	public void setFlag(int i, boolean on){
		if(! ht.containsKey(i) ){
			TextField tf = new TextField(""+i, 1);
			tf.setFont(new java.awt.Font( "Arial", java.awt.Font.BOLD, 20));
			this.add(tf);
			htf.put(i, tf);
		}
		htf.get(i).setForeground( on ? Color.GREEN : Color.RED );
		ht.put(i, on);
	}

	@Override
	public void update(IObservable o, Object val) {
		if( val instanceof VehicleEntry){
			VehicleEntry ve = (VehicleEntry) val;
			setFlag( ve.getVehicle().getID() , ve.getFlag());
		}
	}

}
