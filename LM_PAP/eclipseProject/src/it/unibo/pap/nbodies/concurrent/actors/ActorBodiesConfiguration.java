package it.unibo.pap.nbodies.concurrent.actors;

import it.unibo.pap.nbodies.BodiesConfiguration;
import it.unibo.pap.nbodies.Body;

import java.util.ArrayList;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class ActorBodiesConfiguration extends BodiesConfiguration {
	private ArrayList<ActorRef> actors;
	public ActorRef updateGuiActor;
	public ActorSystem system;
	
	public ActorBodiesConfiguration(ArrayList<Body> bodies){
		super(bodies);
		
		Config config = ConfigFactory.parseString("akka.log-dead-letters-during-shutdown=false");
		this.system = ActorSystem.create("MySystem", config);
		this.actors = new ArrayList<ActorRef>(bodies.size());
		
		int i=1;
		for(Body b : bodies){
			actors.add( 
					system.actorOf( Props.create(BodyActorFactory.class, b, this), "body"+(i++) )	
			);			
		}
				
	}
	
	public static ActorBodiesConfiguration fromPlainBodiesConfiguration(BodiesConfiguration config){
		return new ActorBodiesConfiguration(config.getBodies());
	}
	
	public ArrayList<ActorRef> getActors(){
		return actors;
	}
	
	
}
