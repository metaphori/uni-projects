package it.unibo.buttonLedSystemDistributed;
//import it.unibo.buttonLedSystem.BLSSysKb.LedColor;
import it.unibo.buttonLed.interfaces.IColor;
import it.unibo.buttonled.devices.SysKbBridge;
import it.unibo.buttonled.impl.BLSColor;
import it.unibo.buttonled.impl.LedMock;
import it.unibo.is.interfaces.IOutputView;
import it.unibo.is.interfaces.protocols.IConnInteraction;
import it.unibo.supports.FactoryProtocol;

public class DevLedProxyMain extends  LedMock  {
protected String name="";
protected String hostName="";
protected int portNum = 0;
protected IConnInteraction conn;
protected String cmd = "";
protected IOutputView outView;  
       
	public DevLedProxyMain( String name, IColor ledColor, String hostName, int portNum, IOutputView outView ) throws Exception{
		super( name, ledColor ) ;
		this.hostName = hostName;
		this.portNum = portNum;
		this.outView = outView;
		connect();    
 	}	
	protected void  connect(){
		try {
			println("DevLedProxy starts");
			FactoryProtocol factoryP = new FactoryProtocol(null, "TCP", "LedProxy");
			conn = factoryP.createClientProtocolSupport( hostName,  portNum);
			println("DevLedProxy connected");
		} catch (Exception e) {
 			e.printStackTrace();
		}		
	}
	@Override
	public void turnOn() {
		try {
			cmd =  SysKbBridge.repHigh;
			//dispatch or request/invitation?	
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
		DevLedProxyMain lp = new DevLedProxyMain("",new BLSColor("GREEN"),"192.168.43.229", DevLedServerMain.portNum, null);
		
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
