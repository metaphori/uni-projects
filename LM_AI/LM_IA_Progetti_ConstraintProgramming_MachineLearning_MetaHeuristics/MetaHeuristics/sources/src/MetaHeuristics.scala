
import Optimization._
import Optimization.Solvers.BasicSolver

package Optimization.Solvers.MetaHeuristics {

	/**********************************/
    /******* SIMULATED ANNEALING ******/
    /**********************************/
	class SimulatedAnnealing extends BasicSolver{
	  var init_temperature: Double = 60
	  var step: Double = 0.05
	  
	  def solve(problem: Problem) = {
	    val nn = new Optimization.Solvers.NearestNeighbor
	    nn.solve(problem)
		var currSolution = nn.getBestKnownSolution
		//var currSolution = problem.generateRandomFeasibleSolution
	    
		var currCost = problem.eval(currSolution)
		var bestSolution = currSolution
		var bestCost = currCost
		var temperature = init_temperature
		
		while( end_condition(temperature) == false ){
			val nextSolution = problem.getOneNeighbor(currSolution)
			/*
			 * The next operations make SA too slow (getNeighbors need to be faster)
			val neighs = problem.getNeighbors(currSolution)
			var nextSolution = neighs(KB.random.nextInt(neighs.size))
			*/
			val nextSolutionCost = problem.eval(nextSolution)
			if(nextSolutionCost < currCost){
			  currSolution = nextSolution
			  currCost = nextSolutionCost
			  if(currCost < bestCost){
			    bestCost = currCost
			    bestSolution = currSolution
			    notifyObservers(problem, currSolution)
			  }
			} else{
			  // # accetta di porre sol = neigh con probabilità p = e^-(neigh_cost-cost)/kT
              //# k = -T0 ln(0.8) / (neigh_cost-cost)
			  val diff = nextSolutionCost - currCost
			  val k = 0.4;
			  var probability = Math.exp(-diff/(k*temperature))
			  
			  val randValue = Math.abs(KB.random.nextDouble())			  
			  if( randValue < probability){
			    currSolution = nextSolution
			    currCost = nextSolutionCost
			    notifyObservers(problem, currSolution)
			  }
			}
			
			if( annealing_condition == true){
			  // update temperature
			  temperature = temperature - step
			}
		}
		notifyObservers(problem, currSolution)
	    setBest(problem, bestSolution, bestCost)
	  }
	  
	  def end_condition(temp: Double): Boolean = {
		temp < (init_temperature/10)
	  }
	  
	  def annealing_condition(): Boolean = {
	    true
	  }
	  
	} // end SimulatedAnnealing
	
	/**********************************/
    /*********** TABU SEARCH **********/
    /**********************************/
	class TabuSearch extends BasicSolver {
	  
	  class TabuList(val dim: Int) {
	    private var list: List[Solution] = List()
	    
	    def addItem(sol: Solution) = {
	      if(list.size==dim){
	        list = list.drop(1)
	      }
	      list = list ++ List(sol)
	    }
	    
	    def contains(sol: Solution) = {
	      list.contains(sol)
	    }
	    
	  }
	  
	  def solve(problem: Problem) = {
	    val tsp = problem.asInstanceOf[TSP]
	    
	    // tabu list to keep trace of ultimately explored solutions
	    val TLSIZE = 10
	    val tabu_list = new TabuList(TLSIZE)
	    
	    // generating an initial feasible solution
	    val nn = new Optimization.Solvers.NearestNeighbor
	    nn.solve(problem)
	    
	    // vars to keep trace of current explored solution
		var currSolution = nn.getBestKnownSolution
		var currCost = problem.eval(currSolution)
		
		// vars to keep trace of best solution found (as pejorative solutions can be accepted)
		var bestSolution = currSolution
		var bestCost = currCost
		
		var goOn = true
		var iter = 0
		while(goOn && iter<50){
			val neighs = tsp.getBestNeighbors(currSolution).take(TLSIZE+1)
			val allowed_set = neighs.filterNot(sol => tabu_list.contains(sol))
			if(allowed_set.size>0){
			  currSolution = allowed_set.head
			  currCost = problem.eval(currSolution)
			  tabu_list.addItem(currSolution)
			  notifyObservers(problem, currSolution)
			  if(currCost < bestCost){
			    bestSolution = currSolution
			    bestCost = currCost
			  }
			} else{
			  goOn = false
			}
			iter=iter+1
		}
		
	    notifyObservers(problem, bestSolution)
	    setBest(problem, bestSolution, bestCost)
	    
	  }
	  
	}

}