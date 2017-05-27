package it.unibo.domain.interfaces;
import it.unibo.is.interfaces.IBasicUniboEnv;
import java.awt.Panel;

public interface IDevButtonGui extends IDevInputImpl{
	public Panel getPanel();		//property
	public IBasicUniboEnv getEnv();	//property
}
