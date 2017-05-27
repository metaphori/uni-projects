package it.unibo.is.interfaces.protocols;
 
import java.net.DatagramSocket;
 
public interface IUdpConnection {
	
//	public  IUdpInteraction connectAsReceiver(int portNum) throws Exception;
	public  DatagramSocket connectAsReceiver(int portNum) throws Exception;
// 	public  IUdpInteraction connectAsClient(String hostName, int portNum, int sendPort) throws Exception;
	public DatagramSocket connectAsClient(String hostName, int port) throws Exception;
// 	public  IUdpInteraction connectAsClient(String hostName, int portNum ) throws Exception;
	public void closeConnection(DatagramSocket socket);

}
