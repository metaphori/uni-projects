package it.unibo.is.interfaces;
public interface IMessage extends IBasicMessage{
	public String msgEmitter();	
	public String msgId();	
	public String msgContent();	
	public String msgNum();
}