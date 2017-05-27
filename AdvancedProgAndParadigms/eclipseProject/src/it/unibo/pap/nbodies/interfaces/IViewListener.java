package it.unibo.pap.nbodies.interfaces;

import java.io.File;

/**
 * This interface is implemented by those components who desire to be notified when
 *  events are fired at the view-side.
 */
public interface IViewListener {

	void createRandomBodyConfiguration();
	void createBodyConfiguration(File configFile);
	void started();
	void stopped();

}
