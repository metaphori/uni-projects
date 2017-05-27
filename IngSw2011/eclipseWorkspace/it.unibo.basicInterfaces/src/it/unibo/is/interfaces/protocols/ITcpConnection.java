package it.unibo.is.interfaces.protocols;

import java.net.ServerSocket;
import java.net.Socket;

public interface ITcpConnection {
	public Socket connectAsClient(String hostName, int port) throws Exception;
	public ServerSocket connectAsReceiver(int portNum) throws Exception;
	public Socket acceptAConnection(ServerSocket serverSocket) throws Exception;
	public void closeConnection(ServerSocket serverSocket) throws Exception;
}
