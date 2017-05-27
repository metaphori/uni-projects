package it.unibo.contaKm.domain;

 
 
public interface IContaKm {
 	public final int  LIMIT = 9999; //property	
 	public void inc() throws Exception;	//modifier
 	public int getVal();	//provider
  }