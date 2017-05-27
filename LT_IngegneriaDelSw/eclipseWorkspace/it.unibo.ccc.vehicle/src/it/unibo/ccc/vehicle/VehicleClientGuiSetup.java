package it.unibo.ccc.vehicle;

import it.unibo.baseEnv.basicFrame.*;
import it.unibo.baseEnv.basicgui.input.InputPanel;
import it.unibo.ccc.domain.SysKb;
import it.unibo.ccc.domain.impl.*;
import it.unibo.ccc.domain.interfaces.*;
import it.unibo.ccc.gui.DisplayWithPanel;
import it.unibo.ccc.system.interfaces.ICCC;
import it.unibo.ccc.system.interfaces.IConvoyVehicleFacade;
import it.unibo.contaKm.domain.IDisplay;

import it.unibo.is.interfaces.*;
import it.unibo.supports.FactoryProtocol;
import it.unibo.supports.tcp.FactoryTcpProtocol;
import it.unibo.supports.udp.FactoryUdpProtocol;

public class VehicleClientGuiSetup {
	private FactoryProtocol factory;
	private IBasicEnvAwt env;
	private IConvoyVehicleFacade convoy;
	protected InputPanel ipQueryPanel;
	
	public void doJob(){
		init();
		configure();
		start();
	}
	
	public void init(){
		env = new EnvFrame();
		env.init();
		
	}
	
	public void configure(){
		
		factory = new FactoryTcpProtocol(env,"ContaKmClientGuiSetup");	
		
		IActivity setIpControl = new SetIpControl(env);
		ipQueryPanel =  new InputPanel( "IP","localhost", env.getOutputView(), setIpControl);
    	env.addPanel( ipQueryPanel );
    	int nn = 0;
    	IOutputView view = env.getOutputView();
    	
    	while( SysKb.hostName==null && nn < 10){
    		view.addOutput("Please insert IP");
    		try {
				Thread.sleep(3000);
				nn++;
			} catch (InterruptedException e) {
 				e.printStackTrace();
			}
    	}
    	if( SysKb.hostName==null ) return;
    	env.removePanel(ipQueryPanel);		
		
	}
	
	public void start(){
		
		try{
			convoy = new CCCProxy(env,factory, SysKb.hostName, SysKb.serverPort);
		} catch(Exception exc){
			env.println("Impossibile connettere il proxy: " + exc.getMessage());
			return;
		}
		ConvoyVehicle v = new ConvoyVehicle(env, convoy, "ThisVehicle");
		
		IDisplay d1 = new DisplayWithPanel(env, SysKb.SpeedFormat.KmPerH); 
		v.addObserver(d1);
		IDisplay d2 =  new DisplayWithPanel(env, SysKb.SpeedFormat.KmPerS);
		v.addObserver(d2);
		
		try{
			v.setSpeed(0); // 0.01 km/s == 10 km/h
		} catch(Exception e){}
		
		VehicleInputControl vic = new VehicleInputControl(env, v); // allows the vehicle to inc/dec speed
		env.addCmdPanel("Commands for simulation", new String[]{ SysKb.incSpeed, SysKb.decSpeed, SysKb.stop}, vic);
					
		
	}
	
	
	public static void main(String[] args){
		VehicleClientGuiSetup setup = new VehicleClientGuiSetup();
		setup.doJob();
	}
	
}
