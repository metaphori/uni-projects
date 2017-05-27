package it.unibo.exp.token;

/**
 * @author Antonio Natali
 */
public class NumToken extends WordToken {

	private int value;

	public NumToken(int value){
		super("num");
		this.value=value;
	}

 
	public int getVal(){
		return value;
	}
	
	public String toString(){
		return ""+value;
	}

}