/*
 * Defines the  properties of any context and utility methods
 * 
 * KEY-POINTS:
 * -) An application system is made of a collections of N subsystems (nodes) called Contexts
 * -) The application system is described by a declarative set of rules written in Prolog syntax. For example:
		context( ctx1, "192.168.43.229", "TCP", "8010" ).
		context( ctx2, "192.168.43.229", "TCP", "8020" ).		 
		qactor( qa1, ctx1 ).
		qactor( qa2, ctx2 ).
 * .) A context is the environment for QActors (Quasi-Actors)
 * .) A QActor has an unique name (lowercase) in the system and belongs to just one context
 * .) A context has a unique name (lowercase)  in the system and a unique communication port 
 * .) The context communication port is used by a connection-based communication protocol (TCP/UDP,...)
 * .) Each context activates one CtxServerAgent to receive messages from the other contexts
 * .) Each context activates N-1 SenderAgent to send qactor-messages to the other contexts
 * .) A qactor-message is structured as follows
   			msg( MSGTYPE, SENDER, RECEIVER, CONTENT, SEQNUM )
 * .) The MSGTYPE  can be: dispatch request answer
 * .) The message payload (CONTENT) is a String built according to the rules of an application-dependent concrete syntax
 * .) The SEQNUM  is (the string representation of) a natural number increased by a context each time a new message is sent
 */
package it.unibo.qactors;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Iterator;
import alice.tuprolog.Prolog;
import alice.tuprolog.SolveInfo;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.Theory;
import it.unibo.contactEvent.interfaces.IContactComponent;
import it.unibo.is.interfaces.IOutputView;
import it.unibo.is.interfaces.protocols.IConnInteraction;
import it.unibo.system.SituatedPlainObject;
import it.unibo.system.SituatedSysKb;

public abstract class ActorContext<T> extends SituatedPlainObject{
/*
 * DEFINITIONS	
 */
	public static final String dispatch = "dispatch";
	public static final String request  = "request";
	public static final String answer   = "answer";
//-------------------------------------------------------------	
	
	 
	
protected int ctxPort = 0;
protected InputStream sysKbStream;
protected InputStream sysRulesStream;
protected MsgInterpreter interpreter;	
protected Prolog prologEngine;	
protected int msgNum = 0;

protected  Hashtable<String,SenderObject> ctxTable;
protected  Hashtable<String,QActor> actorTable;
public     Hashtable<String,IConnInteraction> connectionTable;	//TODO protected
protected  Hashtable<String,IContactComponent> contactComponentTable;

	public ActorContext(String name, IOutputView outView,  InputStream sysKbStream, InputStream sysRulesStream ) throws Exception{
		super(name.toLowerCase(), outView);
		this.sysKbStream    = sysKbStream;
		this.sysRulesStream = sysRulesStream;
 		init();
		println("ACTIVATED " + name + " ncores=" +  SituatedSysKb.numberOfCores );
 	}	
	public int newMsgnum(){
		msgNum++;
		return msgNum;
	}
	public Prolog getPrologEngine(){
		return prologEngine;
	}
	public void registerActor(String actorId, QActor actor){
		actorTable.put(actorId, actor);
		println("ActorContext REGISTERED and ACTIVATED " + actorId  );
		/* =====================================================
		 * The qactors could work one at the time ...
		/* =====================================================
		 */
		actor.activate(SituatedSysKb.executorOneThread);
		/* =====================================================
		 * ... otherwise the qactors could work "in parallel"
		/* =====================================================
		 */
		//actor.activate(SituatedSysKb.executor4Thread);
	}
	public void registerContactComponent(String compId, IContactComponent comp){
		contactComponentTable.put(compId, comp);
 		println("ActorContext REGISTERED IContactComponent " + compId  );
	}
	public MsgInterpreter getMsgInterpreter(){
		return interpreter;
	}
	protected void init() throws Exception{
		SituatedSysKb.init();	//Allows us to restart an application like happens in Android
		actorTable 				= new Hashtable<String,QActor>();
		contactComponentTable 	= new Hashtable<String,IContactComponent>();
		connectionTable			= new Hashtable<String,IConnInteraction>();
		loadSystemTheory();		//TO RUN FIRST	 
		activateTheServerAgent();
		activateSenderAgents();
	}
 	protected void activateTheServerAgent(){
		interpreter = MsgInterpreter.getInstance(this,outView); //create the singleton
  		new CtxServerAgent(name+"_Server", this, outView,  ctxPort );		
	}
	public void activateSenderAgents() throws Exception{
		ctxTable 	    = new Hashtable<String,SenderObject>();
		SolveInfo sol   = prologEngine.solve("getCtxNames( CTXNAMES ).");	
		Struct ctxList  = (Struct) sol.getVarValue("CTXNAMES");
		Iterator<? extends Term> it = ctxList.listIterator();
		while( it.hasNext() ){
			String curOtherCtx      = ""+it.next();
			//println(" -------------- curCtx: " + curCtx );
			int curPort   	   =  getCtxPort( curOtherCtx );
			String curHostName =  getCtxHost( curOtherCtx );
			curHostName 	   =  curHostName.replaceAll("'", "");
			String protocol    =  getCtxProtocol( curOtherCtx );
			protocol = protocol.replaceAll("'", "");
			//FactoryProtocol.TCP
			if( ctxPort != curPort ){
				//setConn(  protocol, curHostName,  curPort);SenderAgent
				//println("Other port: "+ curPort );
				SenderObject sa = new SenderObject("sa_"+ctxPort, this, outView, protocol, curHostName, curPort );
				ctxTable.put(curOtherCtx, sa);
			}
		}
	}
//	protected void setConn(final String protocol, final String curHostName, final int curPort){
//		new Thread(){
//			public void run(){
//				try {
//					FactoryProtocol fp = new FactoryProtocol(outView, protocol, "fp");
//		 			//println("ActorContext try conn " +  protocol + " " + curHostName + " " + fp);
//					IConnInteraction conn = 
//					fp.createClientProtocolSupport(curHostName, curPort);
//					connectionTable.put(curHostName, conn);
//					println("ActorContext DONE conn to "  + curHostName   );
//				} catch (Exception e) {
//					println("ActorContext ERROR " + e.getMessage() );
//				}
//				
//			}
//		}.start();
//	}
	protected void loadSystemTheory() throws Exception{
		prologEngine 	  = new Prolog();
  		Theory configTh = new Theory( sysKbStream );
  		Theory rulesTh  = new Theory( sysRulesStream );
		prologEngine.addTheory(configTh);
		prologEngine.addTheory(rulesTh);
		ctxPort = getCtxPort( getName() );
		//println("ctxPort " + ctxPort );
 	}
	
	public abstract void configure();
	/*
	 * Utility methods
	 */
	public QActor getActor(String actorId){
		return actorTable.get(actorId);
	}
	public IContactComponent getContactComponent(String compId){
		return contactComponentTable.get(compId);
	}
	public String getMsgReceiverActorId( String msg ) throws Exception{
		//println("getMsgActorId of " + msg  );
		SolveInfo sol  = prologEngine.solve( "getMsgReceiverId("+ msg +", ACTORID ).");	
		String actorId = ""+sol.getVarValue("ACTORID");
		//println("receiveactorId of " + msg + " = " + actorId);
		return actorId;		
	}
	public String getMsgSenderActorId( String msg ) throws Exception{
		//println("getMsgSenderActorId of " + msg  );
		SolveInfo sol  = prologEngine.solve( "getMsgSenderId("+ msg +", ACTORID ).");	
		String actorId = ""+sol.getVarValue("ACTORID");
		//println("actorId of " + msg + " = " + actorId);
		return actorId;		
	}
	public String getMsgType( String msg ) throws Exception{
		//println("getMsgSenderActorId of " + msg  );
		SolveInfo sol  = prologEngine.solve( "getMsgType("+ msg +", MSGTYPE ).");	
		String msgType = ""+sol.getVarValue("MSGTYPE");
		//println("msgType of " + msg + " = " + msgType);
		return msgType;		
	}
	public String getMsgContent( String msg ) throws Exception{
		//println("getMsgSenderActorId of " + msg  );
		SolveInfo sol  = prologEngine.solve( "getMsgContent("+ msg +", MSGCONTENT ).");	
		String msgContent = ""+sol.getVarValue("MSGCONTENT");
		//println("msgType of " + msg + " = " + msgType);
		return msgContent;		
	}	
	public QActor getReceiverActor( String msg ) throws Exception{
		String actorId = getMsgReceiverActorId(   msg );
		return actorTable.get(actorId);
	}
	public IConnInteraction getConnection( String host ) throws Exception{
 		return connectionTable.get(host);
	}
	public SenderObject getSenderAgent(String actorId) throws Exception{
		String ctxName = getActorCtx(actorId);
		return ctxTable.get(ctxName);
	}
	protected String getActorCtx(String actorId) throws Exception{
		SolveInfo sol = prologEngine.solve("qactor( " + actorId +", CTX ).");	
		String ctxName = ""+sol.getVarValue("CTX");
		//println("ctxName of " + actorId + " = " + ctxName);
		return ctxName;
 	}
	protected String getCtxProtocol(String ctxId) throws Exception{
		SolveInfo sol = prologEngine.solve("getCtxProtocol( " + ctxId +", PROTOCOL ).");	
		String protocolName = ""+sol.getVarValue("PROTOCOL");
		//println("protocolName of " + ctxId + " = " + protocolName);
		return protocolName;
 	}
	protected String getCtxHost(String ctxId) throws Exception{
		SolveInfo sol = prologEngine.solve("getCtxHost( " + ctxId +", HOSTNAME ).");	
		String hostName = ""+sol.getVarValue("HOSTNAME");
		//println("hostName of " + ctxId + " = " + hostName);
		return hostName;
 	}
 	protected int getCtxPort(String ctxId) throws Exception{
 		//println("getCtxPort of " + ctxId  );
		SolveInfo sol = prologEngine.solve("getCtxPort( " + ctxId +", PORTNAME ).");	
		String portStr = ""+sol.getVarValue("PORTNAME");
		portStr = portStr.replaceAll("'", "");
		int port = Integer.parseInt(portStr);
		//println("port of " + ctxId + " = " + port);
		return port;
 	}
}
