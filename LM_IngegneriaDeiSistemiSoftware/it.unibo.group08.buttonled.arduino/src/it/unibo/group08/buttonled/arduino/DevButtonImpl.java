package it.unibo.group08.buttonled.arduino;
import java.util.Observable;

import it.unibo.buttonLed.devices.SysKbBridge;
import it.unibo.domain.interfaces.IDevInputImpl;
import it.unibo.is.interfaces.IActivityBase;
import it.unibo.is.interfaces.IBasicUniboEnv;
import it.unibo.system.SituatedPlainObject;
/*
 * A SituatedPlainObject  
 */
public class DevButtonImpl extends SituatedPlainObject 
					implements IDevInputImpl,  IActivityBase {
protected boolean isPressed = false;
	public DevButtonImpl(String name, IBasicUniboEnv env) {
		super("button("+name+")",env);
	}
	@Override
	public int getInput() throws Exception { return isPressed ?  1 : 0; }
 	@Override
	public void execAction(String cmd) {
  		isPressed = cmd.equals( SysKbBridge.repHigh );
  		//println( "execAction (before notify) -->" + getDefaultRep() );
  		this.setChanged();	//!!!!
		this.notifyObservers( isPressed );
 	}
 	//Used by some subclass
 	public void update( boolean v){
		String cmd = v ? SysKbBridge.repHigh : SysKbBridge.repLow ;
		//println( "update cmd -->" + cmd + " isPressed=" + isPressed);
		execAction( cmd );
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		String vs = ""+arg1;		
		//println( "update vs  -->" + vs );
  	 	update( vs.equals( SysKbBridge.repHigh ) );
 	}
	@Override
	public String getDefaultRep() {
 		try {
			return "sensor("+this.name+","+this.getInput()+")";
		} catch (Exception e) { return "sensor("+this.name+",null)"; }
	}
	@Override
	public String getName() {
 		return name;
	}
 }
