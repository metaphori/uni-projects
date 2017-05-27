package it.unibo.pap.nbodies.physics;

public class MutableVector extends Vector {

	public MutableVector(double... values){
		super(values);
	}
	
	public void setComponent(int i, double val) throws Exception {
		if(i<0 || i>num_dimensions)
			throw new Exception("Invalid dimension index");
		this.components[i] = val;
	}	
	
}
