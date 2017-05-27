package it.unibo.domain.interfaces;

public interface IEdgeDetector {
	public void setInputDevice(IDevButton inputDev);
	public boolean detectEdge() throws Exception;
}
