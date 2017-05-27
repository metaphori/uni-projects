package it.unibo.ccc.domain.impl;

import it.unibo.ccc.domain.SysKb;
import it.unibo.ccc.domain.interfaces.IVehicle;
import it.unibo.ccc.exceptions.CannotPerformException;
import it.unibo.ccc.exceptions.InvalidArgumentException;

public class Vehicle  implements IVehicle, Runnable {
	protected double speed;
	protected boolean isWorking;
	protected boolean started;
	
	public Vehicle(){
		this.speed = 0;
		this.isWorking = false;
		this.started = false;
	}
	
	public synchronized void setSpeed(double speed) throws InvalidArgumentException {
		if(! SysKb.IsSpeedOk(speed)){ throw new InvalidArgumentException("Invalid speed value"); }
		if( speed == 0){
			doStop();
		}
		else{
			this.speed = speed;
			this.isWorking = true;
		}
	}
	
	public double getSpeed() {
		return this.speed;
	}

	public boolean isWorking() {
		return this.isWorking;
	}

	public synchronized void doStart() throws CannotPerformException{
		if(started) return;
		
		if( isWorking() ){
			started = true;
			exec();
		} else{
			throw new CannotPerformException("The vehicle is not working");
		}
		
	}

	@Override
	public synchronized void doStop() {
		this.isWorking = false;
		this.speed = 0;
		this.started = false;
	}
	
	// template method
	public void exec(){

		Thread t = new Thread(this);
		t.start();
		
	}

	@Override
	public void run() {
		doStop();
	}

}
