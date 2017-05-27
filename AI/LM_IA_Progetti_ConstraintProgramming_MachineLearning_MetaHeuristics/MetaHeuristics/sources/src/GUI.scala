import javax.swing._
import java.awt._
import Optimization._
import Optimization.Utils._
import Optimization.Solvers._
import scala.collection.immutable.List
import java.awt.event._
import scala.collection.mutable.HashMap
import javax.swing.event._

object GuiLauncher extends App {
	new GUI()
}

package Optimization {
	
	class GUI extends JFrame with ActionListener with ChangeListener with Observer {
		/* VARIABLES */
		var tsps: HashMap[String, Problem] = HashMap()
		var curSolver: Solver = null
		var curProblem: Problem = null
	    
		var FFX: Double = 5 // fattore di forma
		var FFY: Double = 5
		
		/*********************/
		/* GUI CONFIGURATION */
		/*********************/
		val container = new JPanel()
	    val cmdPanel = new JPanel()
	    val canvas = new JPanel()
		val footer = new JPanel()
	    
	    val loadButton = new JButton("Load problems")
	    val solveButton = new JButton("Solve")
		val nextButton = new JButton("NextStep")
	    val problemsBox = new JComboBox
	    val solversBox = new JComboBox
	    solversBox.addItem("solver1")
	    val info = new JTextField("")
		info.setPreferredSize(new Dimension(500,30))
		//val factorSlider = new JSlider(SwingConstants.HORIZONTAL ,1, 100, 1);
		//factorSlider.setValue(FF.toInt)
	    
	    setSize(1024, 720)
	    setTitle("Drawing TSP")
	    
	    loadButton.addActionListener(this)
	    problemsBox.addActionListener(this)
	    solversBox.addActionListener(this)
	    solveButton.addActionListener(this)
	    nextButton.addActionListener(this)
	    //factorSlider.addChangeListener(this)
	    
	    canvas.setBackground(Color.yellow)
	    canvas.setPreferredSize( new Dimension(880,550))
	    
	    cmdPanel.add(loadButton)
	    cmdPanel.add(problemsBox)
	    cmdPanel.add(solversBox)
	    cmdPanel.add(solveButton)
	    cmdPanel.add(nextButton)
	    //cmdPanel.add(factorSlider)
	    
	    footer.add(info)
	    
	    container.add(cmdPanel)
	    container.add(canvas)
	    container.add(footer)
	    
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
	    setContentPane(container)
	    setVisible(true)  
	  
	  /****************************/
	  /*   INITIALIZATION    */
	  /****************************/
	  // create solvers
	  val nn = new Optimization.Solvers.NearestNeighbor()
	  //val ch = new Optimization.Solvers.ConstructiveHeuristic()
      val bi = new Optimization.Solvers.BestInsertion
	  val ls = new Optimization.Solvers.LocalSearch()
	  val sa = new Optimization.Solvers.MetaHeuristics.SimulatedAnnealing()
	  val ts = new Optimization.Solvers.MetaHeuristics.TabuSearch()
	  
	  val solvers = List(bi, nn, /*ch ,*/ ls, sa, ts)	  
	  solversBox.removeAllItems()
	  solvers.foreach(s => {
	    solversBox.addItem(s)
	    s.addObserver(this)
	  })
	  
	  loadProblemsIntoGUI(new java.io.File("selected_tsp"))
	  
	  /*******************************/
	  /*  METHODS */
	  /*******************************/
	  def actionPerformed(ev: ActionEvent) = {
	    	val source = ev.getSource
	    	if( source == loadButton){
	    	  val jfc = new JFileChooser()
	    	  jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY)
	    	  jfc.setCurrentDirectory(new java.io.File("."))
	    	  val res = jfc.showOpenDialog(this)
	    	  if(res == JFileChooser.APPROVE_OPTION){
	    	    val selectedDir = jfc.getSelectedFile()
	    	    loadProblemsIntoGUI(selectedDir)
	    	  }
	    	} else if (source == problemsBox){
	    	  val item: String = source.asInstanceOf[JComboBox].getSelectedItem().toString()
	    	  if(tsps.contains(item)){
	    		curProblem = tsps( item )
	    	  }
	    	} else if( source == solversBox){
	    	  curSolver = source.asInstanceOf[JComboBox].getSelectedItem().asInstanceOf[Solver]
	    	  println("---------------- " + curSolver + " ----------------")
	    	} else if( source == solveButton && curProblem!=null && curSolver!=null){
	    	  canvas.paintImmediately(0, 0, canvas.getWidth(), canvas.getHeight())
	    	  curSolver.solve(curProblem)
	    	  info.setText("BEST: " + curProblem.getBest + " --- " + "Curr solution: " + curSolver.getBestKnownSolutionCost)
	    	} else if( source == nextButton && curProblem!=null && curSolver!=null){
	    	  //curSolver.ne
	    	}
	  }
	    
	  def stateChanged(ce: ChangeEvent){
	    /*
	    val slider: JSlider = ce.getSource().asInstanceOf[JSlider]
	    if(!slider.getValueIsAdjusting()){
	      FF = slider.getValue()
	    }
	    */
	  }
		
	  def loadProblemsIntoGUI(dir: java.io.File) = {
	    tsps = Utils.loadProblems(dir)
	    problemsBox.removeAllItems()
	    tsps.foreach( entry => {
	    	problemsBox.addItem(entry._1)
	    })	    
	  }	
		
	  def notifySolution(p: Problem, s: Solution) = {
	    val tsp: TSP = p.asInstanceOf[TSP]
	    val tspSol: TSPSolution = s.asInstanceOf[TSPSolution]
	    println(tsp.eval(tspSol))
	    drawTSP(tsp, tspSol)
	  }
	    
	  def drawTSP(tsp:TSP, s:TSPSolution) {	    
	    val g2d = canvas.getGraphics().asInstanceOf[java.awt.Graphics2D]
	    
	    var maxx = Int.MinValue
	    var minx = Int.MaxValue
	    var maxy = Int.MinValue
	    var miny = Int.MaxValue
	    
	    tsp.nodes.foreach( n => {
	    	if(n.x<minx) minx=n.x
	    	else if(n.x>maxx) maxx=n.x
	    	if(n.y<miny) miny=n.y
	    	else if(n.y>maxy) maxy=n.y
	    })
	 
	    FFX = (maxx/500)+1
	    FFY = (maxy/500)+1
	    val stepx = 1.5; //Math.log((maxx-minx)/500)
	    val stepy = 1.5; //Math.log((maxy-miny)/500)
	    
	    val marginx = 50
	    val marginy = canvas.getHeight-50
	    
	    g2d.setColor(Color.BLUE)
	    tsp.nodes.foreach( n => {
	      val x1 = ((n.x*stepx-minx+10)/FFX+marginx).toInt
	      val y1 = (-(n.y*stepy-miny+10)/FFY+marginy).toInt
	      //println(x1 + " - " + y1 + " - - " + FFX + ", " + FFY)
	      g2d.drawString(".", x1, y1)	
	    })
	    
	    g2d.setColor(Color.RED)
	    s.arches.foreach( a => {
	      val x1 = ((a.from.x*stepx-minx+10)/FFX + marginx).toInt
	      val x2 = ((a.to.x*stepx-minx+10)/FFX + marginx).toInt
	      val y1 = (-(a.from.y*stepy-miny+10)/FFY+marginy).toInt
	      val y2 = (-(a.to.y*stepy-miny+10)/FFY+marginy).toInt
	      g2d.drawLine(x1,y1,x2,y2) 
	    })
	    
	  }
	}

}