package it.unibo.pap.nbodies.gui;

import akka.actor.ActorRef;
import akka.actor.PoisonPill;
import akka.actor.Props;
import it.unibo.pap.nbodies.Utils;
import it.unibo.pap.nbodies.concurrent.actors.ActorBodiesConfiguration;
import it.unibo.pap.nbodies.concurrent.actors.UpdateGuiActor;
import it.unibo.pap.nbodies.concurrent.actors.data.StartMsg;

public class ControllerWithActors extends Controller {
	
	private ActorBodiesConfiguration bodyactors;

	@Override
	protected void startGalaxy(){
		/*
		 * APPROACH WITH ACTORS 
		*/
		this.bodyactors = ActorBodiesConfiguration.fromPlainBodiesConfiguration(this.bodies);
		
		this.bodyactors.updateGuiActor =
				this.bodyactors.system.actorOf( Props.create(UpdateGuiActor.class, this.bodyactors, gui, this), "updateGuiActor");
		
		for(ActorRef bodyActor : this.bodyactors.getActors()){
			bodyActor.tell(new StartMsg(), null);
		}
	}
	
	@Override
	public void stopped(){
		if(this.bodyactors!=null){
			this.bodyactors.system.shutdown();
		}
	}
}
