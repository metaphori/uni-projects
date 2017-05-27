 /*
  --------------------------------------------------
 	AUTOMATICALLY GENERATED from LindaLikeCl language
 	by AN - DEIS University of Bologna - Italy
 -------------------------------------------------
*/
package it.unibo.is.interfaces.platforms;
import java.util.*;

import it.unibo.is.interfaces.*;
/*
  --------------------------------------------------
  LindaLike-INTERFACE  
  -------------------------------------------------
*/
public interface ILindaLike {
public IMessage in( String query ) throws Exception;
public IMessage rd( int LastMsgNum, String queryMsg ) throws Exception;
public IMessage rdw( int LastMsgNum, String queryMsg ) throws Exception;
public void out( String msg ) throws Exception;
public Vector<IMessage> inMany( Vector<String> tokens ) throws Exception;
public IMessage selectOneFromMany( Hashtable<String,String> signalMemo, Vector<IMessage> queries ) throws Exception;
public void terminate() throws Exception;
}
