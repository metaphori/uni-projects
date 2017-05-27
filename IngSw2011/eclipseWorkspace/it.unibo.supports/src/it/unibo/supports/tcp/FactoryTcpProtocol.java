package it.unibo.supports.tcp;

import java.net.ServerSocket;
import java.net.Socket;
import it.unibo.is.interfaces.IBasicEnvAwt;
import it.unibo.is.interfaces.protocols.IConnInteraction;
import it.unibo.is.interfaces.protocols.ITcpInteraction;
import it.unibo.platform.tcp.SocketTcpConnSupport;
import it.unibo.platform.tcp.SocketTcpSupport;
import it.unibo.supports.FactoryProtocol;

public class FactoryTcpProtocol extends FactoryProtocol{
	private String worker;
 	private SocketTcpSupport tcpSupport ;

 	
	public FactoryTcpProtocol(IBasicEnvAwt env, String worker ){
		super(env, "TCP", worker);
		tcpSupport = new SocketTcpSupport( "TCP", env );
	 	if( System.getProperty("TcpTrace") != null ) 
			debug = System.getProperty("TcpTrace").equals("set") ;
	}
	
/**
 * CLIENT SITE
 */
	@Override
	public  IConnInteraction createClientProtocolSupport( String hostName, int portNum ) throws Exception{
		showMsg("createClientProtocolSupport " + hostName + ":" + portNum);
  		Socket sock = tcpSupport.connectAsClient( hostName,portNum );	//bloccante
		ITcpInteraction connection  = new SocketTcpConnSupport("TcpOut"+worker, sock, env);		  
		showMsg("createClientProtocolSupport connection " + connection );
		return connection;		
	}

	
/**
* SSERVER SITE
*/
	@Override
	public  IConnInteraction createServerProtocolSupport( int portNum ) throws Exception{
		ServerSocket serverSocket = tcpSupport.connectAsReceiver( portNum );
		showMsg("createServerProtocolSupport " + serverSocket );
		 		IConnInteraction conn = acceptAConnection(serverSocket); //blocking
		return conn;  		 
  	}
 	
	public ITcpInteraction acceptAConnection(ServerSocket serverSocket) throws Exception{
		showMsg( "waits for a connection "  );	
		Socket socket 	= tcpSupport.acceptAConnection(serverSocket);
		showMsg("connection " + socket );
		return new SocketTcpConnSupport(worker,socket,env);
	}

	protected void showMsg(String msg){
		System.out.println(msg);
	}
	
}
