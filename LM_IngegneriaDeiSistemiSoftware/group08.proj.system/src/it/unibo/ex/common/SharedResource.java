package it.unibo.ex.common;

public class SharedResource {
public static SharedResource res = null;
private int nCall = 0;
private int n = 0;

public static SharedResource getInstance(){
	if( res == null ) res = new SharedResource();
	return res;
}
	public void inc(){
		nCall++;
		n++;
	}
	public void dec(){
		nCall++;
		n--;
	}	
	public int getN(){
		return n;
	}
	public int getNcall(){
		return nCall;
	}
}
