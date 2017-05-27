/*
 * A receiver actor
 */
package it.unibo.qactors.example.buttonandroid;
import it.unibo.is.interfaces.IOutputView;
import it.unibo.qactors.ActorContext;
import it.unibo.qactors.QActor;

public class QActorButtonReceiver extends QActor{

	public QActorButtonReceiver(String actorId, ActorContext actx, IOutputView outView) {
		super(actorId, actx, outView);
 	}

	@Override
	protected void doJob() throws Exception {
		while(true)
		try {
/*
* STATE MACHINE implemented as a sequence of commands
* It could be better to give an explicit representation of states
*/			//STATE S0: waits for a message  
			println(getName() + " waiting for a message ...:"  );
			String msg = receiveMsg();
			println(getName() + " received:" + msg);
			//TODO: turnon or turnoff the LED
		} catch (Exception e) {
			println(getName() + " ERROR " + e.getMessage() );
		}
		 		
	}

}
