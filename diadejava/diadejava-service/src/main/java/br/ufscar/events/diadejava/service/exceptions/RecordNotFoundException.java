package br.ufscar.events.diadejava.service.exceptions;

import br.ufscar.events.diadejava.model.DomainObject;

/**
 * Represents a scenario where records were expected to exist in the
 * data store but the query results not even a single element based on
 * the criteria provided to the search. 
 *  
 * @author Pedro Brigatto
 */
public class RecordNotFoundException extends DiaDeJavaException {

	private static final long serialVersionUID = 8298450688604622834L;
	
	private DomainObject entity;

	public RecordNotFoundException() {
		super();
	}

	public RecordNotFoundException(String message) {
		super(message);
	}

	public DomainObject getEntity() {
		return entity;
	}

	public void setEntity(DomainObject entity) {
		this.entity = entity;
	}
}
