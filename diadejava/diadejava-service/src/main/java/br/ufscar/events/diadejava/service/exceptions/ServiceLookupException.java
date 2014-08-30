package br.ufscar.events.diadejava.service.exceptions;

/**
 * Represents a problem when retrieving a service and it cannot be
 * determined/found in the module.
 * 
 * @author Pedro Brigatto
 */
public class ServiceLookupException extends DiaDeJavaException {

	private static final long serialVersionUID = 6836770300204819971L;

	public ServiceLookupException() {}

	public ServiceLookupException(String message) {
		super(message);
	}
}
