package it.unibo.platform.udp;
 
import it.unibo.is.interfaces.IBasicEnvAwt;
import it.unibo.is.interfaces.protocols.IUdpConnection;
import it.unibo.system.SituatedPlainObject;
import java.net.DatagramSocket;


public class SocketUdpSupport extends SituatedPlainObject implements IUdpConnection{
	public SocketUdpSupport(String logo,IBasicEnvAwt env) {
		super(env);
		if( System.getProperty("udpTrace") != null ) 
			debug = System.getProperty("udpTrace").equals("set") ;
	}
	
//	public  IUdpInteraction connectAsReceiver(int portNum) throws Exception {
//	try{
//		DatagramSocket socket;
//		println( "connectAsReceiver on port " + portNum );
//		socket = new DatagramSocket( portNum );		
//		return new SocketUdpConnSupport("receiverFrom"+portNum,socket,portNum,null);		
//	}catch( Exception e ){
//		println("connectAsReceiver ERROR  *** " + portNum + " " + e.getMessage() );
//		throw e;
//	}
//	}
	public  DatagramSocket connectAsReceiver(int portNum) throws Exception {
	try{
		DatagramSocket socket;
		println( "connectAsReceiver on port " + portNum );
		socket = new DatagramSocket( portNum );		
		return socket;		
	}catch( Exception e ){
		println("connectAsReceiver ERROR  *** " + portNum + " " + e.getMessage() );
		throw e;
	}
	}
	
 
//	public IUdpInteraction connectAsClient(String hostName,  int portNum, int sendPort) throws Exception {
	
//	public IUdpInteraction connectAsClient(String hostName,  int portNum) throws Exception {
//		ia = InetAddress.getByName(hostName);
//		DatagramSocket socket;
//		socket = new DatagramSocket(  ); //if no arg => anonymous
////		socket = new DatagramSocket( sendPort ); //if no arg => anonymous
//		println( "connectAsClient connected to port " + portNum + " using " +socket.getLocalPort()  );
//		return new SocketUdpConnSupport("clientTo"+portNum,socket,portNum,ia);
//	}	

	public DatagramSocket connectAsClient(String hostName,  int portNum) throws Exception {
//		InetAddress ia = InetAddress.getByName(hostName);
		DatagramSocket socket;
		socket = new DatagramSocket(  ); //if no arg => anonymous
//		socket = new DatagramSocket( sendPort ); //if no arg => anonymous
		println( "connectAsClient connected to port " + portNum + " using " +socket.getLocalPort()  );
		return socket;
	}	

	
	public void closeConnection(DatagramSocket socket){
		println( "CLOSING" );
		socket.close();
	}
}
