package it.unibo.is.interfaces.protocols;

public interface IConnInteraction {
	public void sendALine(  String msg ) throws Exception;
	public void sendALine(  String msg, boolean isAnswer ) throws Exception;
	public String receiveALine(  ) throws Exception;
	public void closeConnection( ) throws Exception;
}
