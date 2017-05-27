package it.unibo.buttonled.proxy;
import java.util.Observable;

import it.unibo.buttonLed.interfaces.IButton;
import it.unibo.buttonLed.interfaces.ILed;
import it.unibo.buttonled.impl.ButtonMock;
import it.unibo.buttonled.impl.LedMock;
import it.unibo.domain.interfaces.IDevButton;
import it.unibo.domain.interfaces.IDevInputImpl;
import it.unibo.is.interfaces.IBasicEnvAwt;
import it.unibo.is.interfaces.IBasicUniboEnv;
import it.unibo.is.interfaces.IObserver;
import it.unibo.is.interfaces.protocols.IConnInteraction;
import it.unibo.supports.FactoryProtocol;
import it.unibo.system.SituatedActiveObject;

public class LedProxyServer extends SituatedActiveObject {
protected String name="";
protected String hostName="";
protected int portNum = 0;
protected IConnInteraction conn;
protected String cmd = "";
protected ILed led;

	public LedProxyServer( ILed led, int portNum, IBasicEnvAwt env ) throws Exception{
		super(env, led.getName() + "Proxy Server");
		//this.hostName = hostName;
		this.portNum = portNum;
		this.led = led;
		connect(); 
 	}	
	protected void  connect(){
		try {
			System.out.println("LedProxyServer starts");
			System.out.println("LedProxyServer connected");
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
		FactoryProtocol factoryP = new FactoryProtocol(null, "TCP", "LedProxy");		
		conn = factoryP.createServerProtocolSupport(portNum);
 		while(true){
			this.println("LedProxyServer RECEIVING ...");
	 		String cmd = conn.receiveALine();
			this.println("LedProxyServer RECEIVED: " + cmd);
			if(cmd.equals("0")){
				led.turnOff();
			} else if(cmd.equals("1")){
				led.turnOn();
			}
 		}
	}
	@Override
	protected void startWork() throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void endWork() throws Exception {
		// TODO Auto-generated method stub
		
	}	

	public static void main(String[] args) throws Exception{
		new LedProxyServer(new LedMock("led"), 8055, null);
	}

}
