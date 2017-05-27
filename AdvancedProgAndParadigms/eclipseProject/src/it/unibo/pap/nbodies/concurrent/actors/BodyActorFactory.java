package it.unibo.pap.nbodies.concurrent.actors;

import it.unibo.pap.nbodies.BodiesConfiguration;
import it.unibo.pap.nbodies.Body;
import akka.actor.Actor;
import akka.actor.IndirectActorProducer;

public class BodyActorFactory implements IndirectActorProducer {
	  final Body body;
	  final ActorBodiesConfiguration bodies;
	  
	  public BodyActorFactory(Body body, ActorBodiesConfiguration bodies) {
	    this.body = body;
	    this.bodies = bodies;
	  }
	  
	  @Override
	  public Class<? extends Actor> actorClass() {
	    return BodyActor.class;
	  }
	  
	  @Override
	  public BodyActor produce() {
	    return new BodyActor(body, bodies);
	  }
}
