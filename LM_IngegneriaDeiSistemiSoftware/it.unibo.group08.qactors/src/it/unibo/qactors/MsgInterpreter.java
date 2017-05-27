package it.unibo.qactors;
import it.unibo.is.interfaces.IOutputView;
import it.unibo.system.SituatedPlainObject;

public class MsgInterpreter extends SituatedPlainObject{
private static MsgInterpreter mySelf;
private ActorContext ctx;
/*
 * SINGLETON
 */
	public static MsgInterpreter getInstance(ActorContext ctx, IOutputView outView){
		if( mySelf == null ) mySelf = new MsgInterpreter(ctx,outView);
		return mySelf;
	}	
	private MsgInterpreter(ActorContext ctx, IOutputView outView){
		super(outView);
		this.ctx = ctx;
	}
	public void elab(String msg ) throws Exception{
		//msg( MSGTYPE, SENDER, RECEIVER, CONTENT, SEQNUM )
		//println("MsgInterpreter elab " + msg );
		QActor dest = ctx.getReceiverActor(msg);
		if( dest != null )  dest.storeMsg(msg);
	}
}
