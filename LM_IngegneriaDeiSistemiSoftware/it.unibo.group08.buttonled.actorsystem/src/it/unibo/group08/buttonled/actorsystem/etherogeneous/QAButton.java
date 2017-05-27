/*
 * A receiver actor
 */
package it.unibo.group08.buttonled.actorsystem.etherogeneous;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import it.unibo.is.interfaces.IOutputView;
import it.unibo.qactors.ActorContext;
import it.unibo.qactors.QActor;

public class QAButton extends QActor{

	protected boolean pressed = false;
	
	public QAButton(String actorId, ActorContext actx, IOutputView outView) {
		super(actorId, actx, outView);
 	}

	@Override
	protected synchronized void doJob() throws Exception {
		try {
			while(true){
				outView.addOutput("Waiting to be pressed");
				while( ! pressed ) wait();
				pressed = false;
				sendMsg("qacontrol", ActorContext.dispatch, "pressed");
			}
		} catch (Exception e) {
			println(getName() + " ERROR:: " + e.getMessage());
		}
		 		
	}
	
	public synchronized void press() {
		pressed = true;
		notifyAll();
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
