package it.unibo.exp.input;

import it.unibo.exp.interfaces.ISupportInput;

public class StringInputSupport implements ISupportInput {

	private char eofCh = 65535;
	private  String sentence;
	private int lastConsumed;
	private boolean isClosed;
	
	public StringInputSupport( String sentence ){
		this.sentence 	= sentence;
		lastConsumed 	= 0;
		System.out.println("Created StringIOSupport for -> " + sentence);
		isClosed = false;
	}
	
	public boolean isEmpty() throws Exception{
		return sentence.length() == lastConsumed;
	}

	public void open() throws Exception{
	}
	
	public void close() throws Exception{
		isClosed = true;
	}
	
	public boolean isClosed() throws Exception{
		return isClosed ;
	}

	public String receive() throws Exception{	
		return sentence;
	}

	public char receiveChar() throws Exception{		
		return (char)readChar();
	}

	protected char readChar() throws Exception{
		 if( sentence.length() == lastConsumed ) 
			 	return eofCh;
		 else{
			 char curCh = sentence.charAt(lastConsumed++);
			 return curCh;
		 }			  
	}//readChar

}
