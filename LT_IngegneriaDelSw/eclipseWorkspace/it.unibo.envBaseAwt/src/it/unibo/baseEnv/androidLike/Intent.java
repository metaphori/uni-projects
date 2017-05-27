package it.unibo.baseEnv.androidLike;

public class Intent {
	private String action;
	public 	Intent(String action){
		this.action = action;
	}
	public String getAction(){
		return action;
	}
	public void setAction(String action){
		this.action = action;
	}
}
