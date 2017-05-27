package it.unibo.test1;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

public class GUI extends JFrame implements ActionListener {

	private JPanel container;
	private JPanel cmdPanel;
	private JPanel canvas;
	
	private Button loadModel;
	
	public GUI(){
		init();
		configure();
	}
	
	public void init(){
		container = new JPanel();
		cmdPanel = new JPanel();
		canvas = new GUI.Canvas();
		
		loadModel = new Button("Load model");
	}
	
	public void configure(){
		
		loadModel.addActionListener(this);
		cmdPanel.add(loadModel);
		
		
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		container.add(cmdPanel);
		container.add(canvas);
		
		this.getContentPane().add(container);
		
		this.setBounds(100, 100, 800, 650);
		this.setVisible(true);		
	}
	
	public static class Canvas extends JPanel{
		
		public Canvas(){
			Dimension d = new Dimension(800,600);
			this.setMaximumSize(d);
			this.setPreferredSize(d);
			
			this.setBorder(BorderFactory.createLineBorder(Color.RED));
		}
		
		public void paintComponent(Graphics g){
			Graphics2D g2d = (Graphics2D)g;
			this.setBackground(Color.lightGray);
			
			g2d.drawString("HELLO MAN", 10, 10);
			
		}
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		Object obj = ev.getSource();
		if(obj==loadModel){
			
			JFileChooser jfc = new JFileChooser("./");
			int ret = jfc.showOpenDialog(this);
			if(ret == JFileChooser.APPROVE_OPTION){
				File chosen = jfc.getSelectedFile();
			}
			
		}
		
	}
	
	public Canvas getCanvas(){ return (Canvas)canvas; }
	
}
