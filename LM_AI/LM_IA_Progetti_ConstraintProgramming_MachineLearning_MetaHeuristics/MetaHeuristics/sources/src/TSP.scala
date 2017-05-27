import scala.collection.mutable.HashMap

import Optimization._
import Optimization.Utils

class TSP(val nodes:List[Node]) extends Problem {

  /* evaluate the cost associated to a TSP solution */
  def eval(solution:Solution) : Double = {
    val tspSolution:TSPSolution = solution.asInstanceOf[TSPSolution]
    tspSolution.arches.foldLeft(0.)( (s:Double, a:Arch) => s+a.from.distanceTo(a.to) )
  }
  
  /* checks if a TSP solution is feasible */
  def isValid(solution: Solution): Boolean = {
    val tspSolution:TSPSolution = solution.asInstanceOf[TSPSolution]
    if(tspSolution.arches.size!=nodes.size){ // quick test on num of arches
      return false
    }
    
    val archesForNode:HashMap[Node,Int] = HashMap()
    nodes.foreach( (n:Node) => { archesForNode(n)=0 } )
    
    tspSolution.arches.foreach( (a:Arch) => {
    	archesForNode(a.from) = archesForNode(a.from)+1
    	archesForNode(a.to) = archesForNode(a.to)+1
    })

    nodes.forall( n => archesForNode(n) == 2)
  }
  
  /* checks if a partial solution is valid */
  def isValidPartial(solution:Solution) : Boolean = {
    val tspSolution:TSPSolution = solution.asInstanceOf[TSPSolution]
    /*
    val enters:HashMap[Node,Int] = HashMap()
    val exits:HashMap[Node,Int] = HashMap()
    nodes.foreach( (n:Node) => { enters(n)=0; exits(n)=0 } )
    
    tspSolution.arches.foreach( (a:Arch) => {
    	exits(a.from) = exits(a.from)+1
    	enters(a.to) = enters(a.to)+1
    })
    
    nodes.forall( n => enters(n) + exits(n)<=2) && !cycles_?(solution)
    */
    var visited = new HashMap[Node,Boolean]
    //visited(tspSolution.arches.head.from) = true
    tspSolution.arches.foreach( a => {
      if(visited.contains(a.to)) return false
      visited(a.to) = true
    })
    true
  }
 
  /*
   * Controlla che l'ultimo arco inserito non crei un ciclo
   */
  def cycles_?(solution: Solution): Boolean = {
    val tspSolution:TSPSolution = solution.asInstanceOf[TSPSolution]
    val lastArch = tspSolution.getLastComponent.asInstanceOf[Arch]
    tspSolution.arches.forall( a => lastArch.to!=a.from)
  }
    
  def randomize[T](lst: List[T], random: java.util.Random = KB.random): List[T] = {
    var size = lst.size
    var resultList:List[T] = List()
    var listAddedElems:List[Int] = List()
    while(size>0){
      var nextIndex = random.nextInt(size)
      while(listAddedElems.contains(nextIndex)){
        nextIndex = (nextIndex+1)
        if(nextIndex==lst.size)
          nextIndex=0
      }
      resultList = lst(nextIndex) :: resultList
      listAddedElems = nextIndex :: listAddedElems
      size = size - 1
    }
    resultList
  }
  
  /* generate a random solution -- it may return a non feasible solution */ 
  def generateRandomSolution() : Solution = {
     val random = KB.random
     var nodeToVisit = randomize(nodes, random)
     TSPSolution(nodeToVisit)
   }
  
  /* try to complete a partial solution */
  def completeSolution(s:Solution): Solution = {
	val tspSol = s.asInstanceOf[TSPSolution]
	if( nodes.forall(n => tspSol.arches.exists(a => a.from==n || a.to==n)) ){
	  val nodesInfo = nodes.map( (n:Node) => {
	    (n, tspSol.arches.exists(a => a.from==n) && tspSol.arches.exists(a => a.to==n) ) 
	  })
	  // filtra i nodi per cui esiste sia un arco in entrata sia uno in uscita
	  var marginNodes = nodesInfo.filterNot( nInfo => nInfo._2).map( nInfo => nInfo._1)
	  
	  var arches = tspSol.arches
	  
	  while(marginNodes.size>=2){
	    val n1 = marginNodes(0)
	    val n2 = marginNodes(1)
	    marginNodes = marginNodes.drop(2)
	    arches = new Arch(n1,n2) :: arches
	  }
	  
	  new TSPSolution(arches)
	  
	} else{
	  // not completable
	  tspSol
	}
  }
  
  /* generate all possible solution components (i.e. all the possible arches) */
  def generateAllComponents() : List[Component] = {
     nodes.flatMap( (n:Node) => {
    	(for(n2 <- nodes if n2!=n)
    	  yield new Arch(n,n2)
    	)
     })
  }
  
  /* returns an empty solution */
  def getEmptySolution(): Solution = { new TSPSolution(List()) }
  
  def getSolutionFromComponents(comps:List[Component]): Solution = {
     val arches:List[Arch] = comps.asInstanceOf[List[Arch]]
     new TSPSolution(arches)
  }
   
  /*
  * Assumption: solution 's' is feasible
  * Resulting solution is feasible as well
  */
  def getBestNeighbor(s: Solution): Solution = {
    getBestNeighbors(s).head
  }
  
  
  def getBestNeighbors(s: Solution): List[Solution] = {
    val tspSol = s.asInstanceOf[TSPSolution]
    
    val solutions = getNeighbors(s)
    // order solutions from cheaper to more expensive
    solutions.sortBy( sol => this.eval(sol) )    
  }
   
  /* get all the neighbors (based on a neighbor function) for the given solution */
  def getNeighbors(s: Solution, neigh_fun: Solution=>List[Solution]): List[Solution] = {
    neigh_fun(s)
  }
  
  def getNeighbors(s: Solution): List[Solution] = {
    getNeighbors(s, two_opt_arch(_)(-1) )
  }
  
  def getOneNeighbor(s: Solution): Solution = {
    getNeighbors(s, two_opt_arch(_)(1)).head
  }
   
  def two_opt_arch(s: Solution)(howMany:Int=(-1)): List[Solution] = {

    val sol: TSPSolution = s.asInstanceOf[TSPSolution]
	// prendo le combinazioni di 2 archi 
	// filtrando quelle dove si hanno archi adiacenti
    val couples = Utils.shuffle(sol.arches).combinations(2).filter( pair => {
	  pair(0).from!=pair(1).to && pair(0).to!=pair(1).from
    })

    var lst: List[Solution] = List()
    var arches: List[Arch] = List()
    //println("Base solution: " + sol)
    // le combinazioni possono essere tantissime, quindi 
    // il prossimo ciclo su ognuna di esse impiega un sacco
    // TODO: improve performance
    var counter = 0
    try{
	    couples.foreach(pair => {
	      // rimuovo gli archi dalla soluzioni di base
	      val circuits:List[List[Arch]] = getCircuits(sol.arches, pair(0), pair(1))	      
	      var partialSol1 = new TSPSolution(circuits(0))
	      var partialSol2 = new TSPSolution(circuits(1))
	      
	      /*   a----------->b                 <-----------   (part1)
	       *    |          |    need to be         x 
	       *   d<-----------c         		  <-----------   (part2)
	       *   so we reverse one part of the circuit
	       */
	      val p1_from = circuits(0).head.from // a
	      val p1_to = circuits(0).last.to     // b
	      val p2_to = circuits(1).head.from   // c
	      val p2_from = circuits(1).last.to   // d
	      val newArch1 = new Arch(p1_from,p2_to)
	      val newArch2 = new Arch(p2_from, p1_to)
	      partialSol1 = partialSol1.reverse
	      partialSol1 = partialSol1.addComponent(newArch1).asInstanceOf[TSPSolution]
	      partialSol2 = partialSol2.addComponent(newArch2).asInstanceOf[TSPSolution]
	      val newSolution = new TSPSolution(partialSol1.arches ++ partialSol2.arches)
	      
	      if(isValid(newSolution)){
	    	  lst = newSolution  :: lst
	    	  counter = counter + 1
	      }
	      if(howMany!=(-1) && counter>=howMany)
	        throw new Exception("out of this loop")
	    })
    }catch{
      case _ => Unit
    }

	lst
  }

  
  def getCircuits[T](lst: List[T], a:T, b:T): List[List[T]] = {
	var pos_a = lst.indexOf(a)
	var pos_b = lst.indexOf(b)
	if(pos_a > pos_b){
	   val temp = pos_a
	   pos_a = pos_b
	   pos_b = temp
	}
	val firstPart = lst.take(pos_a)
	val secondPart = lst.drop(pos_a+1).take(pos_b-pos_a-1)
	val thirdPart = lst.drop(pos_b+1)

	List( firstPart++thirdPart, secondPart )
  }
   
}

/*
 * It represents a NODE
 */
class Node(val x:Int, val y:Int){
  
  def distanceTo(other:Node) : Double = {
    val xdiff = Math.abs(other.x-x)
    val ydiff = Math.abs(other.y-y)
    Math.sqrt(xdiff*xdiff + ydiff*ydiff)
  }
  
  override def toString():String = {
    x+";"+y
  }

  override def equals(other:Any):Boolean = {
    if(other.isInstanceOf[Node]){
      val otherNode = other.asInstanceOf[Node]
      otherNode.x==x && otherNode.y==y
    }else{
      false
    }
  }
  
}

/*
 * It represents a UNDIRECTED ARCH between one node to another
 */
class Arch(val from:Node, val to:Node) extends Component {
  
  def cost():Double = {
    from.distanceTo(to)
  }
  
  override def toString():String = {
    "("+from.toString()+"->"+to.toString()+")"
  }
  
  // NOTE: we are dealing with undirected graphs!!!
  override def equals(other:Any) : Boolean = {
    if( other.isInstanceOf[Arch] ){
      val otherArch = other.asInstanceOf[Arch]
      (otherArch.from == this.from && otherArch.to == this.to) //||
        //(otherArch.to == this.from && otherArch.from == this.to)
    } else{
      false
    }
  }
}

/*
 * It represents a TSP solution
 */
class TSPSolution(val arches:List[Arch]) extends Solution{
  
  override def toString():String = {
    arches.toString
  }
  
  def addComponent(c:Component): Solution = {
    val newArch: Arch = c.asInstanceOf[Arch]
    val pos = arches.indexWhere(a => a.from == newArch.to)
    val newArches = pos match {
      case -1 =>  arches :+ newArch
      case _ => arches.take(pos)  ++ List(newArch) ++ arches.drop(pos)
    }

	new TSPSolution(newArches.asInstanceOf[List[Arch]])
  }
  
  def getLastComponent(): Component = {
    arches.last
  }
  
  def reverse(): TSPSolution = {
    val newArches = arches.reverse.map(a => new Arch(a.to, a.from))
    new TSPSolution(newArches)
  }
  
  override def equals(other: Any): Boolean = {
    val otherSol: TSPSolution = other.asInstanceOf[TSPSolution]
    
    val startOther = otherSol.arches.indexOf(this.arches.head)
    if(startOther<0) false
    else {
      var otherArches:List[Arch] = null
      if(startOther==0){
        otherArches = otherSol.arches
      } else{
        otherArches = otherSol.arches.drop(startOther)++otherSol.arches.take(startOther)
      }
      otherArches == this.arches
    }
  }
  
}

object TSPSolution {
  
  def apply(nodes: List[Node]): TSPSolution = {
     var nodeToVisit = nodes
     val firstNode = nodeToVisit.head
     nodeToVisit = nodeToVisit.drop(1)
     var node = firstNode
     var sol = new TSPSolution(List())
     while(nodeToVisit.size>0){
       val next = nodeToVisit.head
       nodeToVisit = nodeToVisit.drop(1)
       sol = sol.addComponent(new Arch(node,next)).asInstanceOf[TSPSolution]
       node = next
     }
     sol = sol.addComponent(new Arch(node, firstNode)).asInstanceOf[TSPSolution]
     sol    
  }
  
}