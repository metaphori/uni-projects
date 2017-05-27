package it.unibo.button.bridge.impl;
import java.util.Observable;

import it.unibo.arduino.serial.ISerialPortInteraction;
import it.unibo.buttonLed.devices.SysKbBridge;
import it.unibo.event.interfaces.INodejsLike;
import it.unibo.inforeply.infrastructure.SysKbInfoReplInfr;
import it.unibo.is.interfaces.IObserver;
import it.unibo.is.interfaces.IOutputView;
import it.unibo.system.SituatedPlainObject;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class DevButtonArduinoObserverToEvent extends SituatedPlainObject implements IObserver, SerialPortEventListener{

protected ISerialPortInteraction portConn;
protected  INodejsLike njs;
protected int n = 0;

	public DevButtonArduinoObserverToEvent(ISerialPortInteraction portConn,IOutputView outView) {
		super(outView);
		this.portConn = portConn;
		njs = SysKbInfoReplInfr.getNodejsLike();
 	}
	@Override
	public void update(Observable arg0, Object inputLine) {
		try {
			n++;
			String content = "value(" + n + "," + inputLine + ")";
			println("DevButtonArduinoObserverToEvent " + content);
			if( njs != null ) njs.raiseEvent(null, SysKbBridge.eventName, content);
		} catch (Exception e) {
 			e.printStackTrace();
		}
	}
	@Override
	public void serialEvent(SerialPortEvent oEvent) {
		//println("oEvent " + oEvent);
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
 		  try {
			 /* no read => event not consumed */
			 String inputLine=portConn.receiveALine();
 	 		 update( this, inputLine);
 		  } catch (Exception e) {
			  //System.out.println("DevButtonArduinoObserverToEvent " + e.toString());
		  }
		}	
	}
}
