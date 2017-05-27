package it.unibo.ccc.exceptions;

public class CannotPerformException extends Exception {

	public CannotPerformException(String msg){
		super("Cannot perform operation: " + msg);
	}
	
}