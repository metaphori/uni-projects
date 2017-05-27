package it.unibo.exp.interfaces;

/**
 * @author Antonio Natali
 */
public interface IExp {
	public void accept( IExpVisitor v) throws Exception;
	public double eval() throws Exception;
}