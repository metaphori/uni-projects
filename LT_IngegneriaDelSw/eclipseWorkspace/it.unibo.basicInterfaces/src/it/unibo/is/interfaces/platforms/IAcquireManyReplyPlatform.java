package it.unibo.is.interfaces.platforms;
import it.unibo.is.interfaces.IMessage;

	public interface IAcquireManyReplyPlatform extends IAcquireManyReply{
		public void addReply( IMessage reply );
	}
