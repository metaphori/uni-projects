package it.unibo.ccc.chiefvehicle;

import java.net.ServerSocket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.unibo.ccc.domain.SysKb;
import it.unibo.ccc.domain.impl.ConvoyCruiseControl;
import it.unibo.ccc.domain.interfaces.IConvoyVehicle;
import it.unibo.ccc.exceptions.CannotPerformException;
import it.unibo.ccc.exceptions.InvalidArgumentException;
import it.unibo.ccc.system.interfaces.ICCC;
import it.unibo.is.interfaces.IActivity;
import it.unibo.is.interfaces.IBasicEnvAwt;
import it.unibo.is.interfaces.IIntent;
import it.unibo.is.interfaces.protocols.IConnInteraction;
import it.unibo.is.interfaces.protocols.ITcpInteraction;
import it.unibo.platform.tcp.SocketTcpConnSupport;
import it.unibo.platform.tcp.SocketTcpSupport;
import it.unibo.supports.FactoryProtocol;
import it.unibo.supports.tcp.FactoryTcpProtocol;
import it.unibo.supports.udp.FactoryUdpProtocol;

// TODO: turn to IConvoyVehicleFacade
public class CCCProxyServer implements Runnable {
	private FactoryTcpProtocol factory;
	private IBasicEnvAwt env;
	private int port;
	private IConnInteraction serverConn;
	private ServerSocket ss;
	private ICCC convoy;
	private SocketTcpSupport tcps;
	
	public CCCProxyServer(IBasicEnvAwt env, FactoryTcpProtocol factory, int port, ICCC convoy) throws Exception{
		super();
		this.env = env;
		this.factory = factory;
		this.port = port;
		this.convoy = convoy;
		
		Thread t = new Thread(this);
		t.start();
	}
	
	public IConnInteraction getConnection(){
		return serverConn;
	}
	
	public void run(){
		try{
			
			setupNetworkInfrastructure();
			
			//serverConn = (IConnInteraction) tcps.acceptAConnection(ss);
			
			//ConvoyProxyCommServer cpms = new ConvoyProxyCommServer(serverConn);
			
			while(true){
				try{
				waitForConnection();
				}catch(Exception exc){}
			}
			
		} catch(Exception exc){
			env.println("Error: " + exc.getMessage());
		}
	}

	protected void setupNetworkInfrastructure() throws Exception {
		// serverConn = factory.createServerProtocolSupport(port);
		
		tcps = new SocketTcpSupport("STCPSupp", env);
		ss = tcps.connectAsReceiver(port);
		
	}
	
	protected void waitForConnection() throws Exception{
		
		SocketTcpConnSupport tcpConnSupp = (SocketTcpConnSupport)factory.acceptAConnection(ss);
		new ConvoyProxyCommServer(env, tcpConnSupp, convoy);
		
		/*
		if ( factory instanceof FactoryTcpProtocol ){
			// TODO: handle connections 
			
		} else if( factory instanceof FactoryUdpProtocol ) {
			new ConvoyProxyCommServer(env, serverConn);
		}
		*/
	}

	public boolean isAVehicle(String cmd){
		Pattern p = Pattern.compile("^v[0-9]+$");
		Matcher m = p.matcher(cmd);
		return m.find();
	}
	
	
	
	/** COMMUNICATION SERVER ***/
	
	public class ConvoyProxyCommServer implements Runnable, IActivity {
		private IBasicEnvAwt env;
		private SocketTcpConnSupport supp;
		private ICCC convoy;
		public ConvoyProxyCommServer(IBasicEnvAwt env, SocketTcpConnSupport supp, ICCC convoy){
			this.env = env;
			this.supp = supp;
			this.convoy = convoy;
			Thread t = new Thread(this);
			t.start(); // active object
		}
		
		@Override
		public void execAction() {	}
		
		@Override
		public void execAction(String cmd) {
			env.println("["+cmd+"]");		
			
			String scheme = SysKb.getCmdScheme(cmd);
			String content = SysKb.getCmdContent(cmd);
		
			if( isAVehicle(scheme) ){
				// a vehicle <N> sent a command to CCC ( status or speed info )
				if(content.equals( SysKb.working )){
					convoy.notifyStatus( Integer.parseInt( scheme.substring(1) ) , true);
				} else if(content.equals( SysKb.notWorking)){
					convoy.notifyStatus( Integer.parseInt( scheme.substring(1) ) , false);
				} else{ // speed information
					double speed = Double.parseDouble(content);
					convoy.notifySpeed(Integer.parseInt( scheme.substring(1) ), speed);
				}
			
			} 
			else{
				env.println("Sorry, I can't understand your command");
			}
		}
		@Override
		public void execAction(IIntent input) { }
		
		@Override
		public String execActionWithAnswer(String cmd) {
			if( cmd.equals(SysKb.register) ){
				
				IConvoyVehicle proxyVehicle = new VehicleProxy(env, supp);
				
				int id = convoy.registerToConvoy( proxyVehicle );
				
				if(proxyVehicle instanceof VehicleProxy)
					((VehicleProxy)proxyVehicle).setID(id);
				
				return ""+id;
			}
			return "";
		}
		
		@Override
		public void run() {
			try{
				while(true){
					String cmd = supp.receiveALine();
					env.println("I received: " + cmd);
					
					if( cmd.equals(SysKb.register) ){
						String res = execActionWithAnswer(cmd);
						supp.sendALine(res);
					} else{
						execAction(cmd);
					}
				}
			} catch(Exception exc){
				env.println("Exc comm server: " + exc.getMessage());
			}
		}
	}

	
}
