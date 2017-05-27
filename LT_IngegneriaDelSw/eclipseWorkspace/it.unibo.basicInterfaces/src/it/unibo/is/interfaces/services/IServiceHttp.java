package it.unibo.is.interfaces.services;

import it.unibo.is.interfaces.protocols.IHttpInteraction;

public interface IServiceHttp {	
	public IHttpInteraction getHttpSupportClient(String url);
}
