package it.unibo.ccc.chiefvehicle;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.unibo.is.interfaces.IActivity;
import it.unibo.is.interfaces.IBasicEnv;
import it.unibo.is.interfaces.IBasicEnvAwt;
import it.unibo.is.interfaces.IIntent;

import it.unibo.ccc.domain.SysKb;
import it.unibo.ccc.system.interfaces.ICCC;
import it.unibo.exp.interfaces.IExp;
import it.unibo.exp.interfaces.IParser;
import it.unibo.exp.interpreter.ExpEvaluator;
import it.unibo.exp.parser.Parser;

public class CCCInputControl implements IActivity {
	protected ICCC convoy;
	protected IBasicEnvAwt env;
	private IParser parser;
	private ExpEvaluator eval;
	
	public CCCInputControl(IBasicEnvAwt env, ICCC c){
		this.convoy = c;
		this.env = env;
		
		parser = new Parser();
		eval = new ExpEvaluator();
	}
	
	@Override
	public void execAction() {
		return;
	}

	@Override
	public void execAction(String cmd) {

		env.println("["+cmd+"]");		
		
		String scheme = SysKb.getCmdScheme(cmd);
		String content = SysKb.getCmdContent(cmd);
	
		if(scheme.equals(SysKb.command) ){
			// chief sent a command for the convoy (setSpeed/start/stop)
			
			if( content.equals(SysKb.start)){
				try{
					convoy.startConvoy();
				}catch(Exception exc){
					env.println(exc.toString());
				}
				
			} else if( content.equals(SysKb.stop)){
					convoy.stopConvoy();	
			}
			
		}
		else if(scheme.equals(SysKb.setSpeed)){
			try{
				double speed;
				if( content.indexOf(".") != -1){ // IExp doens't handle decimals
					speed = Double.parseDouble(content);
				} else{
					IExp speedExp = parser.parse(content);
					speedExp.accept(eval);
					speed = eval.getResult();
				}
				
				double s = SysKb.FromFormatTo( SysKb.SpeedChiefFormat, SysKb.SpeedBaseFormat, speed);
				env.println("[Speed Set = " +s + "]");
				convoy.setConvoySpeed( s );
			}catch(Exception exc){
				env.println(exc.toString());
				exc.printStackTrace();
			}
		}
		else if( isAVehicle(scheme) ){
			// a vehicle <N> sent a command to CCC ( status or speed info )
			if(content.equals( SysKb.working )){
				convoy.notifyStatus( Integer.parseInt( scheme.substring(1) ) , true);
				
			} else if(content.equals( SysKb.notWorking)){
				convoy.notifyStatus( Integer.parseInt( scheme.substring(1) ) , false);
				
			} else{ // speed information
				
				double speed = Double.parseDouble(content);
				/*if( convoy.getConvoySpeed() > speed ){
					try{
					convoy.setConvoySpeed(speed);
					}catch(Exception exc){}
				} else if( convoy.getConvoySpeed()<speed){
					convoy.stopConvoy();
				}*/
				convoy.notifySpeed(Integer.parseInt( scheme.substring(1) ), speed);
				
				
			}
		
		} 
		else{
			env.println("Sorry, I can't understand your command");
		}
		
	}
	
	public boolean isAVehicle(String cmd){
		Pattern p = Pattern.compile("^v[0-9]+$");
		Matcher m = p.matcher(cmd);
		return m.find();
	}

	@Override
	public void execAction(IIntent input) {
		return;
	}

	@Override
	public String execActionWithAnswer(String cmd) {
		return null;
	}

}