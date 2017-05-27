package it.unibo.pap.nbodies.concurrent.activeobjects;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import it.unibo.pap.nbodies.BodiesConfiguration;
import it.unibo.pap.nbodies.Body;
import it.unibo.pap.nbodies.Utils;
import it.unibo.pap.nbodies.gui.Controller;
import it.unibo.pap.nbodies.gui.Flag;
import it.unibo.pap.nbodies.gui.Flags;
import it.unibo.pap.nbodies.gui.NBodyGUI;
import it.unibo.pap.nbodies.physics.Force;

/**
 * Recursive action representing the behavior of a body.
 */
public class BodyTask implements Runnable {
	protected Body body;
	protected BodiesConfiguration bodies;
	protected Controller controller = null;
	protected Flags flags;
	protected int id;

	public BodyTask(int id, Body body, BodiesConfiguration bodies, Controller controller, Flags flags){ 
		this.body = body; 
		this.bodies = bodies;
		this.controller = controller;
		this.flags = flags;
		this.id = id;
	}
	
	@Override
	public void run() {
		int state_index = 0;
		
		while(!flags.stopFlag.isSet()){
			//Utils.log("Body#"+this.id+" is at state " + (state_index++));

			Utils.lognl(this.id + "] before calculating net force");
			Force f = body.calculateNetForce(bodies.getBodies());
			try {
				bodies.barrier.await();
			} catch (Exception e) { e.printStackTrace(); return; }
			Utils.lognl(this.id + "] has finished waiting for all to calculate net force");
			body.goToNextState(f);
			Utils.lognl(this.id + "] new state has been set");
			try {
				bodies.barrier.await();
			} catch (Exception e) { e.printStackTrace(); return; }
			
			if(this.id==1)
				controller.notify_state(state_index);
			state_index++;
		}
	}

}
