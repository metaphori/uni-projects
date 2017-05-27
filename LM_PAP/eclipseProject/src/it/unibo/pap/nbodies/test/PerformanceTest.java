package it.unibo.pap.nbodies.test;

import java.util.concurrent.Semaphore;

import it.unibo.pap.nbodies.BodiesConfiguration;
import it.unibo.pap.nbodies.Utils;
import it.unibo.pap.nbodies.gui.Controller;
import it.unibo.pap.nbodies.gui.ControllerSequential;
import it.unibo.pap.nbodies.gui.ControllerWithActors;
import it.unibo.pap.nbodies.interfaces.IStateListener;

public class PerformanceTest {

	public static void main(String[] args) throws Exception{
		
		int msecs = 1000;
		
		Utils.lognl("type,num_threads,num_bodies,time,num_states");
		
		for(int i : new int[]{5,50,200,500,1000} ){ // i = num of bodies
			
			Semaphore end = new Semaphore(0);
			Utils.log("sequential,1,");
			
			// set up the system
			Controller ctrl = new ControllerSequential();
			
			// create a galaxy with i bodies
			ctrl.createRandomBodyConfiguration(i);
			
			// add a listener to keep trace of performance (throughput)
			ctrl.addStateListener(new PerformanceListener(msecs,end));
			
			// wait for the end of the simulation
			ctrl.started();
			end.acquire();
			ctrl.stopped();
			
			
			end = new Semaphore(0);
			Utils.log("actors,x,");
			ctrl = new ControllerWithActors();
			ctrl.createRandomBodyConfiguration(i);
			ctrl.addStateListener(new PerformanceListener(msecs,end));
			ctrl.started();
			end.acquire();
			ctrl.stopped();			
			
			for(int n : new int[]{1,2,3,5,10} ){ // n = num of threads
				
				end = new Semaphore(0);
				//Utils.lognl("****** N bodies="+i+" ---- Concurrent algorithm (N threads=" + n + ") ******");				
				Utils.log("concurrent,"+n+",");
				ctrl = new Controller();
				ctrl.setNThreads(n);
				ctrl.createRandomBodyConfiguration(i);
				ctrl.addStateListener(new PerformanceListener(msecs,end));
				ctrl.started();
				end.acquire();
				ctrl.stopped();
			}
			
		}
		
	}
	
	public static class PerformanceListener implements IStateListener {

		public static int NUM_SAMPLES = 10;
		
		public long start_timestamp;
		public long time_interval;
		public long n_steps;
		private Semaphore end;
		private long old_state_count; 
		
		public PerformanceListener(int time_interval, Semaphore end){
			this.time_interval = time_interval;
			this.end = end;
			this.n_steps = 0;
		}
		
		@Override
		public void notify_state(long state_count, BodiesConfiguration bodies) {
			// at the first notify, start the timer
			if(state_count==1){
				start_timestamp = System.currentTimeMillis();
				n_steps = 1;
			}
			else if(start_timestamp!= 0){
				
				long time = System.currentTimeMillis() - start_timestamp;
				if(time>n_steps*time_interval){	
					n_steps++;
					//Utils.lognl("States after " + time + " msecs: " + state_count + "("+(state_count-old_state_count)+")");
					//Utils.lognl(bodies.getN()+","+time_interval+","+(state_count-1-old_state_count));
					old_state_count = state_count;
				}
				if(n_steps>PerformanceListener.NUM_SAMPLES){
					double states_per_time_unit = (((double)state_count/PerformanceListener.NUM_SAMPLES)/time_interval)*1000;
					Utils.lognl(bodies.getN()+","+time_interval+","+ states_per_time_unit);
					
					// signal the end of the simulations
					this.end.release();
					start_timestamp = 0;
				}
			}
		}
		
	}
	
}
