package it.unibo.exp.interpreter;

import it.unibo.exp.interfaces.*;
import it.unibo.exp.expr.impl.*;
import java.util.Stack;

public class ExpEvaluator implements IExpVisitor{
protected Stack<Double>  stack = new Stack<Double>();
	//NumExp
	public void visit(INumExp e) throws Exception {
		double v = eval( e );
		stack.push( new Double(v) );
	}
	
	public  void visit( IOpExp e ) throws Exception{
		double v = eval( e );
		stack.push( new Double(v) );		
	}
	
//------------------------
	public double getResult() throws Exception{
		if( !stack.empty() ) return stack.pop().doubleValue();
		else throw new Exception("No result available");
	}

/*
 * =========================================
 * EVAL SECTION
 * =========================================
 */
 	//eval Numexp
	protected double eval(INumExp e) throws Exception{
//		System.out.println( "*** eval NumExp " + e.getVal() );
		return e.getVal();
	}
	
	protected double eval(IOpExp e) throws Exception{
//		System.out.println( "*** eval OpExp " + e.getOpName() );
		e.getLeft().accept(this);
		Double leftSon =  stack.pop();
		e.getRight().accept(this);
		Double rightSon = stack.pop();
		double v = apply(e.getOpName(), 
					leftSon.doubleValue(), 
					rightSon.doubleValue() );
		return v;
	}

	/* 
	 * apply operator
	 */
	protected double apply(String op, double v1, double v2) throws Exception{
		double v = 0;
		switch( op.charAt(0) ){
		case '+' : v = v1 + v2;break;
		case '-' : v = v1 - v2;break;
		case '*' : v = v1 * v2;break;
		case '/' : v = v1 / v2;break;
		}
//		System.out.println( "*** apply " + op + " v1="+ v1 + " v2=" + v2 + " v=" + v );
		return  v;
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
 		exp.accept(this);
		Double result =  getResult();
		System.out.println( "The result of 2 is: " + result );
 	}
	

	protected void testOneOp(){
		IExp exp = new OpExp( "*", new NumExp(2), new NumExp(3) );		
  		try {
			exp.accept(this);
			Double result =  getResult();
			System.out.println( "The result of 2*3 is: " + result );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void testManyOp(){
		IExp e1 = new OpExp( "*", new NumExp(2), new NumExp(3) );		
		IExp e2 = new OpExp( "-", new NumExp(4), new NumExp(1) );
		IExp exp = new OpExp( "-", e1, e2 );	
 		try {
			exp.accept(this);
			Double result =  getResult();
			System.out.println( "The result of (2*3)-(4-1) is: " + result );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception{
		ExpEvaluator exprEval = new ExpEvaluator();
		exprEval.tests();
	}

}
