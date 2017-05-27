package it.unibo.domain.interfaces;

public interface IButtonSynch {
  	public boolean isPressed();	//property
 	public void waitPressed();	//synchronizer
 	public void reset();	//modifier
}
