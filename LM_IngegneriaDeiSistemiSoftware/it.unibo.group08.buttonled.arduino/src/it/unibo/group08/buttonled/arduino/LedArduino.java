package it.unibo.group08.buttonled.arduino;

import gnu.io.SerialPort;
import it.unibo.arduino.serial.SerialPortConnSupport;
import it.unibo.arduino.serial.SerialPortSupport;
import it.unibo.buttonLed.devices.SysKbBridge;
import it.unibo.buttonLed.interfaces.IColor;
import it.unibo.buttonLed.interfaces.ILed;
import it.unibo.buttonLedSystem.BLSSysKb.LedColor;
import it.unibo.domain.interfaces.IDevLed;
import it.unibo.group08.buttonLedSystem.BLSSysKb;
import it.unibo.is.interfaces.IOutputView;
import it.unibo.is.interfaces.protocols.IConnInteraction;
import it.unibo.system.SituatedPlainObject;

public class LedArduino extends SituatedPlainObject implements ILed {
	protected IOutputView outView = null;
	protected SerialPort serialPort;
	protected String PORT_NAME;
	protected boolean on = false;
	protected IColor color;
	SerialPortSupport serialPortSupport;
	protected IConnInteraction portConn; // the comm channel with Arduino is a
											// "general" two-way
											// IConnInteraction
	protected int n = 0;

	// Base Configuration
	public LedArduino(String PORT_NAME) {
		// Open the connection
		openConnection(PORT_NAME);
	}

	public LedArduino(String name, IColor color) throws Exception {
		this.name = "led("+name+")";
		if( color.getStringRepr().equals("RED") || 
				color.getStringRepr().equals("GREEN"))
			this.color=color;
		else throw new Exception("a led can be only RED or GREEN");	
	}

	// Connection
	private void openConnection(String PORT_NAME) {
		// TODO Auto-generated method stub

		try {
			// it.unibo.arduino.serial is in the project it.unibo.noawtsupports
			serialPortSupport = new SerialPortSupport(outView);
			println("LedOnArduino connecting to serial port:=" + PORT_NAME);
			serialPort = serialPortSupport.connect(PORT_NAME, this.getClass());
			println("LedOnArduino connected");
			portConn = new SerialPortConnSupport(serialPort, outView);
			// serialPort.addEventListener(this);
			//serialPort.notifyOnDataAvailable(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void closeConnection(String PORT_NAME) {
		try {
			serialPortSupport.closeConnection(PORT_NAME);
			println("LedOnArduino disconnected to serial port:=" + PORT_NAME);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * ---------------------------------------------------- 
	 * Main (rapid check)
	 * ----------------------------------------------------
	 */

	public static void main(String args[]) throws Exception {
		
		/*
		 * Crea il Led aprendo la connessione
		 * */
		LedArduino l = new LedArduino(BLSSysKb.serialPortMacOs);

		/*
		 * Il led su Arduino si accende e si spegne per 3 volte
		 * 
		 * */
		for (int i = 0; i < 3; i++) {
			l.turnOn();
			Thread.sleep(1000);
			l.turnOff();
			Thread.sleep(1000);
		}
		
		/*
		 * Spegni il Led ed esegui la disconnessione
		 * */
		
		l.closeConnection(BLSSysKb.serialPortMacOs);

	}

	@Override
	public String getDefaultRep() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void doSwitch() {
		// TODO Auto-generated method stub

	}

	@Override
	public void turnOn() {
		try {
			portConn.sendALine("1");
			println("Led On = true");
			on = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void turnOff() {
		try {
			portConn.sendALine("0");
			println("Led On = false");
			on = false;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean isOn() {
		// TODO
		return on;
	}
	@Override
	public LedColor getLedColor() {
		// TODO Auto-generated method stub
		return null;
	}
}
