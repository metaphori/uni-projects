package it.unibo.qactors;
import java.util.Vector;
import it.unibo.is.interfaces.IOutputView;
import it.unibo.system.SituatedActiveObject;


public abstract class QActor extends SituatedActiveObject{
	protected String myId;
	protected ActorContext myCtx;
	protected Vector<String> msgQueue ;
	
	public QActor(String actorId, ActorContext actx, IOutputView outView ){
		super( outView , actorId );
		myId  		= actorId;
		myCtx 		= actx;
		msgQueue 	= new Vector<String>();
		myCtx.registerActor(actorId, this);
	} 	
 	public void sendMsg( String destActorId, String msgType, String msg ) throws Exception{
 		//If the context of destActorId is the current context => local call		
		SenderObject sa   = myCtx.getSenderAgent(destActorId);
		//println("sending USING:." + sa );
		if( sa == null ) { //same context
			localCall( destActorId,  msgType,  msg);
		}else{ //OTHER CONTEXT
			//outView.addOutput("sending with " + sa.getName());
			sa.sendMsg(this, destActorId, msgType, msg);		
		}
	}	
 	protected void localCall(String destActorId, String msgType, String msg){
		QActor dest = myCtx.getActor(destActorId);
		if( dest != null ) {
			int msgNum = myCtx.newMsgnum();
	 		String mout = "msg(" + msgType + ","+ getName() +","+ destActorId +",'"+ msg+"',"+ msgNum + ")";
			dest.storeMsg(mout);			
		}
 	}
	public synchronized void  storeMsg( String msg ){ 
		//println("QActor " + this.getName() + " storeMsg:" + msg   );
		msgQueue.add(msg);
		notifyAll();
	}
 	
	public synchronized String receiveMsg( ) throws Exception{
		//println("QActor receiveMsg queue.size=" + msgQueue.size());
		while( msgQueue.size() == 0){
			wait();
		}
		return msgQueue.remove(0);
	}
	@Override
	protected void startWork() throws Exception {
	}
	@Override
	protected void endWork() throws Exception {
	}
	 
}
