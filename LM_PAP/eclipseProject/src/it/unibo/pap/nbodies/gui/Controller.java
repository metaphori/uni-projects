package it.unibo.pap.nbodies.gui;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingUtilities;

import akka.actor.ActorRef;
import akka.actor.Props;

import it.unibo.pap.nbodies.BodiesConfiguration;
import it.unibo.pap.nbodies.Body;
import it.unibo.pap.nbodies.concurrent.activeobjects.BodyTask;
import it.unibo.pap.nbodies.concurrent.actors.ActorBodiesConfiguration;
import it.unibo.pap.nbodies.concurrent.actors.BodyActorFactory;
import it.unibo.pap.nbodies.concurrent.actors.UpdateGuiActor;
import it.unibo.pap.nbodies.concurrent.actors.data.DataMsg;
import it.unibo.pap.nbodies.concurrent.actors.data.StartMsg;
import it.unibo.pap.nbodies.concurrent.tasks.MasterTask;
import it.unibo.pap.nbodies.interfaces.IStateListener;
import it.unibo.pap.nbodies.interfaces.IViewListener;
import it.unibo.pap.nbodies.KB;
import it.unibo.pap.nbodies.Utils;

public class Controller implements IViewListener {

	protected Flags flags;
	protected BodiesConfiguration bodies;
	protected ExecutorService executor;
	protected NBodyGUI gui;
	protected ArrayList<IStateListener> listeners; // for pattern Observer
	protected int num_threads = 0;
	protected boolean num_threads_set = false;
	
	public Controller(){
		this.bodies = null;
		this.gui = null;
		this.flags = null;
		this.listeners = new ArrayList<IStateListener>();
	}
	
	public void setNThreads(int n){
		this.num_threads = n;
		this.num_threads_set = true;
	}
	
	@Override
	public void stopped() {
		if(flags!=null)
			flags.stopFlag.set();
		if(forkjoin!=null){
			forkjoin.shutdown();
		}
	}
	
	@Override
	public void createRandomBodyConfiguration() {
		createRandomBodyConfiguration(0);
	}
	
	public void createRandomBodyConfiguration(int howmany){
		this.stopped();
		this.bodies = Utils.generateRandomBodies(howmany);
		if(gui!=null){
			gui.setBodiesConfig(this.bodies);
			gui.updateCanvas();
		}		
	}

	@Override
	public void createBodyConfiguration(File configFile) {
		this.stopped();
		this.bodies = Utils.generateBodiesFromFile(configFile);
		if(gui!=null){
			gui.setBodiesConfig(this.bodies);
			gui.updateCanvas();
		}
	}

	@Override
	public void started() {
		if(bodies==null) return;
		if(this.flags!=null && !this.flags.stopFlag.isSet()) return;

		int nthreads = Runtime.getRuntime().availableProcessors()+1;
		if(!this.num_threads_set)
			this.num_threads = nthreads;		
		
		this.flags = new Flags();
		
		startGalaxy();
	}
	
	
	private ForkJoinPool forkjoin;
	protected void startGalaxy(){
		/*
		 * APPROACH WITH FUNCTIONAL DECOMPOSITION
		 */
		forkjoin = new ForkJoinPool(num_threads);
		forkjoin.execute(new MasterTask(bodies, this, flags));		
	}
	
	public void setView(NBodyGUI gui){
		this.gui = gui;
	}
	
	public BodiesConfiguration getBodiesConfig(){
		return this.bodies;
	}

	private int current_state;
	public void notify_state(int state_index) {
		if(gui!=null){
				// set status info
				long system_state = bodies.getBodies().get(0).getStateNumber();
				gui.setStatusMessage("N bodies: " + bodies.getN() + "; Simulation state: " + state_index + "; System state: " + system_state);
				
				// request update of the canvas
				try {
					SwingUtilities.invokeAndWait(new NBodyGUI.UpdateCanvasTask(gui));
				} catch (InvocationTargetException | InterruptedException e) {
					e.printStackTrace();
				}
				
		}
		current_state = state_index;
		
		// notify all attached listeners
		for(IStateListener l : listeners){
			l.notify_state(current_state, bodies);
		}
	}

	public void addStateListener(IStateListener listener) {
		listeners.add(listener);
	}

}
