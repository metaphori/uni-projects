package it.unibo.test1;
import java.awt.Graphics2D;
import java.util.ArrayList;

import solutions.Solution;

import metaheuristics.BLS;
import metaheuristics.SimulatedAnnealing;
import metaheuristics.TabuSearch;


public class TestOptimization {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		GUI gui = new GUI();
		GUI.Canvas canvas = gui.getCanvas();
		
		
		TravelSalesmanProblem tsp = new TravelSalesmanProblem("");
		Node n1 = new Node(10,10);
		Node n2 = new Node(20,10);
		Node n3 = new Node(20,20);
		Node n4 = new Node(10,20);
		ArrayList<Node> nodes = new ArrayList<Node>();
		nodes.add(n1);
		nodes.add(n2);
		nodes.add(n3);
		nodes.add(n4);
		tsp.setNodes(nodes);
		
		//tsp.generateRandomSolution();
		tsp.drawProblem(canvas);
		
		/*
		SimulatedAnnealing sa = new SimulatedAnnealing();
		System.out.println(sa.toString());
		Solution s = sa.solve(tsp);
		System.out.println("Solution: "+s);
		*/
		/*
		SimulatedAnnealing sa = new SimulatedAnnealing();
		TabuSearch ts = new TabuSearch();
		BLS bls = new BLS();
		
		ArrayList<CPMP> problems = ModelLoader.loadCPMP("data/pmedcap1.txt");
		for(CPMP p : problems){
			CPMPSolution sol = (CPMPSolution) sa.solve(p);
			System.out.println("(SA) SOLVED WITH SOLUTION OF COST: " + p.evaluate(sol));
			CPMPSolution sol2 = (CPMPSolution) ts.solve(p);
			System.out.println("(TS) SOLVED WITH SOLUTION OF COST: " + p.evaluate(sol2));
			CPMPSolution sol3 = (CPMPSolution) bls.solve(p);
			System.out.println("(BLS) SOLVED WITH SOLUTION OF COST: " + p.evaluate(sol3));
			
			
			System.out.println("(best known solution = " + p.getBestKnownSolution());
			
			try{ System.in.read(); } catch(Exception exc){}
		}
		*/
	}

}
