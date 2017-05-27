package it.unibo.robotSystemStep2;
 
import it.unibo.is.interfaces.IOutputView;
  
public class TakePhoto extends TakePhotoSupport{
private int n=1;
/*
 * The empty constructor is required since the system must create 
 * a new instance for each call
 */
public TakePhoto() throws Exception {
	super();
	this.interrupted = false;
}
	public TakePhoto(String name, boolean cancompensate, String terminationEvId, String answerEvId,
			IOutputView outView, long maxduration) throws Exception {
		super(name, cancompensate, terminationEvId, answerEvId, outView, maxduration);
  	}
  
	@Override
	protected String actionBody() throws Exception {
		try {
		/*
		long execTime = this.maxduration ;// ;
		this.println("TAKEPHOTO is working for arg=" + this.arg + " n=" + n +" execTime=" + execTime);
		n++;		
		this.sleepWithCare(execTime,10);	//this allow to be sensible to interruptions
		this.println("TAKEPHOTO ENDS interrupted="  + this.interrupted  );
		*/
		println("TAKE PHOTO action performing.............................................");
		
			Runtime.getRuntime().exec("fswebcam " + this.arg).waitFor();
			println("PHOTO PERFORMED");
		
		println("EXITING TAKEPHOTO ACTION BODY, interrupted= " + this.interrupted);
		} catch (Exception e) {
			println("EXCEPTION for TAKE PHOTO " + e.getMessage());
		}
		return "done";
	}

	@Override
	public void suspendAction(){
		super.suspendAction();
  		println("TAKEPHOTO " + getName() + " suspendAction "   );		
	}
 

}
