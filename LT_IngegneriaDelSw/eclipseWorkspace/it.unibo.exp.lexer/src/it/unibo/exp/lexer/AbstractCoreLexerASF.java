package it.unibo.exp.lexer;

import it.unibo.exp.interfaces.IToken;
import it.unibo.exp.token.*;



/**
 * This class maps the UML ASF of the lexer into Java code
 * The ASF is represented by a state function (sfn)
 * and a machine function (mfn).
 * Each sate and each transition is mapped into a function.
 * The machine is a POJO that implements the state function (
 * and the machine function of an ASF by abstracting from any I/O
 * @author Antonio Natali
 * @version 1.0
 * @created 21-ott-2008 15.52.08
 */
public abstract class AbstractCoreLexerASF implements ISfnLexer{

	private boolean debug = false;	
	public enum State { TeminalSymState, NumState, MemoState, EndState, ErrorState }; 
	private State curState = State.TeminalSymState;
	private int curNum = 0;
	private char memoCh;

	protected abstract void emitToken(IToken token);

	public void sfn(char n){
		println("sfn curState="+curState +" input="+  n );		
	    switch (curState) {
 	    	case TeminalSymState 	: TeminalSymState(n); return;
	    	case NumState 			: NumState(n); return;
	    	case MemoState 			: MemoState(n); return;
	    	case EndState 			: ErrorState(n); return;
	    	case ErrorState 		: ErrorState(n); return;
//	    	default : ErrorState(n);
	    }//switch	    
	}
	
	/**
	 * curState must be set to the old state
	 * @param newState
	 * @param n
	 */
	public void mfn(State newState, char n){
		println("mfn curState="+curState +" input="+  n );		
	    switch (newState) {
	    	case TeminalSymState 	: 	emitTheToken( n ); 
	    								return;
	    	case NumState 			: 	if( curState == State.MemoState) 
											emitTheToken( memoCh );
	    								return;
	    	case MemoState 			:   if( curState == State.NumState )
	    										emitNumToken();
	    								else emitTheToken( memoCh );
										return;
	    	case EndState 			:  	if( curState == State.NumState )
	    										emitNumToken();
	    								else if( curState == State.MemoState )
	    										emitTheToken(memoCh);
	    								emitToken( new EofToken( ) ); 
	    								return;
	    	case ErrorState 		: 	if( curState == State.NumState )
	    										emitNumToken();
										else if( curState == State.MemoState )
													emitTheToken(memoCh);
										emitToken( new OtherToken( 'E' ) ); 
	    								return;
	    	default : 		if( curState == State.MemoState) 
								emitTheToken( memoCh );
	    					emitToken( new OtherToken( 'E' ) );
	    }//switch	  
	}
	
	protected void emitNumToken(){
		emitToken( new NumToken( curNum ) );
		curNum = 0;
	}

	/* ======================================================
	 * For each state we define a local function
	/* ====================================================== */  
	protected void TeminalSymState(char n){
//		println("TeminalSymState input="+ (char)n  );
		if ( isEof(n) ){  
			transitionToEndState( n );
			return;
		}
		if( isDigit(n) ){
			transitionToNumState( n );
			return;
		}
		if( isTerminal(n) ){
    		transitionMealy( State.TeminalSymState, n);
			return;
		}
    	transitionMealy( State.TeminalSymState,n);
	}

	protected void NumState(char n){
		println("NumState input="+  n + " (int)" + (int)n + " curNum=" + curNum);
		if ( isEof(n) ){  
			transitionToEndState( n );
			return;
		}
		if ( isTerminal(n) ){  
			transitionToMemoState( n );
			return;
		}
		if( isDigit(n) ){
			transitionToNumState( n );
			return;
		}
		transitionToMemoState( n );
	}

	protected void MemoState( char n ){
		println("MemoState input="+ (char)n + " memoCh " + (char)memoCh);
		if ( isEof(n) ){
			transitionToEndState( n );
			return;
		}
		if( isDigit(n) ){
			transitionToNumState( n );
			return;
    	}		
		if ( isTerminal(n) ){  
			transitionToMemoState(n);
			return;
		}
		transitionToMemoState( n );
	}


	protected void ErrorState(char n){
		transitionMealy( State.ErrorState, n );
	}
	
	/* ======================================================
	 * INPUT classification
	/* ====================================================== */
	
	protected boolean isDigit( char n ){
		return (n >= '0' && n <= '9');
	}

	protected boolean isEof( char n ){
		return ( n == 65535 || n == 255 ); //255 comes from TCP
	}

	protected boolean isTerminal( char n ){
		return (isBraket(n) || isAddOp(n) || isMulOp(n) );
	}

	protected boolean isAddOp( char n ){
		return (n == '+' || n == '-');
	}
	
	protected boolean isMulOp( char n ){
		return (n == '*'|| n == '/');
	}
	
	protected boolean isBraket( char n ){
		return (n == '(' || n == ')');
	}

	/* ======================================================
	 * TRANSITIONS
	/* ====================================================== */
	
	protected void transitionToEndState( char n ){
		println("transitionToEndState " + n + " curState=" + curState);
		mfn( State.EndState, n );
		curState = State.EndState;
	}

	protected void transitionToMemoState( char n ){
		mfn( State.MemoState, n );
		memoCh = n;
		curState = State.MemoState;
	}

	protected void transitionMealy( State newState, char n){
		mfn( newState,n );
		curState = newState;
	}

	protected void transitionToNumState( char n ){
		curNum = curNum*10+(n-'0');
		mfn( State.NumState, n );
    	curState = State.NumState; 	
	}

	protected void emitTheToken( char n ){
		IToken token; 
		if( n == ' ') return;  //avoid to generate OtherToken for a blank char
		switch (n) {
	      case '(':   token = new LParToken( ); break;
	      case ')':   token = new RParToken( ); break;	
	      case '+':   token = new AddToken( "+" ); break;
	      case '-':	  token = new AddToken( "-" ); break;
	      case '*':   token = new MulToken( "*" ); break;
	      case '/':   token = new MulToken( "/" ); break;	
	      default: 	  token = new OtherToken( n );		
		}//switch
		emitToken( token );
	}

	/* ======================================================
	 * UTILITIES
	/* ====================================================== */
	protected void println( String msg ){
		  if( debug ) System.out.println("*** CoreLexerASF " + msg );
	}
}