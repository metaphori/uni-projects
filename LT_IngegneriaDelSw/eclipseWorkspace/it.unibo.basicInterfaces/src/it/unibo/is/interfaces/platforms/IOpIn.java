
/* ------------------------------------------
   Automatically generated by AN
   ------------------------------------------ */

package it.unibo.is.interfaces.platforms;
import it.unibo.is.interfaces.IMessage;
import it.unibo.is.interfaces.IPolicy;
import java.util.*;

public interface IOpIn{



//Input operation with no reply/answer  sense
public IMessage sense( String WorkerName, String SignalId, IPolicy policy, int lastMsgNum ) throws Exception
;//sense


//Input operation with no reply/answer  sensing
public IMessage sensing( String WorkerName, String SignalId, IPolicy policy, int lastMsgNum ) throws Exception
;//sensing




//Input operation with no reply/answer  remove
public IMessage remove( String WorkerName, String TokenId, IPolicy policy  ) throws Exception
;//remove


//Input operation with no reply/answer  check
public IMessage check( String WorkerName, String TokenId, IPolicy policy, int lastMsgNum ) throws Exception
;//check


//Input operation with no reply/answer  removeAll
public Vector<IMessage> removeAll( String WorkerName, Vector<String> msgIds, IPolicy policy  ) throws Exception
;//removeAll


//Input operation with no reply/answer  serve
public IMessage serve( String WorkerName, String DispatchId, IPolicy policy  ) throws Exception
;//serve




//Input operation with reply/answer given by the platform
public  IMessage accept( String WorkerName, String InvitationId, IPolicy policy  ) throws Exception
;//accept







//Input operation with reply/answer given by the application
public IMessageAndContext grant( String WorkerName, String RequestId, IPolicy policy  ) throws Exception
;//grant



public void terminate() throws Exception;



}
