/*
 * This actors prints a sequence of messages on the standard output port.
 */
package it.unibo.qactors.example.schedule;
import it.unibo.is.interfaces.IOutputView;
import it.unibo.qactors.ActorContext;
import it.unibo.qactors.QActor;

public class QActor1 extends QActor{

	public QActor1(String actorId, ActorContext myCtx, IOutputView outView) {
		super(actorId, myCtx, outView);
 	}

 	@Override
	protected void doJob() throws Exception {
 		for(int i=1; i<=5; i++){
			println(getName() + " msg=" + i);
			Thread.yield();
 		}
  	}	
	protected void autoMsg() throws Exception{
		println(getName() + " sends a selfy" );
		sendMsg("qa1", ActorContext.dispatch, "autoMsg" );
		String msg = receiveMsg();
		String senderId = myCtx.getMsgSenderActorId(msg);
		println(getName() + " received " + msg + " from " + senderId );
		
	}
}
