package it.unibo.pap.nbodies.test;

import java.util.concurrent.Semaphore;

import it.unibo.pap.nbodies.BodiesConfiguration;
import it.unibo.pap.nbodies.Body;
import it.unibo.pap.nbodies.Utils;
import it.unibo.pap.nbodies.gui.Controller;
import it.unibo.pap.nbodies.gui.ControllerSequential;
import it.unibo.pap.nbodies.gui.ControllerWithActors;
import it.unibo.pap.nbodies.interfaces.IStateListener;

public class CorrectnessTest {

	public static void main(String[] args) throws Exception{

			int msecs = 3000;
			int nbodies = 100;
		
			// set up the system
			Semaphore end = new Semaphore(0);
			Controller ctrl = new Controller();
			
			// create a galaxy
			ctrl.createRandomBodyConfiguration(nbodies);
			
			// set up a listener to make checks
			CorrectnessListener cl = new CorrectnessListener(msecs,end);
			ctrl.addStateListener(cl);
			
			// start the system
			ctrl.started();
			
			// wait for the end of the simulation
			end.acquire();
			ctrl.stopped();
			
			if(cl.correct)
				Utils.lognl("CORRECT");
			else
				Utils.lognl("INCORRECT");
		
	}
	
	public static class CorrectnessListener implements IStateListener{
		public long time_interval;
		private Semaphore end;
		private long start_timestamp;
		public boolean correct;
		
		public CorrectnessListener(int time_interval, Semaphore end){
			this.time_interval = time_interval;
			this.end = end;
			this.correct = true;
		}
		
		@Override
		public void notify_state(long state_count, BodiesConfiguration bodies) {
			if(state_count==1){
				start_timestamp = System.currentTimeMillis();
			}
			else if(start_timestamp!=0){
				
				/* check for correctness: all the bodies must be at the same state count */
				long state = -1;
				for(Body b : bodies.getBodies()){
					if(state<0)
						state = b.getStateNumber();
					else{
						if(b.getStateNumber()!=state)
							correct = false;
					}
				}
				
				long time = System.currentTimeMillis() - start_timestamp;
				if(time>time_interval){	
					end.release();
				}

			}
		}
		
	}
	
}