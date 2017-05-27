package it.unibo.exp.token;

/**
 * @author Antonio Natali
 * @version 1.0
 * @created 21-ott-2008 18.11.48
 */
public class OtherToken extends WordToken {

	public OtherToken(char ch){
		super(ch + " (otherToken)");
	}

 		
	public char getChar(  ){
		return toString().charAt(0);
	}

}