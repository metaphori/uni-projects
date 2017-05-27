package it.unibo.button.bridge.impl;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import it.unibo.arduino.serial.SerialPortConnSupport;
import it.unibo.arduino.serial.SerialPortSupport;
import it.unibo.buttonLed.devices.SysKbBridge;
import it.unibo.buttonLedSystem.BLSSysKb;
import it.unibo.domain.interfaces.IDevButtonArduino;
import it.unibo.is.interfaces.IBasicUniboEnv;
import it.unibo.is.interfaces.protocols.IConnInteraction;
import it.unibo.supports.FactoryProtocol;

public class DevButtonArduino extends DevButtonImpl implements IDevButtonArduino, SerialPortEventListener {
protected  String PORT_NAME ;
protected IConnInteraction portConn; //the comm channel with Arduino is a "general" two-way IConnInteraction
protected SerialPort serialPort;
protected int n = 0;	
	public DevButtonArduino(String name, IBasicUniboEnv env, String PORT_NAME) {
		super(name, env );
		if(env!=null) outView = env.getOutputView();
		this.PORT_NAME = PORT_NAME;
		configureNew();
	}
	protected void configure() {
		try {
			//it.unibo.arduino.serial is in the project it.unibo.noawtsupports
			SerialPortSupport serialPortSupport = new SerialPortSupport(  outView );
			println("DevButtonArduino connecting to serial port:="+PORT_NAME);
			serialPort = serialPortSupport.connect(PORT_NAME, this.getClass());
			println("DevButtonArduino connected" );
			portConn = new SerialPortConnSupport(serialPort, outView);
			
			serialPort.addEventListener( this );
			serialPort.notifyOnDataAvailable(true);
 		} catch (Exception e) { e.printStackTrace(); }
	}
	protected void configureNew() {
		try {
			FactoryProtocol factoryP = new FactoryProtocol(outView, FactoryProtocol.SERIAL, "fp");
			portConn = factoryP.createSerialProtocolSupport(PORT_NAME, this );
			
			for(int i=0; i<1000;i++){
				outView.addOutput("Sending a line to serial port: " + (i%2));
			portConn.sendALine("" + (i%2));
			 try{Thread.sleep(1000);}catch(Exception e){}
			}
			
 		} catch (Exception e) { e.printStackTrace(); }
	}
//	@Override
//	public SerialPort getSerialPort() {
// 		return serialPort;
//	}
	@Override
	public synchronized void serialEvent(SerialPortEvent oEvent) { 
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
		  //println("DevButtonArduino serialEvent event type=" + oEvent.getEventType()  );
 		  try {
			 //no read => event not consumed ; arduino sends arduinoInterrupt(button) when the pin is HIGH
 			 String input = portConn.receiveALine();
 			 n++;
 			 println("DevButtonArduino input=" + input  );
 			 //TODO: use a better 'interpreter'
 			 if( input.length() > 0  && input.contains("arduino") ){	
				 if(input.contains("1") || input.contains("true")) 
					 update( this, SysKbBridge.repHigh);
				 else update( this, SysKbBridge.repLow);
  			 }
 			 else{
 				println("** DevButtonArduino WARNING *** : no button input=" + input );
 			 }
		  } catch (Exception e) {
			  println("DevButtonArduino ERROR:"+e.getMessage());			 
		  }
		}	
	}
	/*
	 * ----------------------------------------------------
	 * Main (rapid check)
	 * ----------------------------------------------------
	 */

	
	public static void main(String args[]) throws Exception {
		DevButtonArduino b = 
				new DevButtonArduino("btArd", null ,BLSSysKb.serialPortLinux);
 	}
 
}