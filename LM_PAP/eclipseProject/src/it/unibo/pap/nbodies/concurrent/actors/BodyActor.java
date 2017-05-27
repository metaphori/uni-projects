package it.unibo.pap.nbodies.concurrent.actors;

import it.unibo.pap.nbodies.BodiesConfiguration;
import it.unibo.pap.nbodies.Body;
import it.unibo.pap.nbodies.Utils;
import it.unibo.pap.nbodies.concurrent.actors.data.DataMsg;
import it.unibo.pap.nbodies.concurrent.actors.data.NewStateAckMsg;
import it.unibo.pap.nbodies.concurrent.actors.data.ProceedToNewStateMsg;
import it.unibo.pap.nbodies.concurrent.actors.data.StartMsg;
import it.unibo.pap.nbodies.physics.Position;
import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.actor.UntypedActorWithStash;

public class BodyActor extends UntypedActorWithStash {

	static enum BodyStates { IDLE, ACTIVE };
	private BodyStates state;
	private int forces;
	private Body body;
	private ActorBodiesConfiguration bodies;
	private long state_number;

	public BodyActor(Body body, ActorBodiesConfiguration bodies){
		this.body = body;
		this.bodies = bodies;
		this.forces = 0;
		this.state_number = 0;
		this.state = BodyStates.IDLE;
	}
	
	@Override
	public void onReceive(Object msg) throws Exception {
		if( this.state == BodyStates.IDLE && msg instanceof StartMsg ){
			//Utils.lognl(this.getSelf().toString() + " ... started");
			this.state = BodyStates.ACTIVE;
			
			for(ActorRef bodyActor : bodies.getActors()){
				if(bodyActor!=this.getSelf())
					bodyActor.tell(new DataMsg(this.body, state_number), getSelf());
			}			
		}
		else if(this.state == BodyStates.ACTIVE && msg instanceof DataMsg 
				&& ((DataMsg)msg).state_number==state_number ){
			//Utils.lognl(this.getSelf().toString() + " ... got contribute to state " + state_number);
			DataMsg dataMsg = (DataMsg)msg;
			
			this.body.contributeToNetForce(dataMsg.body);
			
			// update force contribute count
			forces++;
			
			// synchronizing and notifying completion of calculation of netforce
			if(forces==bodies.getN()-1){
				//Utils.lognl(this.getSelf().toString() + " ... synchronizing");
				bodies.updateGuiActor.tell(new NewStateAckMsg(), this.getSelf());
			}
			
			unstashAll();// re-inserting msgs in temporary queue into msg queue
		}
		else if(this.state == BodyStates.ACTIVE && msg instanceof ProceedToNewStateMsg){
			// moving to next state
			body.goToNextState();
			this.state_number++;
			
			// resetting the force contributes
			forces = 0;
			
			//Utils.lognl(this.getSelf().toString() + " ... moves to new state("+state_number+")");
			// sending new state information to other bodies
			for(ActorRef bodyActor : bodies.getActors()){
				if(bodyActor!=this.getSelf())
					bodyActor.tell(new DataMsg(this.body, state_number), getSelf());
			}	
			
			unstashAll(); // re-inserting msgs in temporary queue into msg queue
		}
		else{
			stash();
			// stash: enables an actor to temporarily stash away messages that can not
			//   or should not be handled using the actor's current behavior
			
		} 
		
	}

}
