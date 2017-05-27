package it.unibo.is.interfaces.services;

import javax.microedition.io.StreamConnection;

import it.unibo.is.interfaces.IOutputView;
import it.unibo.is.interfaces.protocols.IBthConnection;
import it.unibo.is.interfaces.protocols.IBthInteraction;

public interface IServiceBth {	
	public IBthInteraction getBthConnSupport( String logo, StreamConnection conn, IOutputView view );
	public IBthConnection getBthSupport(String logo, IOutputView view);
}
