package optimization.solvers;

import optimization.IProblem;
import optimization.ISolution;

public interface ISolver {

	public ISolution getBestKnownSolution();
	public double getBestKnownSolutionCost();
	
	public void solve(IProblem p);
	
}
