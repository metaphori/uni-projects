/*
 * A sender actor
 */
package it.unibo.group08.buttonled.actorsystem.homogeneous;
import it.unibo.buttonLed.interfaces.ILed;
import it.unibo.is.interfaces.IOutputView;
import it.unibo.qactors.ActorContext;
import it.unibo.qactors.QActor;

public class QALed extends QActor{
	protected ILed led;
	
	public QALed(ILed led, String actorId, ActorContext myCtx, IOutputView outView) {
		super(actorId, myCtx, outView);
		this.led = led;
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
			//println(getName() + " sends a msg" );
			//sendMsg("qareceiver", ActorContext.dispatch, "hello world from " + this.getName());
			//STATE S1: wait for a message from to qa2
			while(true){
				String msg = receiveMsg();
				String content = myCtx.getMsgContent(msg);
				//STATE S2: elaborate the received msg
				String senderId = myCtx.getMsgSenderActorId(msg);
				String msgType  = myCtx.getMsgType(msg);
				if(msgType.equals(ActorContext.dispatch) && content.equals("pressed")){
					if(led.isOn())
						led.turnOff();
					else
						led.turnOn();
					println("The led state is " + (led.isOn() ? "ON" : "OFF"));
				}
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
