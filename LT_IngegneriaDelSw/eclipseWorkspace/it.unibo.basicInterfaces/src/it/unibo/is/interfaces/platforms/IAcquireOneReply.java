package it.unibo.is.interfaces.platforms;
import it.unibo.is.interfaces.IMessage;

	public interface IAcquireOneReply {
	 	public boolean replyAvailable();
	 	public IMessage acquireReply() throws Exception;
	}
