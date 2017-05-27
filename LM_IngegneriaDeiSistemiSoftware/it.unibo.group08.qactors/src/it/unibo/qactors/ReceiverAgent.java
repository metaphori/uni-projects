package it.unibo.qactors;
import it.unibo.is.interfaces.IOutputView;
import it.unibo.is.interfaces.protocols.IConnInteraction;
import it.unibo.supports.FactoryProtocol;
import it.unibo.system.SituatedActiveObject;
import it.unibo.system.SituatedSysKb;
/*
 * Waits for messages from the connected nodes
 */
public class ReceiverAgent extends SituatedActiveObject{
	protected IConnInteraction conn;
	protected String hostName;
	protected int port;
	protected MsgInterpreter interpreter;	
	protected FactoryProtocol factoryP;
	protected ActorContext ctx;
	
	public ReceiverAgent(String name, ActorContext ctx, IOutputView outView, IConnInteraction connIn ) {
		super(outView, name);
		this.ctx = ctx;
		conn 	 = connIn;
 		interpreter = ctx.getMsgInterpreter();
  		//activate( SituatedSysKb.executor4Thread );
  		activate( SituatedSysKb.executor4Thread );
 	}
	@Override
	protected void startWork()  {
 	}
	@Override
	protected void doJob()  {
 		while(true){ 
			try {
//				println("		ReceiverAgent waits for a message ... " );
				String msg = conn.receiveALine();
//				println("		ReceiverAgent received " + msg);
				if( msg != null ){
					interpreter.elab(msg);			
				} else break;
			} catch (Exception e) {
				println( getName() + " ReceiverAgent ERROR " + e.getMessage() );
 				break;
			}			
		}		
	}
	@Override
	protected void endWork() throws Exception {		
	}

 
}
