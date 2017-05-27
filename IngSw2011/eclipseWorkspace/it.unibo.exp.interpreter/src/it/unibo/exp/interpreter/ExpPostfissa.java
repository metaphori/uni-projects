package it.unibo.exp.interpreter;

import it.unibo.exp.interfaces.*;
import it.unibo.exp.expr.impl.*;
import java.util.Stack;

public class ExpPostfissa implements IExpVisitor{
protected Stack<String>  stack = new Stack<String>();

 	public String getResult() throws Exception{
		if( !stack.empty() ) return stack.pop() ;
		else throw new Exception("No result available");
	}

//------------------------
  
	//NumExp
	public void visit(INumExp e) throws Exception {
 		stack.push( ""+e.getVal() );
	}
	
	public  void visit( IOpExp e ) throws Exception{
		e.getLeft().accept(this);
		e.getRight().accept(this);
		String eRight =  stack.pop();
		String eLeft  =  stack.pop();
 		stack.push( eLeft + " " +  eRight + " " + e.getOpName() );		
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
		String result =  getResult();
		System.out.println( "The result of 2 is: " + result );
 	}
	

	protected void testOneOp(){
		IExp exp = new OpExp( "*", new NumExp(2), new NumExp(3) );		
  		try {
			exp.accept(this);
			String result =  getResult();
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
			String result =  getResult();
			System.out.println( "The result of (2*3)-(4-1) is: " + result );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception{
		ExpPostfissa exprEval = new ExpPostfissa();
		exprEval.tests();
	}

}
