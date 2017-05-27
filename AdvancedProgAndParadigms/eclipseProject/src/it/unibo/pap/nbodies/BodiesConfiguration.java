package it.unibo.pap.nbodies;

import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;

/**
 * Container and boundary for a collection of bodies.
 *  It also contains common properties for such a system of bodies.
 */
public class BodiesConfiguration {
	private ArrayList<Body> bodies;
	public CyclicBarrier barrier;

	public BodiesConfiguration(ArrayList<Body> bodies){
		this.bodies = bodies;
	}
	
	public BodiesConfiguration done(){
		this.barrier = new CyclicBarrier(bodies.size());
		return this;
	}	
	
	public ArrayList<Body> getBodies(){
		return bodies;
	}
	
	public int getN(){
		return bodies.size();
	}
}
