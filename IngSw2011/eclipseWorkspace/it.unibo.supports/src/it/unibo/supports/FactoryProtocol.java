package it.unibo.supports;

import it.unibo.is.interfaces.IBasicEnvAwt;
import it.unibo.is.interfaces.protocols.IConnInteraction;
import it.unibo.supports.tcp.FactoryTcpProtocol;
import it.unibo.supports.udp.FactoryUdpProtocol;
import it.unibo.system.SituatedPlainObject;
 

public class FactoryProtocol extends SituatedPlainObject{
private	String protocol = null;
private String worker = null;

	public FactoryProtocol(IBasicEnvAwt env, String protocol, String worker) {
		super(env);
		this.protocol = protocol;
		this.worker = worker;
	}
 	
	public  IConnInteraction createClientProtocolSupport(  String hostName, int portNum ) throws Exception{
 		if( protocol.equals( "TCP" )){
			FactoryTcpProtocol factory = new FactoryTcpProtocol( env, worker);
			return factory.createClientProtocolSupport(  hostName, portNum );
		} else if( protocol.equals( "UDP" )){
			FactoryUdpProtocol factory = new FactoryUdpProtocol( env, worker);
			return factory.createClientProtocolSupport(  hostName, portNum );
		} else throw new Exception("protocol unknown");
	}
	
	public IConnInteraction createServerProtocolSupport( int portNum ) throws Exception{
 		if( protocol.equals( "TCP" )){
			FactoryTcpProtocol factory = new FactoryTcpProtocol( env, worker);
			return factory.createServerProtocolSupport(   portNum );
		} else if( protocol.equals( "UDP" )){
			FactoryUdpProtocol factory = new FactoryUdpProtocol( env, worker);
			return factory.createServerProtocolSupport( portNum );
		} else throw new Exception("protocol unknown");
		
	}
	
	
	
}
