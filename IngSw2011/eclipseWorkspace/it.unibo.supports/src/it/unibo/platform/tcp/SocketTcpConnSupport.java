/*
*  Generated by AN Unibo
*/
package it.unibo.platform.tcp;
 
import it.unibo.is.interfaces.IBasicEnvAwt;
import it.unibo.is.interfaces.protocols.ITcpInteraction;
import it.unibo.system.SituatedPlainObject;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;


public class SocketTcpConnSupport extends SituatedPlainObject implements ITcpInteraction{

private String logo;
private Socket socket;
private DataOutputStream outputChannel;
private BufferedReader inputChannel;

	public SocketTcpConnSupport(String logo, Socket socket, IBasicEnvAwt env) {
		super(env);
		this.logo 	= logo;
		this.socket = socket;
 		try {
			OutputStream outStream 	= socket.getOutputStream();
			InputStream inStream  	= socket.getInputStream();
			outputChannel 			= new DataOutputStream(outStream);
			inputChannel    		= new BufferedReader( new InputStreamReader( inStream ) );	
			if( System.getProperty("tcpLowTrace") != null ) 
				debug = System.getProperty("tcpLowTrace").equals("set") ;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getlogo(){
		return logo;
	}

	
	public Socket getSocket(){
		return socket;
	}
	
	public void closeConnection() throws Exception{
		println(" CLOSING ");
		socket.close();
	}

	/**
	 * Implementation based on DataOutputStream
	 * @param outputChannel
	 * @param msg
	 * @throws Exception
	 */
	public  void sendALine( String msg ) throws Exception{
		println( "sendALine ... " + msg + " localPort=" + socket.getLocalPort() + " port=" + socket.getPort());
		outputChannel.writeBytes( msg+"\n" );	
		println( "has written ... " +msg    );
		outputChannel.flush();
	}
	
	public  void sendACmdLine( String msg ) throws Exception{
		outputChannel.write( (msg+"\n").getBytes());
		outputChannel.flush();
	}
	
	public  String receiveALine(  ) throws Exception{
			return receiveALine(0);		
	}
	
	public  String receiveALine( int timeOut ) throws Exception{
 		println( "receiveALine trying ... " + Thread.currentThread().getName()  + " timeOut="+timeOut);
 		try {
			socket.setSoTimeout(timeOut);
			String	line = inputChannel.readLine();  //blocking =>
			println( "has read ... " +line  );
			return line;		
		} catch (SocketException e) {
	 		println( "timeOut" +  socket.getLocalPort());
			throw e;
		}
	}
	
	/**
	 * A smart implementation of receiveALine based on BufferedReader
	 * @param inputChannel
	 * @return
	 * @throws Exception
	 */

	public  String receiveACmdLine( InputStream inChannel ) throws Exception{
		byte[] 	b = new byte[100];
		int msgLength = 0;
			int n = inChannel.read(b,0,b.length);  //like a receive
			msgLength += n;
//			println( "has read " + n + " bytes\n" + (char)b[n-1]  );
			while( b[msgLength-1] != (byte)'\n' ){
				n = inChannel.read(b,msgLength,100-msgLength);
				msgLength += n;
//				println( "has read " + n + " bytes\n"  + (char)b[msgLength-1]  );
			}
			String cmd = new String(b,0,msgLength-1);
			println( "has received ... " +cmd  );
			return cmd;			
	}
	
	
	protected  void println( String msg){
 		showMsg("		+++ SocketTcpConnSupport|" + logo + " " + msg  );
	}

	@Override
	public void sendALine(String msg, boolean isAnswer) throws Exception {
		sendALine(msg);		
	}

 	
}
	