/*
 * A receiver actor
 */
package it.unibo.qactors.example;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import it.unibo.is.interfaces.IOutputView;
import it.unibo.qactors.ActorContext;
import it.unibo.qactors.QActor;

public class QActorReceiver extends QActor{

	public QActorReceiver(String actorId, ActorContext actx, IOutputView outView) {
		super(actorId, actx, outView);
 	}

	@Override
	protected void doJob() throws Exception {
		try {
/*
* STATE MACHINE implemented as a sequence of commands
* It could be better to give an explicit representation of states
*/			//STATE S0: waits for a message  
			String msg = receiveMsg();
			println(getName() + " received:" + msg);
			//STATE S1: send a request to qasender
			println(getName() + " sends a reques to qasender" );
			sendMsg("qasender", ActorContext.request, "hello world from" + this.getName());
			//STATE S2: waits for an answer 
			msg = receiveMsg();
			//STATE S3: welaborate the answer
			println(getName() + " received " + msg);
			elabTheMsg(msg);
		} catch (Exception e) {
			println(getName() + " ERROR " + e.getMessage() );
		}
		 		
	}
	/*
	 * Access to mesagge arguments via Prolog
	 */
	// msg( MSGTYPE, SENDER, RECEIVER, CONTENT, SEQNUM )
	protected void elabTheMsg(String msg){
		
		Struct msgStruct = (Struct) Term.createTerm(msg);
		String msgType = msgStruct.getArg(0).toString();
		String msgSender = msgStruct.getArg(1).toString();
		String msgReceiver = msgStruct.getArg(2).toString();
		String msgContent = msgStruct.getArg(3).toString();
		String msgNum = msgStruct.getArg(4).toString();
		
		println("msgType	="+msgType);
		println("msgSender	="+msgSender);
		println("msgReceiver="+msgReceiver);
		println("msgContent	="+msgContent);
		println("msgNum		="+msgNum);
	}

}
