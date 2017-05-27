package Optimization {

	object KB {
	  var seed:Int = 1
	  val random:java.util.Random = new java.util.Random(seed)
	}
  
	trait Problem {
	  
	  def eval(s: Solution) : Double
	  
	  def isValid(s: Solution) : Boolean
	  def isValidPartial(s: Solution) : Boolean
	  
	  def generateRandomSolution : Solution
	  
	  def generateRandomFeasibleSolution() : Solution = {
	    var s = generateRandomSolution
	    while(! isValid(s) )
	      s = generateRandomSolution
	    s
	  }
	  
	  protected var name = "untitled"
	  def setName(s:String) = { name=s }
	  def getName(): String = { name }
	  
	  protected var best:Double = Integer.MAX_VALUE
	  def setBest(d:Double) = { best = d }
	  def getBest(): Double = { best }
	  
	  override def toString(): String = {
	    getName + " (best known solution: " + getBest +")"
	  }
	  
	  /* The following are required by ConstructiveHeuristic*/
	  
	  def generateAllComponents(): List[Component]
	  
	  def getSolutionFromComponents(components: List[Component]): Solution
	  
	  def getEmptySolution(): Solution
	  
	  def completeSolution(s: Solution): Solution
	  
	  /* The following are required by LocalSearch */
	  
	  def getBestNeighbor(s: Solution): Solution
	  
	  def getNeighbors(s: Solution): List[Solution]
	  
	  def getOneNeighbor(s: Solution): Solution
	}
	
	trait Component {
	  
	  def cost():Double
	  
	}
	
	trait Solution {
	  
	  // returns a new solution by adding a new component
	  def addComponent(c: Component): Solution
	  
	  def getLastComponent(): Component
	}

  
}