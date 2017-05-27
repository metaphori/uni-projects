package it.unibo.buttonled.proxy;
import it.unibo.buttonled.devices.SysKbBridge;
import it.unibo.buttonled.impl.BLSColor;
import it.unibo.is.interfaces.IOutputView;
import it.unibo.is.interfaces.protocols.IConnInteraction;
import it.unibo.supports.FactoryProtocol;

public class LedProxyClient extends it.unibo.buttonled.impl.Led {
protected String name="";
protected String hostName="";
protected int portNum = 0;
protected IConnInteraction conn;
protected String cmd = "";
protected IOutputView outView;  
       
	public LedProxyClient( String name, 
			it.unibo.buttonLed.interfaces.IColor ledColor, 
			String hostName, 
			int portNum, 
			IOutputView outView ) throws Exception{
		super( name, ledColor ) ;
		this.hostName = hostName;
		this.portNum = portNum;
		this.outView = outView;
		connect();    
 	}	
	protected void  connect(){
		try {
			println("DevLedProxy starts");
			FactoryProtocol factoryP = new FactoryProtocol(outView, "TCP", "LedProxy");
			conn = factoryP.createClientProtocolSupport( hostName,  portNum);
			println("DevLedProxy connected");
		} catch (Exception e) {
 			e.printStackTrace();
		}		
	}
	@Override
	public void turnOn() {
		try {
			cmd =  it.unibo.buttonled.devices.SysKbBridge.repHigh;
			//dispatch or request/invitation?	
			System.out.println("TUNRON");
			conn.sendALine(cmd);	
			super.turnOn();
		} catch (Exception e) {
 			e.printStackTrace();
		}
 	}
	@Override
	public void turnOff() {
		try {
			cmd =  SysKbBridge.repLow;
			//dispatch or request/invitation?	
			conn.sendALine(cmd);
			super.turnOff();
		} catch (Exception e) {
 			e.printStackTrace();
		}
	}
	@Override
	public void doSwitch() {
		try {
			//dispatch or request/invitation?	
			cmd = "doSwitch("+name+")";	//TODO
			conn.sendALine(cmd);
			super.doSwitch();
		} catch (Exception e) {
 			e.printStackTrace();
		}
 	}  
	/*
	 * The proxy is a "mirror" of the state in order to reduce network traffic
 	 */  
	@Override
	public boolean isOn() {
 		return super.isOn();
	}
	
	protected void println(String msg){
		if( outView != null ) outView.addOutput(msg);
		else System.out.println(msg);
	}
	/*
	 * -----------------------------------------
	 * MAIN (rapid check)
	 * -----------------------------------------
	 */
	public static void main(String[] args) throws Exception {
		LedProxyClient lp = new LedProxyClient("",new BLSColor("GREEN"),"0.0.0.0", 8055, null);
		
		lp.turnOn();
		Thread.sleep(1000);
		lp.turnOff();
		Thread.sleep(1000);
		lp.turnOn();
		Thread.sleep(1000);
		lp.turnOff();
 		Thread.sleep(10000);
	}	
}
