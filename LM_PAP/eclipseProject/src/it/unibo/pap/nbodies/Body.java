package it.unibo.pap.nbodies;

import java.util.ArrayList;

import it.unibo.pap.nbodies.physics.Acceleration;
import it.unibo.pap.nbodies.physics.Force;
import it.unibo.pap.nbodies.physics.Position;
import it.unibo.pap.nbodies.physics.Velocity;

/**
 * This class defines a body (or, more precisely, a particle).
 */
public class Body {
	protected double mass;
	protected Position position;
	protected Velocity velocity;
	protected Acceleration acceleration;
	protected Force force;
	protected long state;
	
	public Body(double mass, Position initialPos, Velocity initialVelocity, Acceleration initialAcceleration){
		this.mass = mass;
		// defensive programming: making copies of provided objects
		this.position = initialPos.copy();
		this.velocity = initialVelocity.copy();
		this.acceleration = initialAcceleration.copy();
		this.state = 0;
	}
	
	/***********
	 * GETTERS *
	 ***********/
	
	public double getMass(){ return mass; }
	// NOTE: pay attention at returning objects without copying them
	public Position getPos(){ return position; }
	public Velocity getVelocity(){ return velocity; }
	public Acceleration getAcceleration(){ return acceleration; }
	public long getStateNumber(){ return state; }
	
	/**
	 * It contributes to the net force with the gravitational force due to interaction with @param other (body).
	 */
	public void contributeToNetForce(Body other){
		Force partialForce = Force.getGravityForce(other.getPos(), this.getPos(), other.getMass(), this.getMass());
		if(this.force==null)
			this.force = new Force(0,0);
		this.force.setX( partialForce.getX()+force.getX() );
		this.force.setY( partialForce.getY()+force.getY() );
	}

	/*************
	 * MODIFIERS *
	 *************/	
	
	/**
	 * It calculates the net force associated to this body, given by the gravitational interaction
	 *  with the @param bodies.
	 * NOTE: it's a modifier that updates acceleration, velocity, and position for this body.
	 */
	public Force calculateNetForce(ArrayList<Body> bodies){
		double force_x = 0;
		double force_y = 0;
		
		for(Body b : bodies){
			if(b==this) continue;
			Force newforce = Force.getGravityForce(b.getPos(), this.position, b.getMass(), this.mass);
			force_x += newforce.getX();
			force_y += newforce.getY();		
		}
		this.force = new Force(force_x, force_y);
		return this.force;
	}
	
	public void goToNextState(){
		goToNextState(this.force);
	}
	
	/**
	 * It moves to body to the next state (by updating its acceleration, velocity, and position)
	 *  which results from the provided @param netForce.
	 */
	public void goToNextState(Force netForce){
		//Utils.lognl("Body[m"+mass+"]("+position.getX()+"; "+position.getY()+") subject to force ("+netForce.getX()+ " , " +netForce.getY()+")");
		this.acceleration.setX(netForce.getX()/this.mass);
		this.acceleration.setY(netForce.getY()/this.mass); 
		this.velocity.toNextVelocity(this.acceleration);
		this.position.toNextPosition(this.velocity, this.acceleration);	
		this.state = this.state + 1;
		
		this.force = new Force(0,0); // resetting force
	}
	
	/**
	 * @return a copy of this Body object
	 */
	public Body copy(){
		/*
		Position p = this.position.copy();
		Velocity v = this.velocity.copy();
		Acceleration a = this.acceleration.copy();
		*/
		// NOTE: members can be safely passed as is the Body constructor that makes the copies!
		return new Body(this.mass, this.position, this.velocity, this.acceleration);
	}
	
}
