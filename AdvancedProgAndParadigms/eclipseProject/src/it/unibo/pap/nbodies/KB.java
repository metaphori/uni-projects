package it.unibo.pap.nbodies;

/**
 * Knowledge base. It contains common properties for the system.
 */
public class KB {
	
	/* delta time (msecs) - the length of the time step */
	public static double DT = 0.01;

	public static final double G = 6.67; //6.67E-11;
	public static final double SOFT = 100; // softening param to avoid collisions
	public static final double HARD = 5000;
	public static final double IMPACT = 10;
	
	public static final int CANVAS_WIDTH = 800;
	public static final int CANVAS_HEIGHT = 450;

	public static final int N_STATES_EACH = 10000;
	public static final int MAX_BODIES = 200;
	public static double FF = 0.2; // FORM FACTOR on POSITIONS
}
