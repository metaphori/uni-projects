package it.unibo.pap.nbodies.gui;

import it.unibo.pap.nbodies.Body;
import it.unibo.pap.nbodies.concurrent.activeobjects.BodyTask;

import java.util.concurrent.Executors;

public class ControllerWithActiveObjects extends Controller {

	@Override
	public void startGalaxy(){		
		/*
		 * APPROACH WITH BODIES as ACTIVE OBJECTS
		 */
		// the problem with this executor is that it does not perform scheduling;
		// i.e. if a task is waiting (e.g. on a barrier), it doesn't execute other tasks
		this.executor = Executors.newFixedThreadPool(this.num_threads);
		
		int id = 1;
		
		for(Body b : bodies.getBodies()){
			BodyTask task = new BodyTask(id++, b, this.bodies, this, this.flags);
			this.executor.execute(task);
		}
		this.executor.shutdown();
		
	}
	
	
}
