package it.unibo.pap.nbodies.physics;

import it.unibo.pap.nbodies.KB;

public class Position extends Vector2 {

	public Position(double x, double y){
		super(x, y);
	}
	
	public void toNextPosition(Velocity v, Acceleration a){
		double new_x = getX() + v.getX()*KB.DT; // + a.getX()*KB.DT*KB.DT/2;
		double new_y = getY() + v.getY()*KB.DT; // + a.getY()*KB.DT*KB.DT/2;
		this.setX(new_x);
		this.setY(new_y);
	}
	
	public double getDistanceTo(Position p){
		return Math.sqrt(Math.pow(p.getX()-getX(), 2) + Math.pow(p.getY()-getY(), 2));
	}
	
	public Position copy(){
		return new Position(getX(), getY());
	}
	
	public Position opposite(){
		return new Position(-getX(), -getY());
	}
	
}
