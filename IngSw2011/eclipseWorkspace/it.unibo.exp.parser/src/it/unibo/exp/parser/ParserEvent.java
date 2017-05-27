package it.unibo.exp.parser;

import it.unibo.exp.expr.impl.*;
import it.unibo.exp.input.StringInputSupport;
import it.unibo.exp.interfaces.*;
import it.unibo.exp.lexer.Lexer;
import it.unibo.exp.token.*;



public class ParserEvent implements IParser{
	protected ISupportInput   inpDev;
	protected ILexer   lexer;
	protected IToken curToken;
	
	public ParserEvent( ){
 	}
	
	public ParserEvent(String sentence){
		inpDev = new StringInputSupport(sentence);
	}
 
	public ParserEvent(ILexer lexer){
		this.lexer = lexer;
	}
	
 	
	public IExp parse( ) throws Exception{
		curToken = lexer.next();
 		return E();		
	}
	
	public IExp parse( String s) throws Exception{
		inpDev = new StringInputSupport(s);
		lexer  = new Lexer(inpDev);
		return parse();		
	}
	
	public IToken getFirstTokenNonConsumed(){
		return lexer.getCurToken();
	}
	
	public IExp E() throws Exception{
	IToken myOp;
	IExp myExp,term2;
		//Produzione E := T
		myExp = T();
		if( myExp == null ) return null;
		while( curToken instanceof AddToken ) {
			 //Produzioni T := T { + T } | T { - T }
			myOp = curToken; //push the op
			curToken = lexer.next();
			term2 = T();
			if( term2 == null ) return null;
			myExp = new OpExp( myOp.getName(), myExp, term2 );
		}//while
		return myExp;	   
	}
	   
	public IExp T() throws Exception{
	IToken myOp;
	IExp myExp,fact2;
		//Produzione T := F
	   myExp = F();
	   if( myExp == null ) return null;
	 //Produzioni T := F { * F } | F { / F }
	   while( curToken instanceof MulToken ) {
		   myOp = curToken; //push the op
		   curToken = lexer.next();
		   fact2 = F();
		   if( fact2 == null ) return null;
		   myExp = new OpExp( myOp.getName(), myExp, fact2 );
	   }//while
	   return myExp;	   
	}

	public IExp F() throws Exception {
	IExp myExp = null;
//	   System.out.println("Parser F curToken=" + curToken);
		//Produzione F := N
		if( curToken instanceof NumToken ){
			NumToken num = (NumToken)curToken;
			curToken   = lexer.next();
			return new NumExp( num.getVal() ) ;
		}
		//Produzione F := ( E )
		if( curToken instanceof LParToken ){
			curToken = lexer.next();
			myExp = E();
			if( myExp == null ) return null;
			if( curToken instanceof RParToken ){
				curToken  = lexer.next();
				return myExp;
			}else return null;
		}
		else return null;
	}//F


}
