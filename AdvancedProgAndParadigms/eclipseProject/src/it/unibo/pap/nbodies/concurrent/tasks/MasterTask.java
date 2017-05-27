package it.unibo.pap.nbodies.concurrent.tasks;

import it.unibo.pap.nbodies.BodiesConfiguration;
import it.unibo.pap.nbodies.Body;
import it.unibo.pap.nbodies.Utils;
import it.unibo.pap.nbodies.gui.Controller;
import it.unibo.pap.nbodies.gui.Flags;
import it.unibo.pap.nbodies.physics.Force;

import java.util.ArrayList;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.Semaphore;

public class MasterTask extends RecursiveAction {
	private BodiesConfiguration bodies;
	private Controller controller;
	private int state_number;
	private Flags flags;
	
	public MasterTask(BodiesConfiguration bodies, Controller controller, Flags flags){
		this.bodies = bodies;
		this.controller = controller;
		this.state_number = 1;
		this.flags = flags;
	}
	
	@Override
	protected void compute() {
		
		// event semaphore to have the tasks signal their completion
		Semaphore s = new Semaphore(0);
		
		// creating the tasks (one for each body)
		CalculateNetForce[] taskset1 = new CalculateNetForce[bodies.getN()];
		MoveToNextState[] taskset2 = new MoveToNextState[bodies.getN()];
		int i=0;
		for(Body b : bodies.getBodies()){
			taskset1[i] = new CalculateNetForce(bodies, b, s);
			taskset2[i++] = new MoveToNextState(b, null, s);
		}
		
		while(!flags.stopFlag.isSet()){			
			// 1) execute a CalculateNetForce task for each body
			invokeAll(taskset1);
			// 2) wait for the calculations to finish -- for synchronization
			try {
				s.acquire(bodies.getN());
			} catch (InterruptedException e) { e.printStackTrace(); }
			
			// 3) execute a MoveToNextState task for each body
			invokeAll(taskset2);
			// 4) wait for the state transitions to complete -- for synchronization
			try {
				s.acquire(bodies.getN());
			} catch (InterruptedException e) { e.printStackTrace(); }
			
			// 5) finally, notify to controller a new state has been reached
			controller.notify_state(state_number++);
			
			// reinitialize the tasks for next run
			for(int j=0; j<bodies.getN(); j++){
				taskset1[j].reinitialize();
				taskset2[j].reinitialize();
			}			
		}
	}

}
