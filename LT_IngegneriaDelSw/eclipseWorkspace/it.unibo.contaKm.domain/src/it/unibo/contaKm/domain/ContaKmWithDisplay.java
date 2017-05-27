package it.unibo.contaKm.domain;
  
import it.unibo.is.interfaces.IBasicEnvAwt;
 

public class ContaKmWithDisplay extends ContaKm {
protected IDisplay display;
	
	public ContaKmWithDisplay(IBasicEnvAwt env, IDisplay display){
		super(env);
		this.display = display; 
		display.update( toStr( getVal() ));
	}

	public void inc() throws Exception{
		super.inc();
 		display.update( toStr( getVal() ));
	}
	
	protected String toStr( int v ){
	String vs = ""+v;
		while( vs.length() < 6){
			vs = "0"+vs;
		}
		return vs;
	}

}
