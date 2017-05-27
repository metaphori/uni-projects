package it.unibo.is.interfaces.services;

 
import it.unibo.is.interfaces.protocols.IUdpConnection;
import it.unibo.is.interfaces.protocols.IUdpInteraction;
import java.net.DatagramSocket;
import java.net.InetAddress;

public interface IServiceUdp {	
	public IUdpInteraction getUdpConnSupport(String logo, DatagramSocket socket, int portNum, InetAddress ia);
 	public IUdpConnection getUdpSupport(String logo);
}
