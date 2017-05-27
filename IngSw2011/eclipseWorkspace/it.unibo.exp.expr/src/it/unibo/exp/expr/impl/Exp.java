package it.unibo.exp.expr.impl;
import it.unibo.exp.interfaces.*;

/**
 * @author Antonio Natali
 */
public abstract class Exp implements IExp {
	public abstract double eval() throws Exception;
}

/*
public boolean isAtomic(){
 return this.getClass().getInterfaces()[0].getName().equals(IAtomicExp.class.getName());
}
*/
