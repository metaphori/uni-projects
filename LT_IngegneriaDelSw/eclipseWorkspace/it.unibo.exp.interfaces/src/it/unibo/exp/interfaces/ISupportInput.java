package it.unibo.exp.interfaces;

public interface ISupportInput {
	public void open() throws Exception;
	public void close() throws Exception;
	public String receive() throws Exception;
	public char receiveChar() throws Exception;
	public boolean isEmpty() throws Exception;
	public boolean isClosed() throws Exception;
}
