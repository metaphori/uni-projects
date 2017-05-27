package it.unibo.test1;

import java.util.Hashtable;

import problems.Problem;
import solutions.Solution;

public class CPMPSolution extends Solution {
	private CPMP cpmp;
	private Hashtable<Customer, Boolean> placements = new Hashtable<Customer, Boolean>();
	private Hashtable<Customer, Customer> assignments = new Hashtable<Customer, Customer>();
	
	public CPMPSolution(Problem p) {
		super(p);
		CPMP cpmp = (CPMP)p;
		this.cpmp = cpmp;
		for(Customer c : cpmp.getCustomers()){
			placements.put(c, false);
		}
	}
	
	public void setPlacements(Hashtable<Customer, Boolean> placements){
		this.placements = placements;
	}
	public Hashtable<Customer,Customer> getAssignments(){ return this.assignments; }
	public void setAssignments(Hashtable<Customer, Customer> assignments){
		this.assignments = assignments;
	}	
	
	public void setPlacement(Customer c, boolean assigned){
		if(placements.containsKey(c))
			placements.put(c, assigned);
	}
	
	public void assign(Customer client, Customer service){
		assignments.put(client, service);
	}
	
	public void remove(Customer client){
		assignments.remove(client);
	}
	
	public boolean isService(Customer c){
		return placements.get(c);
	}
	

	@Override
	public String toString() {
		String repr = "";
		for( Customer c : placements.keySet()){
			boolean assigned = placements.get(c);
			repr += "<"+c.getId()+"="+ (assigned ? "true" : false)+">";
		}
		return repr;
	}

	@Override
	public Solution clone() {
		CPMPSolution sol = new CPMPSolution(this.cpmp);
		sol.setAssignments((Hashtable)this.assignments.clone());
		sol.setPlacements((Hashtable)this.placements.clone());
		return sol;
	}

}
