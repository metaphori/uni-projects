package optimization.solvers;

import java.util.ArrayList;

import optimization.*;

public abstract class BasicSolver implements ISolver, IObservable {
	
	protected ISolution currSolution;
	protected double currSolutionCost;
	protected IProblem currProblem;
	
	protected ArrayList<IObserver> observers;
	
	
	
}
