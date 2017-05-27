package it.unibo.is.interfaces.services;

import java.net.Socket;

import it.unibo.is.interfaces.IOutputView;
import it.unibo.is.interfaces.protocols.ITcpConnection;
import it.unibo.is.interfaces.protocols.ITcpInteraction;

public interface IServiceTcp {	
	public ITcpInteraction getTcpConnSupport(String logo, Socket socket,IOutputView view);
	public ITcpConnection getTcpSupport(String logo,IOutputView view);
}
