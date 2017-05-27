package it.unibo.is.interfaces.protocols;

import java.net.Socket;

public interface ITcpInteraction extends IConnInteraction{
	
	public Socket getSocket();
 	
	/**
	 * A smart version of receiveALine 
	 * @param inputChannel
	 * @return
	 * @throws Exception
	 */
//	String receiveACmdLine( InputStream inputChannel ) throws Exception;
}
