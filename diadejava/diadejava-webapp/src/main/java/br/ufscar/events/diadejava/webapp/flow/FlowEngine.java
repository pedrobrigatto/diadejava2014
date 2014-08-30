package br.ufscar.events.diadejava.webapp.flow;

import br.ufscar.events.diadejava.model.DomainObject;
import br.ufscar.events.diadejava.service.exceptions.ServiceLookupException;
import br.ufscar.events.diadejava.webapp.view.listeners.EntityChangeListener;

/**
 * 
 * @author Pedro Brigatto
 */
public interface FlowEngine {
	
	/**
	 * Initializes the application configuring all the MVP units that 
	 * should be available to the user since the system bootstrap.
	 */
	void initApplication() throws ServiceLookupException;
	
	/**
	 * 
	 * @param entity
	 * @param editMode
	 * @param changeListener
	 */
	<T extends DomainObject> void startEdition(T entity, boolean editMode, EntityChangeListener<T> changeListener);
	
	/**
	 * 
	 * @param entity
	 * @param editMode
	 */
	<T extends DomainObject> void endEdition(T entity, boolean editMode);
	
	/**
	 * 
	 * @param entity
	 * @param editMode
	 */
	<T extends DomainObject> void cancelEdition(T entity, boolean editMode);
}
