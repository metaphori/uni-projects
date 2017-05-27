package it.unibo.pap.nbodies.concurrent.tasks;

import it.unibo.pap.nbodies.BodiesConfiguration;
import it.unibo.pap.nbodies.Body;
import it.unibo.pap.nbodies.physics.Force;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.Semaphore;

public class CalculateNetForce extends ForkJoinTask<Void> {
	private BodiesConfiguration bodies;
	private Body body;
	private Semaphore completeEvent;
	
	public CalculateNetForce(BodiesConfiguration bodies, Body body, Semaphore s){
		this.bodies = bodies;
		this.body = body;
		this.completeEvent = s;
	}
	
	@Override
	protected boolean exec() {
		// calculate the net force for the body
		body.calculateNetForce(bodies.getBodies());
		
		// signal task completion
		completeEvent.release();
		return true;
	}


	@Override
	public Void getRawResult() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	protected void setRawResult(Void value) {
		// TODO Auto-generated method stub
		
	}

}
