/*
 * Waits for messages from the connected nodes
 */
package it.unibo.qactors;
import it.unibo.is.interfaces.IOutputView;
import it.unibo.is.interfaces.protocols.IConnInteraction;
import it.unibo.supports.FactoryProtocol;
import it.unibo.system.SituatedActiveObject;
import it.unibo.system.SituatedSysKb;


public class CtxServerAgent extends SituatedActiveObject{
	protected IConnInteraction conn;
 	protected int port;
 	protected FactoryProtocol factoryP;
 	protected ActorContext ctx;
	
	public CtxServerAgent(String name, ActorContext ctx, IOutputView outView,  int port ) {
		super(outView, name);
		this.ctx = ctx;
 		this.port     	= port;
 		activate(SituatedSysKb.executor4Thread);
 	}


	@Override
	protected void startWork()  {
 		try {
 			println("CtxServerAgent startWork on port " + port);
 			System.setProperty("inputTimeOut", "300000");
 			factoryP = new FactoryProtocol(outView,FactoryProtocol.TCP, getName());
		} catch (Exception e) {
	 		println(getName() + " CtxServerAgent ERROR " + e.getMessage() );
		} 		
	}
 
	@Override
	protected void endWork() throws Exception {		
	}
 
	@Override
	protected void doJob() throws Exception {
		while(true)
		try {
			IConnInteraction connIn = factoryP.createServerProtocolSupport(port);
			new ReceiverAgent("rec_"+port,ctx,outView,connIn);
		} catch (Exception e) {
	 		println(getName() + " ERROR " + e.getMessage() );
	 		break;
		} 				
	} 
}
