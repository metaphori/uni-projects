package it.unibo.pap.nbodies;

import it.unibo.pap.nbodies.physics.Acceleration;
import it.unibo.pap.nbodies.physics.Position;
import it.unibo.pap.nbodies.physics.Velocity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * A collection of utility methods.
 */
public class Utils {

	/***********
	 * LOGGING *
	 ***********/
	public static void log(String s){
		System.out.print(s); System.out.flush();
	}
	public static void lognl(String s){
		log(s+"\n");
	}
	
	/************************
	 * GENERATION OF BODIES *
	 ************************/
	public static BodiesConfiguration generateRandomBodies(){
		Random random = new Random(System.currentTimeMillis());
		return generateRandomBodies(random.nextInt(KB.MAX_BODIES)+1);
	}
	
	public static BodiesConfiguration generateRandomBodies(int howmany){
		Random random = new Random(System.currentTimeMillis());
		int nbodies = howmany;
		if(nbodies==0){
			nbodies = random.nextInt(KB.MAX_BODIES)+1;
		}
		ArrayList<Body> result = new ArrayList<Body>(howmany);
		
		for(int i=0; i<nbodies; i++){
			Position p = new Position( 
					random.nextInt(KB.CANVAS_WIDTH)-KB.CANVAS_WIDTH, 
					random.nextInt(KB.CANVAS_HEIGHT)-KB.CANVAS_HEIGHT);
			Velocity v = new Velocity( 0, 0 );
			Acceleration a = new Acceleration(0,0);
			result.add( new Body(random.nextInt(10000), p, v, a) );
		}
		
		return new BodiesConfiguration(result);
	}

	public static BodiesConfiguration generateBodiesFromFile(File selectedFile) {
		ArrayList<Body> result = new ArrayList<Body>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(selectedFile));
			String line;
			
			while( (line = br.readLine())!=null ){
				String[] parts = line.split(",");
				System.out.println(line);
				if(!line.startsWith("#") && parts.length==7){
					double pos_x = Double.parseDouble(parts[0]);
					double pos_y = Double.parseDouble(parts[1]);
					double vel_x = Double.parseDouble(parts[2]);
					double vel_y = Double.parseDouble(parts[3]);
					double acc_x = Double.parseDouble(parts[4]);
					double acc_y = Double.parseDouble(parts[5]);
					double mass = Double.parseDouble(parts[6]);
					result.add( 
							new Body(mass, 
									 new Position(pos_x, pos_y),
									 new Velocity(vel_x, vel_y),
									 new Acceleration(acc_x, acc_y))
					);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new BodiesConfiguration(result);
		
	}
	
}
