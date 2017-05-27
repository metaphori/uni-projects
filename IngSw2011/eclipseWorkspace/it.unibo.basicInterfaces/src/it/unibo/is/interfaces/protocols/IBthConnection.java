package it.unibo.is.interfaces.protocols;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
 

public interface IBthConnection {
	/**
	 * StreamConnection is equivalent to a Socket
	 */
	public StreamConnection connectAsClient( String devName, String p_servicename ) throws Exception;
	/**
	 * StreamConnectionNotifier is equivalent to a ServerSocket
	 */
	public StreamConnectionNotifier connectAsReceiver(String p_servicename) throws Exception;
	public StreamConnection acceptAConnection(StreamConnectionNotifier server) throws Exception;
	public void closeConnection(StreamConnectionNotifier server) throws Exception;

}
