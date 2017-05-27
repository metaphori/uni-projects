/*
 * A sender actor
 */
package it.unibo.qactors.example;
import it.unibo.is.interfaces.IOutputView;
import it.unibo.qactors.ActorContext;
import it.unibo.qactors.QActor;

public class QActorSender extends QActor{
	public QActorSender(String actorId, ActorContext myCtx, IOutputView outView) {
		super(actorId, myCtx, outView);
 	}
	@Override
	protected void doJob() throws Exception {
		try {
 			//autoMsg();	//just to show that an actor can interact with itself
/*
 * STATE MACHINE implemented as a sequence of commands
 * It could be better to give an explicit representation of states
 */
 			//STATE S0: send a dispatch to qareceiver
			println(getName() + " sends a msg" );
			sendMsg("qareceiver", ActorContext.dispatch, "hello world from " + this.getName());
			//STATE S1: wait for a message from to qa2
			String msg = receiveMsg();
			//STATE S2: elaborate the received msg
			String senderId = myCtx.getMsgSenderActorId(msg);
			String msgType  = myCtx.getMsgType(msg);
			println(getName() + " received " + msg + " of type=" + msgType +  " from " + senderId );
			if( msgType.equals( ActorContext.request )){
				//STATE S3: send a reply to the sender
				println(getName() + " sends a reply to " + senderId );
				sendMsg(senderId, ActorContext.answer, "reply from " + this.getName());
			}else{
				//STATE S4: terminate
			}
		} catch (Exception e) {
			println(getName() + " ERROR " + e.getMessage() );
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
