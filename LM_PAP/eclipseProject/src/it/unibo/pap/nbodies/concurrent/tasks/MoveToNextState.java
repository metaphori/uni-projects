package it.unibo.pap.nbodies.concurrent.tasks;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.Semaphore;

import it.unibo.pap.nbodies.BodiesConfiguration;
import it.unibo.pap.nbodies.Body;
import it.unibo.pap.nbodies.physics.Force;

public class MoveToNextState extends ForkJoinTask<Void> {
	private Body body;
	private Force force = null;
	private Semaphore completeEvent;
	
	public MoveToNextState(Body body, Force force, Semaphore s){
		this.body = body;
		this.force = force;
		this.completeEvent = s;
	}

	@Override
	protected boolean exec() {
		// move body to the next state
		if(force==null)
			body.goToNextState();
		else
			body.goToNextState(force);
		
		// signal task completion
		this.completeEvent.release();
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
