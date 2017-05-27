package it.unibo.exp.lexer;

import it.unibo.exp.interfaces.*;
import it.unibo.exp.token.EofToken;
 

public class LexerEvent extends AbstractCoreLexerASF implements ILexerEvent{
protected boolean initialized = false;
private IToken curToken = null;
private ISupportInput inpDev;
private boolean endOfJob = false;

  	public LexerEvent(ISupportInput inpDev) {
 		this.inpDev = inpDev;
	}
 
 	public void next() {
		try {
		while( curToken == null ){
			char curCh;
				curCh = inpDev.receiveChar();
				sfn(curCh); //calls emitToken
		}		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void emitToken(IToken token) {
		if( endOfJob && token == null ){			
			System.out.println("LexerEvent emitToken " + new EofToken() );
		}else
			System.out.println("LexerEvent emitToken " + token + " endOfJob=" + endOfJob);
	}
	
}
 

