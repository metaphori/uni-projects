package it.unibo.pap.nbodies.physics;

public class Acceleration extends Vector2 {

	public Acceleration(double x, double y){
		super(x, y);
	}	
	
	public Acceleration copy(){
		return new Acceleration(getX(), getY());
	}

}
