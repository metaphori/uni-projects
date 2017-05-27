package it.unibo.test1;
import it.unibo.test1.GUI.Canvas;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import problems.Problem;
import solutions.Solution;


public class TravelSalesmanProblem extends Problem {
	
	private ArrayList<Node> nodes;
	private int numnodes = 0;
	
	public TravelSalesmanProblem(String fileName) {
		super(fileName);
	}

	public void setNodes(ArrayList<Node> nodes){
		this.nodes = nodes;
		this.numnodes = nodes.size();
	}
	
	public ArrayList<Node> getNodes(){ return nodes; }

	public void drawProblem(Canvas c){
		Graphics2D g2d = (Graphics2D) c.getGraphics();
		g2d.setColor(Color.BLUE);
		for(Node n : nodes){
			g2d.drawString("X", n.getX(), n.getY());
		}
		c.paintComponent(g2d);
	}
	
	@Override
	public double evaluate(Solution solution) {
		TSPSolution sol = (TSPSolution) solution;
		ArrayList<Arch> arches = sol.getArches();
		double totcost = 0;
		for(Arch a : arches){
			totcost += a.getCost();
		}
		return totcost;		
	}

	@Override
	public boolean isValid(Solution solution) {
		TSPSolution sol = (TSPSolution) solution;
		if(sol==null || nodes==null) return false;
		
		for(Node n : nodes){
			int nenters = 0;
			int nexits = 0;
			for(Arch a : sol.getArches()){
				if(a.getStartNode()==n)
					nexits++;
				if(a.getEndNode()==n)
					nenters++;
			}
			if(nenters!=1 || nexits!=1)
				return false;
		}
		
		System.out.println("Solution si valid");
		
		return true;
	}

	@Override
	public Solution generateRandomSolution() {
		System.out.println("Generating random solution");
		
		Random random = new Random();
		
		nodes = new ArrayList<Node>();
		ArrayList<Arch> arches = new ArrayList<Arch>();		
		for(int i=0; i<numnodes; i++){
			Node node = new Node(random.nextInt(100), random.nextInt(100));
			nodes.add(node);
		}
		int i;
		for(i=0;i<numnodes-1; i++){
			Arch arch = new Arch(nodes.get(i), nodes.get(i+1));
			arches.add(arch);
		}
		arches.add( new Arch(nodes.get(i), nodes.get(0)));
		
		TSPSolution sol = new TSPSolution(this);
		
		sol.setNodes(nodes);
		sol.setArches(arches);
		
		System.out.println("Generated solution: " + sol);
		
		return sol;
	}

	@Override
	public Solution generateNeighborSolution(Solution solution, int maxDist) {
		System.out.println("neighbor");
		TSPSolution sol = (TSPSolution)solution;
		double cost = this.evaluate(sol);
		System.out.println("Cost of sol is : " + cost);
		sol.swapTwoRandomArches();
		cost = this.evaluate(sol);
		System.out.println("Cost of neighbor is : " + cost);		
		return sol;
	}

	@Override
	public Solution combine(Solution s1, Solution s2) {
		System.out.println("combine");
		return null;
	}

	@Override
	public Solution mutate(Solution solution) {
		TSPSolution sol = (TSPSolution)solution;
		sol.swapTwoRandomArches();
		return sol;
	}

}
