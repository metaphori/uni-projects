package it.unibo.exp.interfaces;

/**
 * @author Antonio Natali
 * @version 1.0
 */
public interface IComposedExp extends IExp {
	public IExp getLeft();
	public String getOpName();
	public IExp getRight();
}