package it.unibo.ccc.domain.impl;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import it.unibo.ccc.domain.SysKb;
import it.unibo.ccc.domain.SysKb.SpeedCompare;
import it.unibo.ccc.domain.interfaces.IConvoy;
import it.unibo.ccc.domain.interfaces.IConvoyVehicle;
import it.unibo.ccc.domain.interfaces.IConvoyVisitor;
import it.unibo.ccc.exceptions.CannotPerformException;
import it.unibo.ccc.exceptions.InvalidArgumentException;
import it.unibo.ccc.system.interfaces.ICCC;
import it.unibo.system.IObservable;
import it.unibo.system.IObserver;

public class ConvoyCruiseControl implements /*IConvoy,*/ ICCC, IObservable {
	private ArrayList<VehicleEntry> vehicles;
	private double speed;
	private boolean running;
	
	private ArrayList<IObserver> observers;
	
	public ConvoyCruiseControl(){
		vehicles = new ArrayList<VehicleEntry>();
		observers = new ArrayList<IObserver>();
		running = false;
	}

	public void speedNotReceived(int vehicle){
		stopConvoy();
	}	
	
	@Override
	public int registerToConvoy(IConvoyVehicle v) {
		vehicles.add( new VehicleEntry(v));
		return getVehicleId(v);
	}
	
	private int getVehicleId(IConvoyVehicle v){
		for(VehicleEntry ve : vehicles){
			if(  ve.getVehicle() == v )
				return vehicles.indexOf(ve);
		}
		return -1;
	}
	
	public double getConvoySpeed(){
		return speed;
	}

	@Override
	public void startConvoy() throws CannotPerformException {
		long millisdelay = SysKb.GiveMillisDelayFor( getConvoySpeed(), SysKb.DD );  

		for(VehicleEntry v : vehicles){
			try{// lascia distanza sicurezza DD metri
				Thread.sleep( millisdelay );
				// sleep del tempo che occorre a compiere DD metri, 
				// se si va alla velocità [getConvoySpeed]
			} catch(Exception e){}
			v.getVehicle().doStart();
			v.startNotifyTimer();
		}
		
		running = true;
	}

	@Override
	public void stopConvoy() {
		if(!running) return;
		
		running = false;
		
		int last = vehicles.size()-1;
		
		// stop the vehicles from the last to the first
		// 		in order to avoid pile-up crashes
		for(int i=last; i>=0; i--){
			
			vehicles.get(i).getVehicle().doStop();
			//  TODO: should handle the possibility that one vehicle
			// doesn't stop !!!!!!
			
		}
	}

	@Override
	public void setConvoySpeed(double speed) throws InvalidArgumentException {
		for(VehicleEntry v : vehicles){
			v.getVehicle().setSpeed(speed);
			v.startNotifyTimer();
		}
		this.speed = speed;
	}

	@Override
	public void notifySpeed(int vehicle, double speed) {
		
		// speed has been notified, wait for the next notify
		vehicles.get(vehicle).startNotifyTimer();
		
		if( SysKb.CompareSpeed(speed, this.speed) == SpeedCompare.IsLower ){
			try {
				setConvoySpeed(speed);
			} catch(Exception exc){ }
		}
		else if( SysKb.CompareSpeed(speed, this.speed) == SpeedCompare.IsGreater ){
			stopConvoy();
		}
	}

	@Override
	public void notifyStatus(int vehicle, boolean status) {
		//System.out.println("V["+vehicle+"] IS "+status);
		vehicles.get(vehicle).setFlag(status);
		if(status == false && running){
			stopConvoy();
		}
		notifyObservers(vehicle, status);
	}
	

	@Override
	public void addObserver(IObserver ob) {
		observers.add(ob);
	}
		
	public void notifyObservers(int vehicle, boolean status){
		for(IObserver o : observers)
			o.update(this, vehicles.get(vehicle));
	}
	
	/******************************/
	/** VEHICLE ENTRY ON THE LIST */
	/******************************/
	public class VehicleEntry {
		private IConvoyVehicle vehicle;
		private Timer notifyTimer;
		private TimerTask notifyTask;
		private boolean flag;
		
		public VehicleEntry(IConvoyVehicle v){
			this.vehicle = v;
			this.flag = false;
		}
		
		public boolean getFlag(){
			return flag;
		}
		
		public void setFlag(boolean status){
			flag = status;
		}
		
		public IConvoyVehicle getVehicle(){
			return vehicle;
		}
		
		public void startNotifyTimer(){
			if(notifyTask!=null)
				notifyTask.cancel();
			notifyTask = new NotifyTask(vehicle.getID());
			notifyTimer = new Timer();
			notifyTimer.schedule(notifyTask, SysKb.R*SysKb.DT);
		}
		
	}
	
	/******************************************************/
	// When the timer expires, it calls speedNotReceived()
	/******************************************************/
	public class NotifyTask extends TimerTask{
		private int vehicle;
		
		public NotifyTask(int vehicle){
			this.vehicle = vehicle;
		}

		@Override
		public void run() {
			speedNotReceived(vehicle);
		}
		
	}


	
}
