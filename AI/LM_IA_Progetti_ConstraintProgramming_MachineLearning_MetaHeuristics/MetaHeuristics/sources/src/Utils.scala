import Optimization._
import java.io.File
import scala.io.Source
import scala.collection.mutable.HashMap

package Optimization {

	object Utils {
	  
	  def loadProblems(path: String) : HashMap[String, Problem] = {
	    loadProblems(new File(path))
	  }
	  
	  def loadProblems(dir: File): HashMap[String, Problem] = {
	    val problems = new HashMap[String, Problem]
	    dir.listFiles().foreach(curr_file => {
	      val p = parseProblem(curr_file)
		  problems( p.getName ) = p         
	    })
	    problems
	  }
		
	  def parseProblem(file: File): Problem = {
			var id = "untitled"
			var best = 0.
			var tsp_nodes:List[Node] = List()
			Source.fromFile(file, "utf-8").getLines().foreach( l => {
				val attrs = l.split(":").filterNot(c => c=="").map(s => s.trim.toLowerCase)
				val parts = l.stripMargin.split(" ").filterNot(c => c=="").map(s => s.trim)
			
				if(attrs.size>1){ // attribute found
					if(attrs(0)=="name")
					  id = attrs(1)
					else if(attrs(0)=="best")
					  best = attrs(1).toDouble
				} else if(parts.size>1){ // node found
					tsp_nodes = new Node(parts(1).toDouble.toInt, parts(2).toDouble.toInt) :: tsp_nodes
				}
			})
			val tsp = new TSP(tsp_nodes)
			print(id + "  ")
			tsp.setName(id)
			tsp.setBest(best)
			tsp
	  }
	  
	  def shuffle[T](lst: List[T]): List[T] = {
	    var newList:List[T] = List()
	    var listOfAddedElems:List[Int] = List()
	    var toAdd = lst.size
	    while(toAdd>0){
	      val nextIndex = KB.random.nextInt(toAdd)
	      var index = nextIndex
	      while(listOfAddedElems.contains(index)){
	        index = (index + 1) % lst.size
	      }
	      listOfAddedElems = index :: listOfAddedElems
	      newList = lst(index) :: newList
	      toAdd = toAdd-1
	    }
	    newList
	  }
	  
	}

}