package optimization;

public interface IProblem {

	public double eval(ISolution s);
	
	public boolean isValid(ISolution s);
	
}
