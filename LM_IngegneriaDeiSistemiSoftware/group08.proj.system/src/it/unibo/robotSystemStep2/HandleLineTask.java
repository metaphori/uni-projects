package it.unibo.robotSystemStep2;
import it.unibo.contactEvent.interfaces.IEventItem;
import it.unibo.robotActor.utils.RobotSysKb;

public class HandleLineTask extends HandleLineTaskSupport{
	//protected String curState = "insideSensibleArea";
	protected boolean insideSensibleArea = false;
	int n;

	public HandleLineTask(String name, String[] eventId) throws Exception {
		super(name, eventId);
		RobotSysKb.addRule("outsideSensibleArea");
		showMsg("@@@ HandleLine constructor");
 	}

	@Override
	protected void stateControl() throws Exception {
		try{
		showMsg("@@@ HandleLine state control");
		
		if(curstate.equals("stateInit")){
			curstate = "outsideSensibleArea";
		}
		
			if( curstate.equals("insideSensibleArea")){ whenInside(); }
			else if( curstate.equals("outsideSensibleArea")){ whenOutside(); }
			else showMsg("Bad state::: " + curstate);
		}catch(Exception exc){
			
			exc.printStackTrace();
		}

	}
	protected void whenInside()  throws Exception{
		showMsg("@@@ REACTING LINE EVENT while INSIDE ");
		IEventItem ne = getNextLineEvent();
		if(ne==null){
			showMsg("Next event null");
			transition("Line", "insideSensibleArea");
			return;
		}
		RobotSysKb.removeRule("insideSensibleArea");	
		this.raiseEvent(this.getName(), "RobotStep1Exit", "");
		transition("Line", "outsideSensibleArea");
   	}	

	protected void whenOutside()  throws Exception{
		showMsg("@@@ REACTING LINE EVENT while OUTSIDE ");
		IEventItem ne = getNextLineEvent();
		if(ne==null){
			showMsg("Next event null");
			transition("Line", "outsideSensibleArea");		
			return;
		}
		RobotSysKb.addRule("insideSensibleArea");
		this.raiseEvent(this.getName(), "RobotStep1Enter", "");
		transition("Line", "insideSensibleArea");
   	}
	
	private IEventItem getNextLineEvent() throws Exception{
		showMsg("@@@ Waiting for Line event");
		IEventItem curEvent = null;
		curEvent= this.getEventItem();
		if(curEvent==null)
			return null;
		Thread.sleep(600);
		IEventItem nextEv = this.getEventItem();
		int k=1;
		while(nextEv!=null){
			k++;
			curEvent = nextEv;
			Thread.sleep(200);
			nextEv = this.getEventItem();
		}
		showMsg("@@@ Event item retrieved["+k+"] " + curEvent.getEventId());
		return curEvent;
	}

	@Override
	protected void handleEvent(IEventItem curEvent) {
		showMsg("SHOULD NOT BE IMPL");
	}

}
