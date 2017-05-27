package it.unibo.exp.expr.impl;
import it.unibo.exp.interfaces.*;

/**
 * @author Antonio Natali
 */
public class NumExp extends Exp implements INumExp {
	private int value;

	public NumExp(int value){
		this.value = value;
	}

	public double getVal(){
		return value;
	}

	public String toString(){
		return ""+value;
	}

	public void accept(IExpVisitor v) throws Exception {
		v.visit(this);
	}

	public double eval() throws Exception{
		return getVal();
	}
 
}