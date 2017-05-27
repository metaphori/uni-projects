package it.unibo.pap.nbodies.gui;

import it.unibo.pap.nbodies.BodiesConfiguration;
import it.unibo.pap.nbodies.Body;
import it.unibo.pap.nbodies.KB;
import it.unibo.pap.nbodies.Utils;
import it.unibo.pap.nbodies.concurrent.activeobjects.BodyTask;
import it.unibo.pap.nbodies.interfaces.IStateListener;
import it.unibo.pap.nbodies.physics.Force;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.swing.*;

public class NBodyGUI extends JFrame implements ActionListener, KeyEventDispatcher {
	private JPanel container;
	private JPanel cmdArea;
	private JPanel canvasArea;
	private SimulationCanvas canvas;
	private JButton start;
	//private JButton pause;
	private JButton stop;
	private JButton nextStep;
	private JButton loadBodies;
	private JButton randomBodies;
	private JTextField statusLabel;
	private JTextField infoLabel;
	
	private Controller controller;
	
	private boolean isPause = true;
	
	public NBodyGUI(Controller controller){
		this.controller = controller;
		
		init();
		configure();
	}
	
	public void init(){
		container = new JPanel();
		cmdArea = new JPanel();
		canvasArea = new JPanel();
		canvas = new SimulationCanvas();
		
		loadBodies = new JButton("Load body config..");
		randomBodies = new JButton("Random body config");
		start = new JButton("Start");
		//pause = new JButton("Pause");
		stop = new JButton("Stop");
		nextStep = new JButton("Next");
		
		statusLabel = new JTextField("Gravitational N-Body Problem");
		infoLabel = new JTextField();
	}
	
	public void configure(){
		/* Configure container panel */
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		container.setPreferredSize(new Dimension(1024,600));
        container.setMaximumSize(container.getPreferredSize());
        
        /* Configure components */
        statusLabel.setEditable(false);
        statusLabel.setMinimumSize(new Dimension(1024,40));
        statusLabel.setMaximumSize(new Dimension(1024,80));
        statusLabel.setPreferredSize(new Dimension(1024,40));
        statusLabel.setBackground(Color.WHITE);
        infoLabel.setEditable(false);
        infoLabel.setMinimumSize(new Dimension(1024,40));
        infoLabel.setMaximumSize(new Dimension(1024,80));
        infoLabel.setPreferredSize(new Dimension(1024,40));
        infoLabel.setBackground(Color.GRAY);
        
		/* Configure canvas area */
		canvasArea.add(canvas);
		
		/* Configure command panel */
		cmdArea.add(loadBodies);
		cmdArea.add(randomBodies);
		cmdArea.add(start);
		//cmdArea.add(pause);
		cmdArea.add(stop);
		cmdArea.add(nextStep);
		
		/* Configure layout */
		container.add(cmdArea);
		container.add(canvasArea);
		container.add(statusLabel);
		container.add(infoLabel);
		Container contentPane = getContentPane();
		contentPane.add(container);
		
		/* Configure events */
		loadBodies.addActionListener(this);
		randomBodies.addActionListener(this);
		start.addActionListener(this);
		stop.addActionListener(this);
		//pause.addActionListener(this);
		nextStep.addActionListener(this);
		
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(this);		
		
		/* Configure frame */
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1024, 600);
		this.setMinimumSize(this.getSize());
		this.setMaximumSize(this.getSize());
		this.setVisible(true);
	}

	/**
	 * Handles action events (mainly, button presses).
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton comp = (JButton)e.getSource();
		if(comp == loadBodies){
			JFileChooser jfc = new JFileChooser(".");
			int res = jfc.showOpenDialog(this);
			if(res == JFileChooser.APPROVE_OPTION){
				controller.createBodyConfiguration(jfc.getSelectedFile());
			}
		} else if(comp == randomBodies){
			controller.createRandomBodyConfiguration();
		} else if(comp == start){
			setInfoMessage("start called");
			controller.started();
			isPause = false;
		} else if(comp == stop){
			isPause = true;
			controller.stopped();
			setInfoMessage("stop called");
		} else if(comp == nextStep && isPause){
			setInfoMessage("nextStep called");
			nextStep();
			updateCanvas();
		}
	}
	
	public void setStatusMessage(String msg){
		statusLabel.setText(msg);
	}
	public void setInfoMessage(String msg){
		infoLabel.setText(msg);
	}
	
	/**
	 * Makes the galaxy move to its next state.
	 */
	public void nextStep(){
		if(controller.getBodiesConfig()==null) return;
		
		// first, calculate the net forces
		for(Body b : controller.getBodiesConfig().getBodies()){
			b.calculateNetForce(controller.getBodiesConfig().getBodies());
		}

		// then, make the bodies move to the next state
		for(Body b : controller.getBodiesConfig().getBodies()){
			b.goToNextState();			
		}
	}
	
	/**
	 * Request a repaint of the canvas.
	 */
	public void updateCanvas(){
		canvasArea.repaint();
	}	
	
	/**
	 * Sets the body configuration for the canvas.
	 */
	public void setBodiesConfig(BodiesConfiguration config){
		canvas.setBodiesConfig(config);
	}
	
	/**
	 * Handles keyboard-events.
	 * Summary:
	 * 	- ALT + up/down: scales up/down the form factor value
	 *  - CTRL + up/down: scales up/down the delta time value
	 *  - arrow: changes the offset by "moving" the window of observation 
	 */
	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		if(e.getID() != KeyEvent.KEY_PRESSED) return false;
		
		if(e.isAltDown() && e.getKeyCode() == KeyEvent.VK_UP)
			KB.FF = KB.FF<=0.1 ? KB.FF*2 : KB.FF+0.05;
		else if(e.isAltDown() && e.getKeyCode() == KeyEvent.VK_DOWN)
			KB.FF = KB.FF<=0.1 ? KB.FF/2 : KB.FF-0.05;
		else if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_UP)
			KB.DT *= 10;
		else if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_DOWN)
			KB.DT /= 10;
		else if(e.getKeyCode() == KeyEvent.VK_UP)
			canvas.moveUp(50);
		else if(e.getKeyCode() == KeyEvent.VK_DOWN)
			canvas.moveDown(50);
		else if(e.getKeyCode() == KeyEvent.VK_LEFT)
			canvas.moveLeft(50);
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			canvas.moveRight(50);
		else if(e.getKeyCode() == KeyEvent.VK_N){
			nextStep();
			updateCanvas();
		}
		else
			return false;
		
		setInfoMessage("FF: " + KB.FF + "; OFFSET-X: " + canvas.getOffsetX() + "; OFFSET-Y: " + 
				canvas.getOffsetY());
		canvas.repaint();
		
		return true;
	}
	
	
	/**
	 * 
	 * This task encapsulates the logic for updating the canvas.
	 *
	 */
	public static class UpdateCanvasTask implements Runnable {
		private NBodyGUI gui;
		
		public UpdateCanvasTask(NBodyGUI gui){
			this.gui = gui;
		}
		
		public void run(){
			gui.updateCanvas();
		}
	}	
	
}
