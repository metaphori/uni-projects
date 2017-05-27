package it.unibo.buttonLed.impl;
import it.unibo.is.interfaces.IBasicEnvAwt;
import it.unibo.is.interfaces.IBasicUniboEnv;
import it.unibo.buttonLed.interfaces.IColor;
//import it.unibo.buttonLedSystem.BLSSysKb.LedColor;

import it.unibo.buttonled.impl.Led;

import java.awt.Color;
import java.awt.Panel;
 
public class LedWithGui extends Led{
	private IBasicUniboEnv env;
 	protected Panel p ;
	public LedWithGui( String name, IBasicEnvAwt env, IColor color ) throws Exception{
		super( name, color );
		this.env = env;
 		configure();
 		show("");
 	}	
	protected void configure(){
		p= new Panel();
		if( color.getStringRepr().equals("GREEN"))
			p.setBackground(Color.green);
		else p.setBackground(Color.red);
 		if( env !=null) ((IBasicEnvAwt)env).addPanel(p);
 		p.validate();
 		this.turnOff();
	}	
	@Override
	public void doSwitch() {
		 super.doSwitch();	
		 show("");
	}
	@Override
	public void turnOn() {
		super.turnOn();	
		 show("");
	}
	@Override
	public void turnOff() {
		super.turnOff();
		 show("");
	}
	@SuppressWarnings("deprecation")
	protected void show( String msg ){	
		if( this.isOn() ){
			p.resize(15, 15);
		}
		else p.resize(5, 5);
		p.validate();
	}
}
