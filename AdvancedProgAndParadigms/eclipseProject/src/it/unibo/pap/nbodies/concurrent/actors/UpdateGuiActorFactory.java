package it.unibo.pap.nbodies.concurrent.actors;

import it.unibo.pap.nbodies.BodiesConfiguration;
import it.unibo.pap.nbodies.Body;
import it.unibo.pap.nbodies.gui.Controller;
import it.unibo.pap.nbodies.gui.NBodyGUI;
import akka.actor.Actor;
import akka.actor.IndirectActorProducer;

public class UpdateGuiActorFactory implements IndirectActorProducer {
	  final NBodyGUI gui;
	  final Controller ctrl;
	  final ActorBodiesConfiguration config;
	  
	  public UpdateGuiActorFactory(ActorBodiesConfiguration config, NBodyGUI gui, Controller ctrl) {
	    this.gui = gui;
	    this.config = config;
	    this.ctrl = ctrl;
	  }
	  
	  @Override
	  public Class<? extends Actor> actorClass() {
	    return UpdateGuiActor.class;
	  }
	  
	  @Override
	  public UpdateGuiActor produce() {
	    return new UpdateGuiActor(config, gui, ctrl);
	  }
}
