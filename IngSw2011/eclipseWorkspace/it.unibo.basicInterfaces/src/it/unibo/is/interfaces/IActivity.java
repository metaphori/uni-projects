package it.unibo.is.interfaces;

public interface IActivity {
	public  void execAction( );
	public  void execAction( String cmd );
	public  void execAction(IIntent input);
	public String execActionWithAnswer(String cmd);
}
