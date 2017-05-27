package it.unibo.robotSystemStep2;
import it.unibo.contactEvent.interfaces.IEventItem;
import it.unibo.robotActor.utils.RobotSysKb;

public class HandlePhoto extends HandlePhotoSupport{
	protected boolean insideSensibleArea = false;

	public HandlePhoto(String name, String[] eventId) throws Exception {
		super(name, eventId);
		RobotSysKb.addRule("outsideSensibleArea");
 	}
	@Override
	public void doJob() throws Exception {
		IEventItem event = getEventItem();
		//String msg = event.getEventId() + " msg:" + event.getMsg() + " from:" + event.getSubj();
		
		println("////////////////// PHOTO TAKEN ////////////////");

	}	

}
