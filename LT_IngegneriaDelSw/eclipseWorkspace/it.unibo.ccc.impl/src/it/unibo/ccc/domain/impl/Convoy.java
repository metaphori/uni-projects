package it.unibo.ccc.domain.impl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import it.unibo.ccc.domain.SysKb;
import it.unibo.ccc.domain.interfaces.IConvoy;
import it.unibo.ccc.domain.interfaces.IConvoyVehicle;
import it.unibo.ccc.domain.interfaces.IConvoyVisitor;
import it.unibo.ccc.exceptions.CannotPerformException;
import it.unibo.ccc.exceptions.InvalidArgumentException;

public class Convoy implements IConvoy {
	
	private ArrayList<IConvoyVehicle> vehicles;
	private double  speed;
	private boolean running;
	
	public Convoy(){
		this.speed = 0;
		this.running = false;
		this.vehicles = new ArrayList<IConvoyVehicle>();
	}
	
	@Override
	public double getConvoySpeed() {
		return this.speed;
	}

	@Override
	public void setConvoySpeed(double speed) throws InvalidArgumentException {
		if(! SysKb.IsSpeedOk(speed) ) throw new InvalidArgumentException("Invalid speed");
		if( speed == 0)
			stopConvoy();
		this.speed = speed;
		for( IConvoyVehicle cv : vehicles )
			cv.setSpeed(speed);
	}

	@Override
	public void startConvoy() throws CannotPerformException {
		if( getConvoySpeed()==0 ) throw new CannotPerformException("Speed must be set.");
		this.running = true;
		for( IConvoyVehicle cv : vehicles )
			cv.doStart();
	}

	@Override
	public void stopConvoy() {
		this.speed = 0;
		this.running = false;
		for( IConvoyVehicle cv : vehicles )
			cv.doStop();
	}

	@Override
	public int addVehicle(IConvoyVehicle vehicle) {
		if( vehicles.add(vehicle) ){
			return vehicles.indexOf(vehicle);
		} else{
			return -1;
		}
	}

	@Override
	public IConvoyVehicle getVehicle(int position) {
		return vehicles.get(position);
	}
	
	/*
	@Override
	public void accept(IConvoyVisitor cv){
		for(IConvoyVehicle veh : vehicles)
			cv.visit(veh);
	}	
	*/
	
}
