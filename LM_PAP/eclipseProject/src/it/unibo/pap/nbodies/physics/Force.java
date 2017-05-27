package it.unibo.pap.nbodies.physics;

import it.unibo.pap.nbodies.KB;

public class Force extends Vector2 {

	public Force(double x, double y) {
		super(x, y);
	}
	
	/**
	 * Returns the force vector that a body at position p1 and mass m1
	 *  generates on a body at position p2 and mass m2
	 */
	public static Force getGravityForce(Position p1, Position p2, double m1, double m2){
		double x_distance = p1.getX() - p2.getX();
		double y_distance = p1.getY() - p2.getY();
		double distance = Math.sqrt(Math.pow(x_distance,2) + Math.pow(y_distance, 2));
		
		// softening param: to avoid exaggerated forces due to proximity
		double softening = distance<KB.SOFT ? 2000 : 1;
		// impact param: inverting the force
		double impact = distance<KB.IMPACT ? -1 : 1;
		// hardening param: to increase forces between far bodies
		double hardening = distance>KB.HARD ? Math.pow(distance,2) : 1;

		double force_module = impact * hardening * KB.G * m1 * m2 / (Math.pow(distance, 3) + Math.pow(softening, 3));
		
		double force_x = force_module * (p1.getX() - p2.getX());
		double force_y= force_module * (p1.getY() - p2.getY());	
		
		return new Force(force_x, force_y);
	}

}
