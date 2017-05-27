package it.unibo.is.interfaces.platforms;
import it.unibo.is.interfaces.IMessage;

	public interface IAcquireManyReply {
		public int numOfReplyExpected();
		public int numOfReplyReceived();		
		public IMessage acquireReply(int n) throws Exception; //1<=n<=numOfReplyExpected()
	}
