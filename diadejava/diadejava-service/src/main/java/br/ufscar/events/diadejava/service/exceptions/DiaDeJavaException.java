package br.ufscar.events.diadejava.service.exceptions;

import br.ufscar.events.diadejava.model.DomainObject;

/**
 * Generic representation of exceptions happening in the context of
 * services used in the application.
 * 
 * @author Pedro Brigatto
 */
public class DiaDeJavaException extends Exception {

	private static final long serialVersionUID = -6573084272640146339L;
	private DomainObject entity;

	public DiaDeJavaException() {
		super();
	}

	public DiaDeJavaException(String message) {
		super(message);
	}

	public DomainObject getEntity() {
		return entity;
	}

	public void setEntity(DomainObject entity) {
		this.entity = entity;
	}
}
