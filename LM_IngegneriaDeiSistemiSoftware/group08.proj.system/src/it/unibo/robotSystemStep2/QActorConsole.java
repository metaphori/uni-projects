package it.unibo.robotSystemStep2;

import java.io.*;

import it.unibo.is.interfaces.IOutputView;
import it.unibo.qactors.ActorContext;
import it.unibo.qactors.QActor;

public class QActorConsole extends QActor{

	private int k = 0;
	
	public QActorConsole(String actorId, ActorContext myCtx,
			IOutputView outView) {
		super(actorId, myCtx, outView);
 	}

	@Override
	protected void doJob() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		println("************ CONSOLE ACTOR ************");
		println("Press 'q' to exit.");
		String input = "";
		while(!input.equals("q")){
			println("Perform your command: ");
			input = br.readLine();
			if(input.equals("halt")){
					this.platform.raiseEvent(getName(), "haltCmd", "" + (k++));
					println("===> 'Halt' event raised correctly.");
			}
			Thread.sleep(500);
		}
		println("Bye bye!!!");
	}

}
