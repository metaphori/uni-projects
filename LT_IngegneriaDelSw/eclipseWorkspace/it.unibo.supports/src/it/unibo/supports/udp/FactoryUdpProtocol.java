package it.unibo.supports.udp;

import java.net.DatagramSocket;
import java.net.InetAddress;
import it.unibo.is.interfaces.IBasicEnvAwt;
import it.unibo.is.interfaces.protocols.IConnInteraction;
import it.unibo.is.interfaces.protocols.IUdpInteraction;
import it.unibo.platform.udp.SocketUdpConnSupport;
import it.unibo.platform.udp.SocketUdpSupport;
import it.unibo.supports.FactoryProtocol;

public class FactoryUdpProtocol extends FactoryProtocol{
 	private SocketUdpSupport udpSupport ;

 	
	public FactoryUdpProtocol(IBasicEnvAwt env, String worker ){
		super(env, "UDP", worker);
 		udpSupport = new SocketUdpSupport( "UDP", env );
	 	if( System.getProperty("TcpTrace") != null ) 
			debug = System.getProperty("TcpTrace").equals("set") ;
	}
	
/**
 * CLIENT SITE
 */
	@Override
	public  IConnInteraction createClientProtocolSupport( String hostName, int portNum ) throws Exception{
		showMsg("createClientProtocolSupport " + hostName + ":" + portNum);
		DatagramSocket sock = udpSupport.connectAsClient( hostName,portNum );	//bloccante
		InetAddress ia = InetAddress.getByName(hostName);
  		IUdpInteraction connection  = new SocketUdpConnSupport(  sock, portNum, ia, env);		  
		showMsg("createClientProtocolSupport connection " + connection );
		return connection;		
	}

	
/**
* SSERVER SITE
*/
	@Override
	public  IConnInteraction createServerProtocolSupport( int portNum ) throws Exception{
		DatagramSocket serverSocket = udpSupport.connectAsReceiver( portNum );
		showMsg("createServerProtocolSupport " + serverSocket );
//		IConnInteraction conn = acceptAConnection(serverSocket); //blocking
		IConnInteraction conn = new SocketUdpConnSupport(serverSocket,portNum,null,env);
		return conn;  		 
  	}
 	
//	protected IUdpInteraction acceptAConnection(DatagramSocket serverSocket) throws Exception{
//		showMsg( "waits for a connection "  );	
//		DatagramSocket socket 	= udpSupport.acceptAConnection(serverSocket);
//		showMsg("connection " + socket );
//		return new SocketUdpConnSupport(worker,socket,portNum,null,env);
//	}

	protected void showMsg(String msg){
		System.out.println(msg);
	}
	
}
