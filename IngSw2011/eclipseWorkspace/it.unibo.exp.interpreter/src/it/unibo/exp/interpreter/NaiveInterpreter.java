package it.unibo.exp.interpreter;

import it.unibo.exp.expr.impl.NumExp;
import it.unibo.exp.expr.impl.OpExp;
import it.unibo.exp.interfaces.*;

public class NaiveInterpreter {

	public double evalExp(IExp exp) throws Exception{
		return exp.eval();
	}
	public double eval(IExp exp) throws Exception{
		double v = 0;;
		if (exp instanceof INumExp)
			return ((INumExp) exp).getVal();
		else if (exp instanceof IOpExp) {
			double v1 = eval(((IOpExp) exp).getLeft());
			double v2 = eval(((IOpExp) exp).getRight());
			char op = ((IOpExp) exp).getOpName().charAt(0);
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
		throw new Exception("Expression type unknown " );
	}
	
/*
* =========================================
* TEST SECTION
* =========================================
*/
		
	protected void tests() throws Exception{
			testNum();
			testOneOp();
			testManyOp();
	}

	protected void testNum() throws Exception{
		INumExp exp = new NumExp(2);
 		double result = eval(exp);
 		System.out.println( "The result of 2 is: " + result + " == " + evalExp(exp) );
 	}
	

	protected void testOneOp() throws Exception{
		IExp exp = new OpExp( "*", new NumExp(2), new NumExp(3) );		
    	double result = eval(exp);
		System.out.println( "The result of 2*3 is: " + result + " == " + evalExp(exp) );
 	}

	protected void testManyOp() throws Exception{
		IExp e1 = new OpExp( "*", new NumExp(2), new NumExp(3) );		
		IExp e2 = new OpExp( "-", new NumExp(4), new NumExp(1) );
		IExp exp = new OpExp( "-", e1, e2 );	
    	double result = eval(exp);
		System.out.println( "The result of (2*3)-(4-1) is: " + result + " == " + evalExp(exp) );
	}
	
	public static void main(String[] args) throws Exception{
		NaiveInterpreter exprEval = new NaiveInterpreter();
		exprEval.tests();
	}
	
}
