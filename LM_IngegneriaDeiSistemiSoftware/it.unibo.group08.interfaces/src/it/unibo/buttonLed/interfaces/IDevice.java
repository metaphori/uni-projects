package it.unibo.buttonLed.interfaces;
/*
 * -----------------------------------------------------------
 * This is a first model of a (IOT) Device:
 * an entity with a name and 
 * a default representation (expressed in Prolog syntax)
 * -----------------------------------------------------------
 */
public interface IDevice {
	public String getName(); 	    //property	
	public String getDefaultRep(); 	//mapping
}