package it.unibo.qactors;

import it.unibo.is.interfaces.IMessage;

public class QActorMessage implements IMessage{
//msg( MSGID, SENDER, CONTENT, SEQNUM )
	@Override
	public String getMsgName() {
 		return null;
	}
	@Override
	public String getMsgContent() {
 		return null;
	}
	@Override
	public int getMsgSeqNum() {
 		return 0;
	}
	@Override
	public String basicToString() {
 		return null;
	}
	@Override
	public String msgEmitter() {
 		return null;
	}
	@Override
	public String msgId() {
 		return null;
	}
	@Override
	public String msgContent() {
 		return null;
	}

	@Override
	public String msgNum() {
 		return null;
	}
}
