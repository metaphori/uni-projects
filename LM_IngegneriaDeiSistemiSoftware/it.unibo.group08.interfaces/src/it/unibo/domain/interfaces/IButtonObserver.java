package it.unibo.domain.interfaces;
import it.unibo.is.interfaces.IObserver;

public interface IButtonObserver extends IObserver{
	public void setControl(IController controller);
}
 