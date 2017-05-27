package it.unibo.robotSystemStep2;
import it.unibo.contactEvent.interfaces.IEventItem;
import it.unibo.robotActor.utils.RobotSysKb;

public class HandleLine { //extends HandleLineSupport{
	protected boolean insideSensibleArea = false;

	/*
	public HandleLine(String name, String[] eventId) throws Exception {
		super(name, eventId);
		RobotSysKb.addRule("outsideSensibleArea");
 	}
	@Override
	public void doJob() throws Exception {
		IEventItem event = getEventItem();
		//String msg = event.getEventId() + " msg:" + event.getMsg() + " from:" + event.getSubj();
		
		if(!event.getEventId().equals("Line"))
			return;
		
		insideSensibleArea = !insideSensibleArea;
		
		if (insideSensibleArea){ 
			this.evPlatform.raiseEvent("robot", "RobotEvent"+System.currentTimeMillis(), "RobotCtxEnter");
			showMsg( "--------------------------------------------------" );	
			RobotSysKb.removeRule("outsideSensibleArea");
			RobotSysKb.addRule("insideSensibleArea");
			showMsg( "INSIDE sensible area");				 
			showMsg( "--------------------------------------------------" );			
		}else{
			this.evPlatform.raiseEvent("robot", "RobotEvent"+System.currentTimeMillis(), "RobotCtxExit");
			showMsg( "--------------------------------------------------" );	
			RobotSysKb.removeRule("insideSensibleArea");
			RobotSysKb.addRule("outsideSensibleArea");
			showMsg( "OUTSIDE sensible area");				 
			showMsg( "--------------------------------------------------" );			
		}

	}	
	*/
}
