package it.unibo.test1;

public class Customer {
	private int id;
	private int x;
	private int y;
	private int demand;
	
	public Customer(int id, int x, int y, int demand){
		this.id = id;
		this.x = x;
		this.y = y;
		this.demand = demand;
	}
	
	public int getId(){ return this.id; }
	public int getX(){ return this.x; }
	public int getY(){ return this.y; }
	public int getDemand(){ return this.demand; }
	
	public double distanceTo(Customer other){
		int distx = Math.abs(other.getX() - this.getX());
		int disty = Math.abs(other.getY() - this.getY());
		
		double distance = Math.sqrt( Math.pow(distx, 2) + Math.pow(disty, 2) );
		return distance;
	}
	
}
