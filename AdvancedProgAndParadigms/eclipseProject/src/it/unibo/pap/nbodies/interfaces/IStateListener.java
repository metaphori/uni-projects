package it.unibo.pap.nbodies.interfaces;

import it.unibo.pap.nbodies.BodiesConfiguration;

/**
 * This interface is implemented by those components who desire to be notified when
 *  events are fired at the model-side.
 */
public interface IStateListener {

	public void notify_state(long state_count, BodiesConfiguration bodies);
	
}
