package it.unibo.pap.nbodies.gui;

import it.unibo.pap.nbodies.BodiesConfiguration;
import it.unibo.pap.nbodies.Body;
import it.unibo.pap.nbodies.KB;
import it.unibo.pap.nbodies.physics.Position;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.io.IOException;
import java.util.ArrayList;

public class SimulationCanvas extends JPanel {
	/* canvas dimension */
	private int width = KB.CANVAS_WIDTH;
	private int height = KB.CANVAS_HEIGHT;
	
	/* canvas centrality */
	private int middle_x = width/2;
	private int middle_y = height/2;
	
	/* offset position for (0,0) */
	private int offset_x = 0;
	private int offset_y = 0;
	
	private Image bg_image = null;
	private Image body_image = null;
	
	private BodiesConfiguration bodies;
	
	public SimulationCanvas(){
		super();
		this.bodies = null;
		
		this.setBackground(new Color(220, 220, 220));
		this.setSize(width, height);
		this.setMinimumSize(this.getSize());
		this.setPreferredSize(this.getSize());
		this.setMaximumSize(new Dimension(Integer.MIN_VALUE, Integer.MAX_VALUE));
		
		loadBackgroundImage("files/galaxy.jpg");
		loadBodyImage("files/body.png");
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.drawImage(bg_image, 0, 0, null);
		
		// draw the axis
		/*
		g2d.setPaint(Color.RED);
		g2d.drawLine(KB.CANVAS_WIDTH/2, 0, KB.CANVAS_WIDTH/2, KB.CANVAS_HEIGHT);
		g2d.drawLine(0, KB.CANVAS_HEIGHT/2, KB.CANVAS_WIDTH, KB.CANVAS_HEIGHT/2);
		for(int i=-KB.CANVAS_WIDTH/2; i<KB.CANVAS_WIDTH/2; i+=50){
			g2d.drawString(""+i, i+KB.CANVAS_WIDTH/2, KB.CANVAS_HEIGHT/2+20);
		}
		for(int i=0; i<KB.CANVAS_HEIGHT; i+=50){
			g2d.drawString((i-KB.CANVAS_HEIGHT/2)+"", KB.CANVAS_WIDTH/2-20, i+5 );
		}
		*/
		
		g2d.setColor(Color.YELLOW);
		g2d.setPaint(Color.YELLOW);
		
		// draw all the bodies of the galaxy
		if(bodies!=null){
			for(Body b : bodies.getBodies()){
				drawBody(g2d, b.getPos());		
			}
		}
	}
	
	private void drawBody(Graphics2D g2d, Position p){
		// calculate coordinates for the body at position p
		int x = (int)Math.round(p.getX()*KB.FF)+ middle_x + offset_x;
		int y = -(int)Math.round(p.getY()*KB.FF) + middle_y + offset_y;

		// draw the body
		if(bodies.getN()>50){
			g2d.drawLine(x, y, x, y);
			g2d.drawLine(x-1, y-1, x-1, y-1);
			g2d.drawLine(x+1, y+1, x+1, y+1);
			g2d.drawLine(x-1, y+1, x-1, y+1);
			g2d.drawLine(x+1, y-1, x+1, y-1);			
		} else{
			g2d.drawImage(body_image, x - body_image.getWidth(null)/2, y-body_image.getHeight(null)/2, null);
		}
	}
	
	private void loadBackgroundImage(String filepath){
		try {
			bg_image = javax.imageio.ImageIO.read(getClass().getClassLoader().getResource(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}			
	}
	
	private void loadBodyImage(String filepath){
		try {
			body_image = javax.imageio.ImageIO.read(getClass().getClassLoader().getResource(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public void setBodiesConfig(BodiesConfiguration config){
		this.bodies = config;
		resetOffset();
	}

	public void moveRight(int offx){ this.offset_x += offx; }
	public void moveLeft(int offx){ this.offset_x -= offx; }
	public void moveUp(int offy){ this.offset_y -= offy; }
	public void moveDown(int offy){ this.offset_y += offy; }
	public void resetOffset(){ this.offset_x = 0; this.offset_y = 0; }
	public int getOffsetX(){ return this.offset_x; }
	public int getOffsetY(){ return this.offset_y; }
	
}
