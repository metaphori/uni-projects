package it.unibo.contaKm.domain;

import it.unibo.system.SituatedPlainObjectObservable;
import it.unibo.is.interfaces.IBasicEnvAwt;
 
public class ContaKm  extends SituatedPlainObjectObservable implements IObservableContaKm {
	private int counter;
	
	public ContaKm(IBasicEnvAwt env) {
		super(env);
		try {
			counter = 0;
			ensure( invariant("build") );
		} catch (Exception e) {
//			e.printStackTrace();
		}
	}
 
	public void inc() throws Exception{
		if( counter  == IContaKm.LIMIT ) counter = 0  ;
		else counter++;
		ensure( invariant("inc") );
//		System.out.println("*** notify " + counter);
 		raiseObservableEvent( ""+counter ) ;	//ADDDED since it is observable
	}

	public int getVal(){
		return counter ; 
	}

	 /**
	 * Test dell'invariante di classe
	 */
	protected void ensure( boolean v ) throws Exception{
	int curVal = counter ;
		if( !v ){
			counter = 0 ;
			throw new Exception("contaKm error: "+curVal);
		}
	}//ensure
	
	protected boolean invariant(String opName)  {
//		System.out.println(" check  "+ opName + " val=" + counter.getVal());
		return (counter  >= 0 && counter  <= IContaKm.LIMIT) ;
	}

}