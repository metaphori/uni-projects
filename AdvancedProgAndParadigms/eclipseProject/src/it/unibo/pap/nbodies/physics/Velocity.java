package it.unibo.pap.nbodies.physics;

import it.unibo.pap.nbodies.KB;

public class Velocity extends Vector2 {

	public Velocity(double x, double y){
		super(x, y);
	}	
	
	public void toNextVelocity(Acceleration a){
		double new_x = getX() + a.getX()*KB.DT;
		double new_y = getY() + a.getY()*KB.DT;
		this.setX(new_x);
		this.setY(new_y);
	}
	
	public Velocity copy(){
		return new Velocity(getX(), getY());
	}
	
}
