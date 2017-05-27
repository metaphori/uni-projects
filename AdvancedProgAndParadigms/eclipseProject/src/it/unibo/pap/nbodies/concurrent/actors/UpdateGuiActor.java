package it.unibo.pap.nbodies.concurrent.actors;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import it.unibo.pap.nbodies.BodiesConfiguration;
import it.unibo.pap.nbodies.Utils;
import it.unibo.pap.nbodies.concurrent.actors.data.NewStateAckMsg;
import it.unibo.pap.nbodies.concurrent.actors.data.ProceedToNewStateMsg;
import it.unibo.pap.nbodies.concurrent.actors.data.StartMsg;
import it.unibo.pap.nbodies.gui.Controller;
import it.unibo.pap.nbodies.gui.NBodyGUI;
import akka.actor.ActorRef;
import akka.actor.UntypedActor;

public class UpdateGuiActor extends UntypedActor {
	private final int num;
	private int num_acks;
	private ActorBodiesConfiguration config;
	private NBodyGUI gui;
	private Controller ctrl;
	private long state_count;
	
	public UpdateGuiActor(ActorBodiesConfiguration config, NBodyGUI gui, Controller ctrl){ 
		this.config = config;
		this.gui = gui;
		this.num = config.getN(); 
		this.num_acks = 0;
		this.ctrl = ctrl;
		this.state_count = 1;
	}
	
	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg instanceof NewStateAckMsg){
			this.num_acks++;
			//Utils.lognl(this.self() + "... Ack " + this.num_acks + "/" + this.num);
		} else{
			unhandled(msg);
		}
		
		if(this.num_acks==this.num){
			//Utils.lognl(this.self() + "... Going to update the GUI");
			this.num_acks = 0;
			ctrl.notify_state((int)this.state_count++);
			
			if(gui!=null){
				try {
					SwingUtilities.invokeAndWait(new NBodyGUI.UpdateCanvasTask(gui));
				} catch (InvocationTargetException | InterruptedException e) {
					e.printStackTrace();
				}			
			}

			for(ActorRef a : config.getActors()){
				a.tell( new ProceedToNewStateMsg(), this.getSelf());
			}
		}
	}

}
