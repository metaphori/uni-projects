package it.unibo.pap.nbodies.physics;

public class Vector2 extends MutableVector {
	
	public Vector2(double x, double y){
		super(x,y);
	}
	
	public double getX(){ 
		double x = 0;
		try{ x = getComponent(0); } catch(Exception e){} 
		return x;
	}
	public double getY(){ 
		double y = 0;
		try{ y = getComponent(1); } catch(Exception e){} 
		return y;
	}
	public void setX(double x){
		try{ setComponent(0, x); } catch(Exception e){} 	
	}
	public void setY(double y){
		try{ setComponent(1, y); } catch(Exception e){} 	
	}
	
	public Vector2 copy(){
		return new Vector2( getX(), getY());
	}

}
