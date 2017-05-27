package it.unibo.exp.expr.impl;

import it.unibo.exp.interfaces.*;

/**
 * @author Antonio Natali
 */
public class OpExp extends Exp implements IOpExp {

	private String opName;
	private IExp left;
	private IExp right;

	public OpExp(String opName, IExp left, IExp right ){
		this.left 	= left;
		this.right 	= right;
		this.opName = opName;
	}


	public IExp getLeft(){
		return left;
	}

	public IExp getRight(){
		return right;
	}

	public String getOpName(){
		return opName;
	}

	public void accept(IExpVisitor v) throws Exception {
		v.visit(this);
	}
	
	public double eval() throws Exception{
		double v = 0;;  
			double v1 = getLeft().eval() ;
			double v2 = getRight().eval() ;
			char op =   getOpName().charAt(0);
			switch (op) {
				case '+':
					v = v1 + v2;
					break;
				case '-':
					v = v1 - v2;
					break;
				case '*':
					v = v1 * v2;
					break;
				case '/':
					v = v1 / v2;
					break;
				default : throw new Exception("Operator unknown " + op);
			}// else : TODO
			return v; 		
	}
}