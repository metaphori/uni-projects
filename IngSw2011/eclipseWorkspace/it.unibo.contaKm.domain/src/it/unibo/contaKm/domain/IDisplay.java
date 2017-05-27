package it.unibo.contaKm.domain;

import it.unibo.system.IObserver;

public interface IDisplay extends IObserver {
	public void update(String v);
}
