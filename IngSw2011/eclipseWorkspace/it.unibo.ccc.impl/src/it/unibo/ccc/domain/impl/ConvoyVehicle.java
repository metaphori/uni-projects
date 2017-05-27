package it.unibo.ccc.domain.impl;

import java.util.ArrayList;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import it.unibo.ccc.domain.SysKb;
import it.unibo.ccc.domain.interfaces.IConvoyVehicle;
import it.unibo.ccc.exceptions.CannotPerformException;
import it.unibo.ccc.exceptions.InvalidArgumentException;
import it.unibo.ccc.system.interfaces.IConvoyVehicleFacade;
import it.unibo.contaKm.domain.ContaKmWithDisplay;
import it.unibo.contaKm.domain.Display;
import it.unibo.contaKm.domain.IContaKm;
import it.unibo.contaKm.domain.IDisplay;
import it.unibo.contaKm.view.DisplayContaKm;
import it.unibo.contaKm.view.DisplayGui;
import it.unibo.contaKm.view.IDisplayGui;
import it.unibo.is.interfaces.IBasicEnvAwt;
import it.unibo.is.interfaces.IOutputView;
import it.unibo.system.IObservable;
import it.unibo.system.IObserver;
import it.unibo.system.SituatedActiveObject;

public class ConvoyVehicle extends Vehicle implements IConvoyVehicle, Runnable, IObservable {
	private int ID;
	
	private IConvoyVehicleFacade convoy;
	private IBasicEnvAwt env;
	
	private String name;
	private int delay;
	
	IContaKm ckm;
	
	ArrayList<IObserver> observers;
	
	Timer incTimer, notifySpeedTimer;
	TimerTask incTask, notifySpeedTask; 
	
	public ConvoyVehicle(IBasicEnvAwt env, IConvoyVehicleFacade convoy, String name){
		super();
		
		this.convoy = convoy;
		this.ID = convoy.registerToConvoy(this);
		this.env = env;
		this.name = name;
		this.delay = 0;
		
		IOutputView view = new DisplayGui(env);
		IDisplay dis = new DisplayContaKm(env, view);
		ckm = new ContaKmWithDisplay(env, dis);

		observers = new ArrayList<IObserver>();
	}
	
	@Override
	public synchronized void setSpeed(double speed) throws InvalidArgumentException {
		super.setSpeed(speed);

		notifyObservers(); // of the new speed value
		if(!started)
			notifyStatusToCCC();
	}
	
	public void notifyObservers(){
		for(IObserver o : observers){
			o.update((IObservable)this, (Object)speed);
		}
	}

	@Override
	public synchronized void doStop() {
		super.doStop();
		env.println("V"+getID()+"stop");
		notifyStatusToCCC();
	}
	
	// template method
	public void notifyStatusToCCC(){
		convoy.notifyStatus(ID, isWorking());
	}

	// template method
	public void exec(){
		
		env.println("["+ID+"] going to start");

		Thread t = new Thread(this);
		t.start();
	}
	
	public class IncTask extends TimerTask {
		@Override
		public void run() {
			try {
				ckm.inc();
			} catch (Exception e) {	}
		}
	}

	public class NotifySpeedTask extends TimerTask {
		@Override
		public void run() {
			if( isWorking() ){
				convoy.notifySpeed(ID, speed);
			}
		}
	}
	

	@Override
	public void run() {
		env.println("V"+getID()+"run");
		boolean flag = false;
		double myspeed = -2;
		try{
			while( isWorking() ){
				if( !flag ){
					notifySpeedTimer = new Timer();
					notifySpeedTask = new NotifySpeedTask();
					notifySpeedTimer.schedule( notifySpeedTask, SysKb.DT, SysKb.DT);
					flag = true;
				}
				
				if( myspeed!=getSpeed() ){
					myspeed = getSpeed();
					incTimer = new Timer();
					if(incTask!=null)
						incTask.cancel();
					incTask = new IncTask();
					incTimer.schedule(incTask, 
							SysKb.GiveMillisDelay(SysKb.SpeedBaseFormat, getSpeed()) , 
							SysKb.GiveMillisDelay(SysKb.SpeedBaseFormat, getSpeed()) );

					flag = true;
				}
				
			}
		} catch(Exception e){ }
		finally{
			notifySpeedTimer.cancel();
			incTimer.cancel();
		}
	}

	@Override
	public int getID() {
		return this.ID;
	}

	@Override
	public void addObserver(IObserver o) {
		observers.add(o);
	}


}
