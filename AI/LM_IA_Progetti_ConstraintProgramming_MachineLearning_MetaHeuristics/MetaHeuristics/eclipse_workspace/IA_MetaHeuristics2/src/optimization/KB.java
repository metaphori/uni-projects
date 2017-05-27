package optimization;

public class KB {

	public static int seed = 1;
	private static java.util.Random random;
	
	public static java.util.Random getRandom(){
		if(random==null)
			random = new java.util.Random(seed);
		return random;
	}
	
}
