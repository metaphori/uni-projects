import scala.io.Source
import java.io.File
import scala.collection.mutable.HashMap
import Optimization._

object Main extends App {

  println("----------- METAHEURISTICS ----------");
  
  var csvOutput = ""
  
  // load a bunch of TSPs from specified directory
  val tsps = Utils.loadProblems("selected_tsp")
  
  // create solvers
  val nn = new Optimization.Solvers.NearestNeighbor()
  val bi = new Optimization.Solvers.BestInsertion()
  //val ch = new Optimization.Solvers.ConstructiveHeuristic()
  val ls = new Optimization.Solvers.LocalSearch()
  val sa = new Optimization.Solvers.MetaHeuristics.SimulatedAnnealing()
  val ts = new Optimization.Solvers.MetaHeuristics.TabuSearch()
  val solvers = List(bi, nn, /*ch ,*/ ls, sa, ts)
  
  
  // use solvers on the problems
  tsps.foreach( entry => {    
	  val id = entry._1
	  val problem:Problem = entry._2
	  println("\n>>>>>>>>>>>>>>>Problem: " + problem)
	  csvOutput += "\n"+id+","+problem.getBest+","
	  
	  solvers.foreach( s => {
	    s.solve(problem)
		//println("Best solution found: " + s.getBestKnownSolution)
		var validSol = ""
		if(s.isValid)
		  validSol="valid"
		else
		  validSol="NOTvalid"
		println(s + " Cost of solution(" + validSol +"): " + s.getBestKnownSolutionCost)
		csvOutput += s.getBestKnownSolutionCost + ","
		//readLine
	  })
 
  })

  println(csvOutput)
  
}
