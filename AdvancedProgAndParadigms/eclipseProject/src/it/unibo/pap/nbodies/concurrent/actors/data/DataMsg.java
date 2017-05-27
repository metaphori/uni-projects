package it.unibo.pap.nbodies.concurrent.actors.data;

import it.unibo.pap.nbodies.Body;

public class DataMsg {
	public final Body body;
	public final long state_number;
	
	public DataMsg(Body body, long state_number){
		this.body = body;
		this.state_number = state_number;
	}
}
