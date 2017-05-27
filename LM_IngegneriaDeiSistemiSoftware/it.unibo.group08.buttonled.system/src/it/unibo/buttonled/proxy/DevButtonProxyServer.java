package it.unibo.buttonled.proxy;
import java.util.Observable;

import it.unibo.buttonLed.interfaces.IButton;
import it.unibo.buttonled.impl.ButtonMock;
import it.unibo.domain.interfaces.IDevButton;
import it.unibo.domain.interfaces.IDevInputImpl;
import it.unibo.is.interfaces.IBasicEnvAwt;
import it.unibo.is.interfaces.IBasicUniboEnv;
import it.unibo.is.interfaces.IObserver;
import it.unibo.is.interfaces.protocols.IConnInteraction;
import it.unibo.supports.FactoryProtocol;
import it.unibo.system.SituatedActiveObject;

public class DevButtonProxyServer extends SituatedActiveObject {
protected String name="";
protected String hostName="";
protected int portNum = 0;
protected IConnInteraction conn;
protected String cmd = "";
protected IButton button;

	public DevButtonProxyServer( IButton button, int portNum, IBasicEnvAwt env ) throws Exception{
		super(env, button.getName() + "Proxy Server");
		//this.hostName = hostName;
		this.portNum = portNum;
		this.button = button;
		env.getOutputView().addOutput("DevButtonProxyServer trying to connect...");
		connect(); 
 	}	
	protected void  connect(){
		try {
			System.out.println("DevButtonProxyServer starts");
			FactoryProtocol factoryP = new FactoryProtocol(null, "TCP", "LedProxy");
			conn = factoryP.createServerProtocolSupport(portNum);
			System.out.println("DevButtonProxyServer connected");
		} catch (Exception e) {
 			e.printStackTrace();
		}		
	}
 	/*
	 * -----------------------------------------
	 * MAIN  (rapid check)
	 * -----------------------------------------
	 */


	@Override
	protected void doJob() throws Exception {
 		while(true){
			this.println("DevButtonProxyServer RECEIVING ...");
	 		String cmd = conn.receiveALine();
			this.println("DevButtonProxyServer RECEIVED: " + cmd);
			if(cmd.equals("0")){
				button.low();
			} else if(cmd.equals("1")){
				button.high();
			}
 		}
	}
	@Override
	protected void startWork() throws Exception {
		
		
	}
	@Override
	protected void endWork() throws Exception {
		// TODO Auto-generated method stub
		
	}	

	public static void main(String[] args) throws Exception{
		new DevButtonProxyServer(new ButtonMock("btn"), 8055, null);
	}

}
