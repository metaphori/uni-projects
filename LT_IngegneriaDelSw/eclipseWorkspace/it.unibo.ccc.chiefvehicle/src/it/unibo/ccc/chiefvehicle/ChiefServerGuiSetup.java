package it.unibo.ccc.chiefvehicle;


import java.awt.GridLayout;
import java.awt.Panel;

import it.unibo.baseEnv.basicFrame.*;
import it.unibo.baseEnv.basicgui.input.InputPanel;
import it.unibo.ccc.domain.impl.*;
import it.unibo.ccc.gui.ChiefCmdPanel;
import it.unibo.ccc.gui.DisplayWithPanel;
import it.unibo.ccc.gui.FlagPanel;
import it.unibo.ccc.domain.SysKb;
import it.unibo.contaKm.domain.IDisplay;
import it.unibo.is.interfaces.*;
import it.unibo.supports.tcp.FactoryTcpProtocol;

public class ChiefServerGuiSetup {

	private IBasicEnvAwt env;
	private ConvoyCruiseControl convoy;
	private FactoryTcpProtocol factory;
	
	public void doJob(){
		init();
		configure();
		start();
	}
	
	public void init(){
		env = new EnvFrame();
		env.init();	
		
		factory = new FactoryTcpProtocol(env,"ContaKmClientGuiSetup");
	}
	
	public void configure(){
		FlagPanel fp = new FlagPanel();
		
		convoy = new ConvoyCruiseControl();
		convoy.addObserver(fp);
		ConvoyVehicle chiefVehicle = new ChiefVehicle(env, convoy);
		
		IDisplay d1 = new DisplayWithPanel(env, SysKb.SpeedFormat.KmPerH);
		chiefVehicle.addObserver(d1);
		IDisplay d2 =  new DisplayWithPanel(env, SysKb.SpeedFormat.KmPerS);
		chiefVehicle.addObserver(d2);
		
		try{
			chiefVehicle.setSpeed(0);
		} catch(Exception e){}
		
		CCCInputControl ccc = new CCCInputControl(env, convoy); // interfaces to CCC system
		
		InputPanel ip = new InputPanel("Set Convoy Speed", "10", env.getOutputView(), ccc, SysKb.setSpeed);
		
		ChiefCmdPanel cpanel = 
				new ChiefCmdPanel("Chief Panel", new String[]{ SysKb.start, SysKb.stop }, ccc);
		
		env.addPanel(cpanel);
		
		Panel ppanel = new Panel();
		ppanel.setLayout( new GridLayout(4,4) );
		ppanel.add(ip);
		ppanel.add(fp);
		
		env.addPanel(ppanel);		
		
		CCCProxyServer server;
		try{
			server = new CCCProxyServer(env, factory, SysKb.serverPort, convoy);
		} catch(Exception exc){
			env.println("Impossibile connettere il proxy: " + exc.getMessage());
			return;
		}			
	}
	
	public void start(){
		
	}
	
	
	public static void main(String[] args){
		ChiefServerGuiSetup setup = new ChiefServerGuiSetup();
		setup.doJob();
	}
	
}
