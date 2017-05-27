package it.unibo.buttonLedSystem;
import java.util.Observable;

import it.unibo.buttonLed.interfaces.ILed;
import it.unibo.domain.interfaces.IBuzzerGpio;
import it.unibo.domain.interfaces.IController;
import it.unibo.domain.interfaces.ICounterController;
import it.unibo.domain.interfaces.IDevLed;
import it.unibo.is.interfaces.IBasicUniboEnv;
import it.unibo.is.interfaces.IObservable;
import it.unibo.is.interfaces.IOutputView;

public class ButtonLedSystemControllerFull implements IController {
	protected int nOfLowHighEdges = 0;
 	protected String curstate = "S0";
 	protected boolean on = false;
 	protected int nSwitch = 0; 
 	protected IObservable basicButton; 	
  	protected ICounterController counterCtrl;
 	protected IBuzzerGpio buzzer;
	protected IDevLed ledGreen;
 	protected IDevLed ledRed;
 	protected IBasicUniboEnv env; 	
 	
 	public ButtonLedSystemControllerFull(IBasicUniboEnv env){
 		this.env = env;
 	}
 	public void update(Observable arg0, Object isOn) {
 		doJob(""+isOn); //TODO: data model
	}
	@Override
	public void doJob(String cmd) {
 		//println("ButtonLedSystemController doJob cmd="  + cmd  );	
		controllerAsFsm( cmd );
	}
	protected void controllerSuperSmart(String cmd) {
		println("ButtonLedSystemController controllerSuperSmart cmd="  + cmd  );	
		if(cmd.contains("HIGH") || cmd.contains("true")){
			nSwitch++;
			if( ledGreen != null && ledGreen.isOn() ){
				ledGreen.turnOff();
				if(buzzer!=null)  buzzer.turnOff();
				if(ledRed !=null) ledRed.turnOn();		
				if(counterCtrl != null ) counterCtrl.stop();
				//System.out.println(" --- ButtonLedSystemController ledSwitch done OFF"   );
			}
			else {
				if( ledGreen != null ) ledGreen.turnOn();		
				if( buzzer!=null )     buzzer.turnOn();
				if(ledRed !=null)      ledRed.turnOff();		
				if(counterCtrl != null ) counterCtrl.start();
				//System.out.println(" --- ButtonLedSystemController ledSwitch done ON"   );
			}		
		}
	}		
	protected void controllerAsFsm(String cmd){
		String result = 
 				controllerFsm(cmd);
				//controllerSmartFsm(cmd);	//Optimization 1
  		//println("ButtonLedSystemController curstate=" + curstate + "/result=" + result);
		if( result != null ){
			if( result.equals("on")){
				if(ledGreen !=null) ledGreen.turnOn();		
				if(ledRed !=null) ledRed.turnOff();		
				if(counterCtrl != null ) counterCtrl.start();
				if(buzzer!=null) buzzer.turnOn();
				println(" --- ButtonLedSystemController FSM done ON"   );
			}
			if( result.equals("off")){
				if(ledGreen !=null) ledGreen.turnOff();
				if(ledRed !=null) ledRed.turnOn();		
				if(counterCtrl != null ) counterCtrl.stop();
				if(buzzer!=null) buzzer.turnOff();
				println(" --- ButtonLedSystemController FSM done OFF"   );
			}
		}		
	}
	protected String controllerFsm(String inp){
		if( curstate.equals("S0")){
			if( inp.equals("true")){
				nOfLowHighEdges++;
				curstate = "SOdd";
				return "on";
			}else  return null; //Exception
		}
		if( curstate.equals("SOdd")){
			if( inp.equals("false")){
				curstate = "SOn";
				return "";
			}else   return null; //Exception
		}
		if( curstate.equals("SOn")){
			if( inp.equals("true")){
				nOfLowHighEdges++;
				curstate = "SEven";
				return "off";
			}else  return null; //Exception
		}
		if( curstate.equals("SEven")){
			if( inp.equals("false")){
				curstate = "S0";
				return "";
			}else return null; //Exception
		}
		return null;
 	}	
	//Optimization 1
	protected String controllerSmartFsm(String inp){
		if( inp.equals("true")){
			nOfLowHighEdges++;
			if( nOfLowHighEdges % 2 != 0 ) return "on";
			else return "off";
		}else return "";
	}
	public String getInfo(){
		return ""+nOfLowHighEdges;
	}
	//--------------------------------------------------	
		public void setButton( IObservable basicButton){
	 		this.basicButton = basicButton;
		}
		public void setLedGreen( IDevLed led){
			this.ledGreen = led;
		}
		public void setLedRed( IDevLed led){
			this.ledRed = led;
		}
		public void setBuzzer( IBuzzerGpio buzzer){
			this.buzzer = buzzer;
		}
		public void setCounter( ICounterController counterCtrl ){
			this.counterCtrl = counterCtrl;
		}		
		protected void println(String msg){
			if( env != null ) env.getOutputView().addOutput( msg );
			else System.out.println( msg );
		}
}
