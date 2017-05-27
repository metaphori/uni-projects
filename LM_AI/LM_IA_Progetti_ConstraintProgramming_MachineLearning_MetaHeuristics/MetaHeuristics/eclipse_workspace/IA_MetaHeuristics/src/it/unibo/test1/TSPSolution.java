package it.unibo.test1;
import java.util.ArrayList;
import java.util.Random;

import problems.Problem;
import solutions.Solution;


public class TSPSolution extends Solution {
	private ArrayList<Arch> arches = new ArrayList<Arch>();
	private ArrayList<Node> nodes;
	private Problem p;
	
	public TSPSolution(Problem p) {
		super(p);
		this.p = p;
		TravelSalesmanProblem tsp = (TravelSalesmanProblem)p;
		this.nodes = tsp.getNodes();
	}

	@Override
	public String toString() {
		String res = "{";
		for(Arch a : arches){
			res += a.toString();
		}
		res += "}";
		return res;
	}

	@Override
	public Solution clone() {
		return new TSPSolution(this.p);
	}
	
	public ArrayList<Arch> getArches(){ return arches; }
	
	public void setArches(ArrayList<Arch> arches){ this.arches = arches; }
	public void setNodes(ArrayList<Node> nodes){ this.nodes = nodes; }

	public void swapTwoRandomArches(){
		int max = arches.size();
		if(max<2) return;
		
		Random rand = new Random();
		int fst = rand.nextInt(max);
		
		int snd = rand.nextInt(max);
		while(snd==fst)
			rand.nextInt(max); 
		
		Arch fstarch = arches.get(fst);
		Arch sndarch = arches.get(snd);
		Node from1 = fstarch.getStartNode();
		Node to1 = fstarch.getEndNode();
		Node from2 = sndarch.getStartNode();
		Node to2 = sndarch.getEndNode();
		Arch third = getArchWhere(to1, null);
		
		fstarch.setEndNode(from2);
		sndarch.setEndNode(to1);
		third.setEndNode(to2);
		
	}
	
	public Arch getArchWhere(Node from, Node to){
		boolean found = false;
		for(Arch a : arches){
			if(from!=null){
				if(a.getStartNode()==from)
					found = true;
				else
					found = false;
			} else{
				found = true;
			}
			
			if(to!=null && found){
				if(a.getEndNode()==to)
					found = true;
				else
					found = false;
			}
			
			if(found)
				return a;
		}
		return null;
	}
	
}
