package it.unibo.group08.buttonled.system;
import java.util.Observable;

import it.unibo.buttonLed.interfaces.IBLSControl;
import it.unibo.domain.interfaces.IDevLed;
import it.unibo.is.interfaces.IBasicUniboEnv;
import it.unibo.system.SituatedPlainObject;

public class ButtonLedSystemController extends SituatedPlainObject implements IBLSControl {
  	protected String curstate = "S0";
 	protected boolean on = false;
  	protected IDevLed ledLogical;
  	
 	public ButtonLedSystemController(IBasicUniboEnv env, IDevLed ledLogical){
 		super(env);
 		this.ledLogical = ledLogical;
 	}
 	public void update(Observable arg0, Object isOn) {
  		//println("ButtonLedSystemController update cmd="  + cmd  );	
		controllerAsFsm( ""+isOn );
	}
 	protected void controllerAsFsm(String cmd){
		String result = controllerFsm(cmd);
   		//println("ButtonLedSystemController curstate=" + curstate + "/result=" + result);
		if( result != null ){
			if( result.equals("on")){
				if(ledLogical !=null) ledLogical.turnOn();		
 				println(" --- ButtonLedSystemController FSM done ON"   );
			}
			if( result.equals("off")){
				if(ledLogical !=null) ledLogical.turnOff();
 				println(" --- ButtonLedSystemController FSM done OFF"   );
			}
		}		
	}
	protected String controllerFsm(String inp){
		if( curstate.equals("S0")){
			if( inp.equals("true")){
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
 }
