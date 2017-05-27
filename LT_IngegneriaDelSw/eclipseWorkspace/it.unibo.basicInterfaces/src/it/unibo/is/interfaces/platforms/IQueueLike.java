 /*
  --------------------------------------------------
 	AUTOMATICALLY GENERATED from QueueLikeCl language
 	by AN - DEIS University of Bologna - Italy
 -------------------------------------------------
*/
package it.unibo.is.interfaces.platforms;
import it.unibo.is.interfaces.IMessage;
/*
  --------------------------------------------------
  QueueLike-INTERFACE  
  -------------------------------------------------
*/
public interface IQueueLike {

public IMessage remove( String query ) throws Exception;

public boolean check( String queryMsg ) throws Exception;

public void insert( String msg ) throws Exception;
}
