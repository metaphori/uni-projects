package it.unibo.platform.udp;

import it.unibo.is.interfaces.IBasicEnvAwt;
import it.unibo.is.interfaces.protocols.IUdpInteraction;
import it.unibo.system.SituatedPlainObject;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class SocketUdpConnSupport extends SituatedPlainObject implements IUdpInteraction{
private DatagramSocket socket;
private int portNum;
private InetAddress ia ;
private DatagramPacket curPacket;
    
public SocketUdpConnSupport( DatagramSocket socket, int portNum, InetAddress ia, IBasicEnvAwt env ){
	super(env);
	this.socket 	= socket;
	this.portNum	= portNum;
 	this.ia			= ia;
	if( System.getProperty("udpTrace") != null ) 
		debug = System.getProperty("udpTrace").equals("set") ;
}

@Override
public void closeConnection() throws Exception {
	// TODO Auto-generated method stub
	
}

@Override
public void sendALine( String msg ) throws Exception {
 	try {
 		println( " *** sendALine " + ia  );
		send(socket, ia, portNum, msg.getBytes());	
	} catch (Exception e) {
		println( "ERROR " + e.getMessage()  );
	}
}

@Override
public void sendALine(String msg, boolean isAnswer) throws Exception {
	if(isAnswer) sendAReplyLine(msg);
	else sendALine(msg);
	
}

@Override
public DatagramSocket getSocket() {
	return socket;
}

@Override
public String receiveALine(   ) throws Exception{
		DatagramPacket rp = receiveAPacket( 0 );
//	println("received " + rp );
	String s  = new String(rp.getData(),0,rp.getLength());
//	println("received host= " + rp.getAddress().getHostName()+ "  port=" + rp.getPort());
	return s;
}
//--------------------------------------------------------------------

public void sendAReplyLine( String msg )throws Exception{
	if( curPacket != null )
		sendALine( curPacket.getAddress(), curPacket.getPort(), msg );
	else throw new Exception("SocketUdpConnSupport ERROR: reply without caller");
}

public  DatagramPacket receiveAPacket(  int timeout ) throws Exception {
	byte[] 	buffer = new byte[65507];
	curPacket = new DatagramPacket(buffer,buffer.length);
	try{
//		println("*** receiveAPacket " + socket.getLocalPort()   );
		socket.setSoTimeout(timeout);	//blocks for no more than 0.2 sec	    	
		socket.receive(curPacket);
		return curPacket;
	}catch(  Exception e ){
		println("TOUT ds  *** " + socket.getLocalPort() + " " + e.getMessage() );
//		ds.close();
		throw e;
	}			
}

protected void sendALine(InetAddress ia, int destPort, String msg)
		throws Exception {
	send(socket, ia, destPort, msg.getBytes() );
}

protected synchronized void send(
		DatagramSocket ds, InetAddress ia, int portNum, byte[] buffer) throws Exception{
	if( buffer.length > 64900) throw new IOException();
	DatagramPacket dp = new DatagramPacket(buffer, buffer.length, ia, portNum);
	ds.send(dp);
	println("has sent a msg to " + dp.getAddress()+":"+portNum);
}


}//Class

