package br.ufscar.events.diadejava.service.exceptions;


/**
 * Represents a problem that happened when an entity was about to be
 * persisted in the data store.
 * 
 * @author Pedro Brigatto
 */
public class PersistenceException extends DiaDeJavaException {

	private static final long serialVersionUID = 4452699488917354377L;

	public PersistenceException() {
		super();
	}

	public PersistenceException(String message) {
		super(message);
	}
}
