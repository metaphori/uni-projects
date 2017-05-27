package it.unibo.pap.nbodies.gui;

import it.unibo.pap.nbodies.Body;

public class ControllerSequential extends Controller {

	@Override
	public void startGalaxy(){
		/*
		 * SEQUENTIAL ALGORITHM
		 */
		
		new SequentialNBodyTask().start();
						
	}
	
	class SequentialNBodyTask extends Thread {
		@Override
		public void run(){
			int curr_state = 1;
			while(!flags.stopFlag.isSet()){
				for(Body b : bodies.getBodies()){
					b.calculateNetForce(bodies.getBodies());
				}
				for(Body b : bodies.getBodies()){
					b.goToNextState();
				}
				notify_state(curr_state++);
			}			
		}
	}
	
}
