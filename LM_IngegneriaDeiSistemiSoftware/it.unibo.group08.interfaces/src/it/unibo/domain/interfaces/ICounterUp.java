package it.unibo.domain.interfaces;
public interface ICounterUp {
 	public final int  LIMIT = 9; //property	
 	public void inc() throws Exception;	//modifier
 	public int getVal();	//provider 
 	public void show();
}