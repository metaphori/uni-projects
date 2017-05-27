package it.unibo.test1;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

import problems.Problem;
import solutions.Solution;

public class CPMP extends Problem {
	private int id;
	private int best_known_solution;
	int n; // num of customers
	int m; // num of medians
	int Q; // capacity of medians
	ArrayList<Customer> customers;
	
	private Random rand = new Random();
	
	
	public CPMP(String fileName) {
		super(fileName);
		customers = new ArrayList<Customer>();
	}
	
	public CPMP(String filepath, int id){
		this(filepath);
		this.id = id;
	}
	
	public int getBestKnownSolution(){ return this.best_known_solution; }
	public void setBestKnownSolution(int best){ this.best_known_solution = best; }

	public int getNumOfCustomers(){ return this.n; }
	public void setNumOfCustomers(int n){ this.n = n; }
	
	public int getNumOfMedians(){ return this.m; }
	public void setNumOfMedians(int m){ this.m = m; }
	
	public int getCapacityOfMedians(){ return this.Q; }
	public void setCapacityOfMedians(int Q){ this.Q = Q; }
	
	public void addCustomer(int cust_id, int x, int y, int req){
		Customer c = new Customer(cust_id, x, y, req);
		customers.add(c);
	}
	
	public ArrayList<Customer> getCustomers(){ return this.customers; }
	
	@Override
	public double evaluate(Solution solution) {
		CPMPSolution sol = (CPMPSolution) solution;
		Hashtable<Customer,Customer> assignments = sol.getAssignments();
		double cost = 0;
		for(Customer client : assignments.keySet()){
			cost += client.distanceTo(assignments.get(client));
		}
		return cost;
	}

	@Override
	public boolean isValid(Solution solution) {
		CPMPSolution sol = (CPMPSolution)solution;
		
		// 1) one client is assigned to one and only one service (guaranteed by impl)
		
		// 2) client demands do not exceed the capacities
		// 3) exactly m services needs to be placed
		
		Hashtable<Customer, Integer> totalRequestsForService = new Hashtable<Customer, Integer>();
		Hashtable<Customer, Customer> assignments = sol.getAssignments();
		int placed = 0;
		
		for(Customer client : assignments.keySet()){
			Customer service = assignments.get(client);
			if(totalRequestsForService.containsKey(service)){
				int curReq = totalRequestsForService.get(service);
				int newReq = client.getDemand() + curReq;
				if(newReq > this.Q) // 2)
					return false;
				totalRequestsForService.put(service, client.getDemand() + curReq);
			} else{
				placed++;
				if(client.getDemand() > this.Q) // 2)
					return false;
				totalRequestsForService.put(service, client.getDemand());
			}
		}
		
		if(placed!=this.m) // 3)
			return false;
		
		return true;
	}

	@Override
	public Solution generateRandomSolution() {
		CPMPSolution sol = new CPMPSolution(this);
		
		ArrayList<Customer> custs = (ArrayList)customers.clone();
		ArrayList<Customer> services = new ArrayList<Customer>();
		int toplace = this.m;
		int toassign  = this.n;
		while(toplace>0){
			int max = custs.size();
			int tobeplaced = rand.nextInt(max);
			Customer newservice = custs.get(tobeplaced);
			services.add(newservice);
			custs.remove(tobeplaced);
			sol.setPlacement(newservice, true);
			toplace--;
		}
		
		custs = (ArrayList)customers.clone();
		while(custs.size()>0){
			int max = services.size();
			Customer service = services.get(rand.nextInt(max));
			Customer client = custs.remove(0);
			sol.assign(client, service);
		}
		
		return sol;
	}

	@Override
	public Solution generateNeighborSolution(Solution solution, int maxDist) {
		CPMPSolution sol = (CPMPSolution)solution.clone();
		
		double eval = this.evaluate(solution);
		
		double eval2 = eval;
		
		//System.out.println("Generating neigh for solution of cost " + eval);
		
		while( Math.abs(eval2-eval) > maxDist ){
		
			Hashtable<Customer,Customer> assignments = sol.getAssignments();
			int size = assignments.size();
			
			Customer c1 = customers.get(rand.nextInt(this.n));
			Customer c2 = null;
			do{
				c2 = customers.get(rand.nextInt(this.n));
			} while(c1==c2);
			
			// now, exchange assignment
			
			Customer s1 = assignments.get(c1);
			Customer s2 = assignments.get(c2);
			
			sol.assign(c1, s2);
			sol.assign(c2, s1);
			
			eval2 = this.evaluate(sol);
		
		}
		
		//System.out.println("Found neighbor of cost " + eval2);
		
		return sol;
	}

	@Override
	public Solution combine(Solution s1, Solution s2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Solution mutate(Solution solution) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
