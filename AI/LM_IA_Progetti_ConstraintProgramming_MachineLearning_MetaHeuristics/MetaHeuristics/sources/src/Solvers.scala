import Optimization._

package Optimization.Solvers {  

	trait Observer {
	  def notifySolution(problem: Problem, currSolution: Solution)
	}
  
	trait Solver {
	  
	  def getBestKnownSolution(): Solution
	  def getBestKnownSolutionCost(): Double
	  def isValid(): Boolean
	  
	  def solve(problem:Problem): Unit

	  /* refactoring BEGIN*/
	  /*
	  protected var currProblem: Problem = null
	  protected var currSolution: Solution = null
	  protected var currCost: Double = Int.MaxValue
	  protected var observers: List[Observer] = List()
	  
	  def solveCompletely(problem: Problem): Unit = {
	    init(problem)
	    while(end_condition){
	      observers.foreach( o => o.notifySolution(problem, currSolution))
	      nextStep(problem)
	    }
	    finalStep(problem)
	    prepareResults()
	    reset()
	  }
	  def nextStep(problem: Problem): Unit = {}
	  def finalStep(problem: Problem): Unit = {}
	  def init(problem: Problem): Unit = {currProblem = problem}
	  def reset(): Unit = { currProblem}
	  def end_condition(): Boolean = {true}
	  def prepareResults(): Unit = {}
	  
	  def addObserver(obs: Observer) = {
	    observers = observers :+ obs
	  }
	  def removeObserver(obs: Observer) = {
	    observers = observers.filter(o => o!=obs) // TODO: use better collection for removal
	  }	  
	  /* refactoring END */
	  */
	}
	
	abstract class BasicSolver extends Solver {
	  
	  protected var best: Solution = null
	  protected var bestCost: Double = 0
	  protected var valid: Boolean = false
	  protected var observers: List[Observer] = List()
	  
	  def getBestKnownSolution(): Solution = { best }
	  def getBestKnownSolutionCost(): Double = { bestCost }
	  def isValid(): Boolean = { valid }

	  
	  def setBest(problem:Problem, solution:Solution, solutionCost: Double = -1) {
	    best = solution
	    if(solutionCost== (-1))
	    	bestCost = problem.eval(best)
	    else
	    	bestCost = solutionCost
	    if(problem.isValid(best))
	      valid=true
	    else
	      valid=false
	  }
	  
	  override def toString(): String = { this.getClass().getName() }
	  
	  def addObserver(obs: Observer) = {
	    observers = observers :+ obs
	  }
	  def removeObserver(obs: Observer) = {
	    observers = observers.filter(o => o!=obs) // TODO: use better collection for removal
	  }
	  
	  def notifyObservers(problem: Problem, solution: Solution) : Unit = {
		  observers.foreach( o => o.notifySolution(problem, solution))
	  }
	}
  
	/********************************************
	 *   NEAREST NEIGHBOR
	 ********************************************/
	class NearestNeighbor extends BasicSolver {
	  
	  def solve(problem:Problem):Unit = {
		val tsp: TSP = problem.asInstanceOf[TSP]
		val nodes = tsp.nodes
		
		var solution: TSPSolution = problem.getEmptySolution.asInstanceOf[TSPSolution]
		
		var nodesToVisit: List[Node] = Utils.shuffle(nodes)
		val firstNode = nodesToVisit.head
		var curNode = firstNode
		nodesToVisit = nodesToVisit.drop(1)
		while(nodesToVisit.size>0){
		  nodesToVisit = nodesToVisit.sortBy(n => n.distanceTo(curNode))
		  val next = nodesToVisit.head
		  nodesToVisit = nodesToVisit.drop(1)
		  solution = solution.addComponent(new Arch(curNode,next)).asInstanceOf[TSPSolution]
		  notifyObservers(problem, solution)
		  curNode = next
		}
		solution = solution.addComponent(new Arch(curNode,firstNode)).asInstanceOf[TSPSolution]
		notifyObservers(problem, solution)
		
		setBest(problem, solution)
	  }
	  
	  /*
	  var nodesToVisit: List[Node] = List()
	  var firstNode: Node = null
	  var currNode: Node = null
	  override def init(problem: Problem):Unit = {
		val tsp = problem.asInstanceOf[TSP]
		val nodes = tsp.nodes
		var solution: TSPSolution = problem.getEmptySolution.asInstanceOf[TSPSolution]
		
		nodesToVisit = shuffle(nodes)
		currProblem = tsp
		currSolution = solution
		
		firstNode = nodesToVisit.head
		currNode = firstNode
		nodesToVisit = nodesToVisit.drop(1)	    
	  }
	  override def end_condition(): Boolean = {
	    nodesToVisit.size>0
	  }
	  override def nextStep(problem: Problem): Unit = {
		  nodesToVisit = nodesToVisit.sortBy(n => n.distanceTo(currNode))
		  val next = nodesToVisit.head
		  nodesToVisit = nodesToVisit.drop(1)
		  currSolution = currSolution.addComponent(new Arch(currNode,next)).asInstanceOf[TSPSolution]
		  //observers.foreach( o => o.notifySolution(problem, solution))
		  currNode = next	    
	  }
	  override def finalStep(problem: Problem): Unit = {
		  currSolution = currSolution.addComponent(new Arch(currNode,firstNode)).asInstanceOf[TSPSolution]
	  }
	  */
	  
	}
	
	
	/********************************************
	 *   CONSTRUCTIVE HEURISTIC SOLVER
	 ********************************************/
	/*
	class ConstructiveHeuristic extends BasicSolver {
	  
	  def solve(problem:Problem):Unit = {
	    var components:List[Component] = problem.generateAllComponents()
	    var solution:Solution = problem.getEmptySolution()
	    var componentsSolution:List[Component] = List()

	    components = components.sortWith( (c1:Component, c2:Component) => c1.cost < c2.cost) 
	    
	    while(components.size>0 && ! problem.isValid(solution)){
	    	//val soltmp = problem.getSolutionFromComponents(componentsSolution ++ List(components.head))
	    	val soltmp = solution.addComponent(components.head)
	    	if( problem.isValidPartial(soltmp)){
	    		//componentsSolution = componentsSolution ++ List(components.head)
		    	solution = soltmp
	    	}
	    	components = components.drop(1) 
	    }
	    
	    println("Incomplete CH solution: " + solution)
	    
	    if(problem.isValidPartial(solution) && !problem.isValid(solution)){
	    	solution = problem.completeSolution(solution)
	    }
	    
	    setBest(problem, solution)
	  }

	}
	*/
	
	/********************************************
	 *   LOCAL SEARCH SOLVER
	 ********************************************/
	class LocalSearch extends BasicSolver {
	  
	  def solve(problem: Problem):Unit = {
	    // val initialFeasibleSolution = problem.generateRandomFeasibleSolution();
	    val nn = new Optimization.Solvers.NearestNeighbor
	    nn.solve(problem)
	    val initialFeasibleSolution= nn.getBestKnownSolution
		
	    var solution = initialFeasibleSolution
	    var cost = problem.eval(solution)
	    var goOn = true
	    var iter = 0
	    while(goOn){
	      val bestneighbor = problem.getBestNeighbor(solution)
	      val neighborcost = problem.eval(bestneighbor)
	      if(neighborcost < cost){
	        solution = bestneighbor
	        notifyObservers(problem, solution)
	        cost = neighborcost
	      } else{
	       /* println("Best neigh: " + bestneighbor)
	        println("Neigh cost: " + neighborcost)
	        println("Best sol: " + solution)
	        println("SOl cost: " + cost)*/
	        goOn = false
	      }
	    }
	    
	    setBest(problem, solution)
	  }
	  
	}
	
	
	/********************************************
	 *   BEST INSERTION
	 ********************************************/
	class BestInsertion extends BasicSolver {
	  
	  def solve(problem: Problem):Unit = {
		val tsp:TSP = problem.asInstanceOf[TSP]
		var nodes: List[Node] = Utils.shuffle(tsp.nodes)
		
		val a = nodes.head
		nodes = nodes.drop(1)
		val b = nodes.head
		nodes = nodes.drop(1)
		
		var solution: List[Node] = List(a,b)
		var bestSolution = TSPSolution(solution)
		var bestCost = problem.eval(bestSolution)
		var currBestSolution = bestSolution
		var currBestCost = bestCost
		
		while(nodes.size>0){
		  var currNodeList = solution
		  var currSolution:TSPSolution = null
		  var currCost:Double = 0
		  var nodeIndex = 0
		  nodes.foreach(n => {
			  for( i <- (1 to solution.size) ){
			    val nodeList = solution.take(i)++List(n)++solution.drop(i)
			    val sol = TSPSolution(nodeList)
			    val solCost = tsp.eval(sol)
			    if(solCost < currCost || currSolution==null){
			      nodeIndex = nodes.indexOf(n)
			      currSolution = sol
			      currCost = solCost
			      currNodeList = nodeList
			    }
			  }
		  })
		  currBestSolution = currSolution
		  currBestCost = currCost
		  solution = currNodeList
		  notifyObservers(problem, currBestSolution)
		  nodes = nodes.take(nodeIndex)++nodes.drop(nodeIndex+1)
		  /*println("New solution: " + solution)
		  println("Remained nodes: " + nodes)*/
		}
	    
	    setBest(problem, currBestSolution, currBestCost)
	  }
	  
	}
	

}