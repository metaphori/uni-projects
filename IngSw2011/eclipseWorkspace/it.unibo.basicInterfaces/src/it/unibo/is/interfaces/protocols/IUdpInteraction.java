package it.unibo.is.interfaces.protocols;
 
import java.net.DatagramSocket;
 

public interface IUdpInteraction extends IConnInteraction{
	public DatagramSocket getSocket();

//	public void sendALine(  String msg ) throws Exception;	
//	public String receiveALine(   ) throws Exception;
// 	public void sendALine( InetAddress ia, int destPort, String msg )throws Exception;
//	public DatagramPacket receiveAPacket( int timeout ) throws Exception;
//	public void sendAReplyLine(  String msg )throws Exception;
//	public void closeConnection(DatagramSocket socket);
	
	
}
