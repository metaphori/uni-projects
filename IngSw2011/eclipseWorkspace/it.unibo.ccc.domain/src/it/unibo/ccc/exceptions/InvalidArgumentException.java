package it.unibo.ccc.exceptions;

public class InvalidArgumentException extends Exception {

	public InvalidArgumentException(String msg){
		super("Invalid argument exception: " + msg);
	}
	
}
