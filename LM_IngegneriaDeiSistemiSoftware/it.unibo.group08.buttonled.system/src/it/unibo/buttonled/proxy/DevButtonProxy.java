package it.unibo.buttonled.proxy;
import it.unibo.buttonled.devices.SysKbBridge;
import it.unibo.buttonled.impl.ButtonMock;
import it.unibo.is.interfaces.protocols.IConnInteraction;
import it.unibo.supports.FactoryProtocol;

public class DevButtonProxy extends  ButtonMock  {
protected String name="";
protected String hostName="";
protected int portNum = 0;
protected IConnInteraction conn;
protected String cmd = "";

	public DevButtonProxy( String name, String hostName, int portNum ) throws Exception{
		super( name ) ;
		this.hostName = hostName;
		this.portNum = portNum;
		connect(); 
 	}	
	protected void  connect(){
		try {
			System.out.println("DevButtonProxyClient starts");
			FactoryProtocol factoryP = new FactoryProtocol(null, "TCP", "LedProxy");
			conn = factoryP.createClientProtocolSupport( hostName,  portNum);
			System.out.println("DevButtonProxyClient connected");
		} catch (Exception e) {
 			e.printStackTrace();
		}		
	}
	@Override
	public void high() {
		try {
			cmd =  SysKbBridge.repHigh;
			//dispatch or request/invitation?	
			conn.sendALine(cmd);	
			super.high();
		} catch (Exception e) {
 			e.printStackTrace();
		}
 	}
	@Override
	public void low() {
		try {
			cmd =  SysKbBridge.repLow;
			//dispatch or request/invitation?	
			conn.sendALine(cmd);
			super.low();
		} catch (Exception e) {
 			e.printStackTrace();
		}
	}
 	/*
	 * -----------------------------------------
	 * MAIN  (rapid check)
	 * -----------------------------------------
	 */
	public static void main(String[] args) throws Exception {
		DevButtonProxy lp = new DevButtonProxy("","192.168.43.224", 8085/*DevButtonRaspServerMain.portNum*/);
		for( int i=1; i<=5; i++ ){			
			lp.high();
			Thread.sleep(800);
			lp.low();
			Thread.sleep(500);
			lp.high();
			Thread.sleep(800);
			lp.low();
			Thread.sleep(500);
		}
		System.out.println("BYE");
	}	
}
