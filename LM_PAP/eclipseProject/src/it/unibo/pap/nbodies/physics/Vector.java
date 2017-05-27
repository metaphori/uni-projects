package it.unibo.pap.nbodies.physics;

public class Vector {
	protected double[] components;
	protected int num_dimensions;
	
	public Vector(double... values){
		this.components = new double[values.length];
		this.num_dimensions = values.length;
		for(int i=0; i<values.length; i++)
			this.components[i] = values[i];
	}
	
	public double getComponent(int i) throws Exception {
		if(i<0 || i>num_dimensions)
			throw new Exception("Invalid dimension index");
		return this.components[i];
	}
	
	@Override
	public boolean equals(Object obj){
		Vector v = (Vector)obj;
		try{
			for(int i=0; i<num_dimensions;i++){
				if(components[i] != v.getComponent(i))
					return false;
			}
		} catch(Exception exc){ return false; }
		return true;
	}
	
	@Override
	public String toString(){
		String res = "(";
		int i=0;
		for(; i<num_dimensions-1; i++)
			res += Math.round(components[i])+", ";
		res+= Math.round(components[i]) + ")";
		return res;
	}

}
