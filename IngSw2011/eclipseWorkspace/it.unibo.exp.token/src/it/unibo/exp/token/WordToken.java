package it.unibo.exp.token;

import it.unibo.exp.interfaces.IToken;

/**
 * @author Antonio Natali
 */
public  class WordToken implements IToken{

	private String name;

	public WordToken(String name){
		this.name = name;
	}
 
	public String getName(){
		return name;
	}
	
	public String toString(){
		return name;
	}

}