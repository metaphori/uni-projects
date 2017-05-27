package it.unibo.exp.lexer;
 
import it.unibo.exp.interfaces.ILexer;
import it.unibo.exp.interfaces.ISupportInput;
import it.unibo.exp.interfaces.IToken;
import it.unibo.exp.token.*;


public class Lexer extends AbstractCoreLexerASF implements ILexer{
protected boolean initialized = false;
private IToken curToken = null;
private IToken lastToken = null;
private ISupportInput inpDev;
private boolean endOfJob = false;

 	public Lexer(ISupportInput inpDev) {
 		this.inpDev = inpDev;
	}

	public IToken getCurToken(){
		return lastToken;
	}

 	public IToken next() throws Exception {
		if( endOfJob ) return new EofToken();
		while( curToken == null && !endOfJob){
			char curCh = inpDev.receiveChar();
			sfn(curCh); //calls emitToken
		}
		if( endOfJob && curToken == null ) return new EofToken();
		IToken token = curToken;
		lastToken = curToken;
		curToken  = null;
		return token;
	}
	
	protected void emitToken(IToken token) {
		System.out.println("Lexer emitToken " + token + " endOfJob=" + endOfJob);
		if( token instanceof EofToken ) endOfJob = true;
		else curToken = token;				
	}
	
}
 

