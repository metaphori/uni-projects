/*
  * Provides a method to send messages to a remote context
  * (by creating a connection with that remote context)
 */
package it.unibo.qactors;
import it.unibo.is.interfaces.IOutputView;
import it.unibo.is.interfaces.protocols.IConnInteraction;
import it.unibo.supports.FactoryProtocol;
import it.unibo.system.SituatedPlainObject;

public class SenderObject extends SituatedPlainObject{	
	protected IConnInteraction conn = null;
	protected String hostName;
	protected int port;
 	protected FactoryProtocol factoryP;
 	protected ActorContext ctx;
	protected String protocol;
	
	public SenderObject(String name, ActorContext ctx, IOutputView outView,  String protocol, String hostName, int port ) {
		super(name, outView);
		this.ctx = ctx;
		this.hostName 	= hostName;
		this.port     	= port;
		this.protocol	= protocol;
		factoryP 		= new FactoryProtocol(outView, protocol, "fp");
   	}
  
	public void sendMsg(QActor sender, String receiverName, String msgType, String m) throws Exception {
		conn = ctx.getConnection(hostName);
		if( conn != null ){
			sendTheMsg(  sender,   receiverName,   msgType,   m) ;	
		} else{
			boolean res = setConn( );
			if( res ) {
				conn = ctx.getConnection(hostName);
				sendTheMsg(  sender,   receiverName,   msgType,   m) ;
			} else println("		SenderObject no connection "   );
 		}
  	}
	protected void sendTheMsg(QActor sender, String receiverName, String msgType, String m) throws Exception {
		try {
			int msgNum = ctx.newMsgnum();
			//msg( MSGID, SENDER, CONTENT, SEQNUM )
			String senderName  = sender.getName().toLowerCase();
	 		String mout = "msg(" + msgType + ","+ senderName +","+ receiverName +",'"+ m+"',"+ msgNum +")";
			conn.sendALine( mout );
		} catch (Exception e) {
			ctx.connectionTable.remove(hostName);
			throw e;
		}									
	}
	protected boolean setConn( ){
		try {
				//outView.addOutput("SenderObject thread try conn " +  protocol + " " + curHostName + " " + curPort);
				IConnInteraction conn = factoryP.createClientProtocolSupport(hostName, port);
				ctx.connectionTable.put(hostName, conn);
				return true;
		} catch (Exception e) {
		}				
		return false;
 	}
}
