package it.unibo.exp.tests.lexer;

import it.unibo.exp.input.StringInputSupport;
import it.unibo.exp.interfaces.*;
import it.unibo.exp.lexer.LexerEvent;
import it.unibo.exp.token.*;

import java.util.Vector;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
 


public class TestLexerEvent extends TestCase {

private ILexerEvent lexer; 
private ISupportInput inpDev;

	public TestLexerEvent(String name) {
		super(name);
	}

	protected  void setUp() {
 	}

	protected  void setUp(String sentence) {
 		System.out.println("setUp     " + sentence);
		inpDev  = new StringInputSupport(sentence);
		lexer	= new LexerEvent(inpDev);
 	}

	protected  void tearDown() {
 	}

	public static Test suite() {
		TestSuite ts = new TestSuite();
			ts.addTest( new TestLexerEvent("testNoExpToken") );
//			ts.addTest( new TestLexerEvent("testSentence") );
		return ts;
	}	
	
	public void testNoExpToken(){
		System.out.println(" ====================== ");
		System.out.println("  testNoExpToken     ");
 		System.out.println(" ====================== ");
 		setUp(" k ab \n");
 		//Create expected answer sequence
		Vector<IToken> answer = new Vector<IToken>();
		answer.add( new OtherToken('k') );
		answer.add( new OtherToken('a') );
		answer.add( new OtherToken('b') );
		answer.add( new OtherToken('\n') );
 		doJob(answer);
	}

	public void testSentence(){
		System.out.println(" ====================== ");
		System.out.println("  testSentence     ");
 		System.out.println(" ====================== ");
//		setUp("27");
		setUp(" 2+3 * 5 = 17;");
 		//Create expected answer sequence
		Vector<IToken> answer = new Vector<IToken>();
		answer.add( new NumToken(2) );
		answer.add( new AddToken("+") );
		answer.add( new NumToken(3) );
 		answer.add( new MulToken("*") );
		answer.add( new NumToken(5) );
		answer.add( new OtherToken('=') );
		answer.add( new NumToken(17) );
		answer.add( new OtherToken(';') );
		doJob(answer);
	}

	public void testSentence1(){
		System.out.println(" ====================== ");
		System.out.println("  testSentence1     ");
 		System.out.println(" ====================== ");
		setUp("1 + xy 2+2*x-4");
 		//Create expected answer sequence
		Vector<IToken> answer = new Vector<IToken>();
		answer.add( new NumToken(1) );
		answer.add( new AddToken("+") );
		answer.add( new OtherToken('x') );
		answer.add( new OtherToken('y') );
		answer.add( new NumToken(2) );
		answer.add( new AddToken("+") );
		answer.add( new NumToken(2) );
		answer.add( new MulToken("*") );
		answer.add( new OtherToken('x') );
		answer.add( new AddToken("-") );
		answer.add( new NumToken(4) );
		doJob(answer);
	}

	protected void doJob(Vector<IToken> answer){
		IToken curToken = null;
		try {
			lexer.next();
			int i=0;
			System.out.println("doJob " + curToken);
			while( ! (curToken instanceof EofToken ) ){
				assertEquals("testSentence", 
						answer.elementAt(i).toString(), curToken.toString() );	
				i++;
				lexer.next();
				System.out.println("doJob " + curToken);
			}
		} catch (Exception e) {
			fail();
		}				
	}
	
 	
	
}
