package br.ufscar.events.diadejava.webapp.presenters;

import java.io.Serializable;

import br.ufscar.events.diadejava.model.DomainObject;
import br.ufscar.events.diadejava.service.exceptions.PersistenceException;
import br.ufscar.events.diadejava.webapp.view.PopupView;
import br.ufscar.events.diadejava.webapp.view.listeners.EntityChangeListener;

/**
 * Handler to operations of either update or creation  
 * 
 * @author Pedro Brigatto
 *
 * @param <T> The type of the entity being created or updated
 */
public interface EditViewHandler<T extends DomainObject> extends Serializable {
	
	/**
	 * Saves the data in the back-end.
	 * 
	 * @param entity Object containing the data to be saved in the back-end
	 * @param editMode Indicates whether the operation is an update or not
	 * 
	 * @throws PersistenceException if the operation could not be processed in the back-end
	 */
	void save(T entity, boolean editMode) throws PersistenceException;
	
	/**
	 * Cancels the operation of either an record update or creation
	 * 
	 * @param entity The entity being edited when the operation is cancelled.
	 * @param editMode Indicates whether the operation is an update or creation
	 */
	void cancel(T entity, boolean editMode);
	
	/**
	 * Defines the view to be managed and presented by the view handler.
	 * 
	 * @param view The view to present data processed by the handler
	 */
	void setView(PopupView<T> view);
	
	/**
	 * Registers a handler to track changes performed in a given entity. 
	 * 
	 * @param changeListener The object to be defined as the change handler
	 */
	void setChangeListener(EntityChangeListener<T> changeListener);
}
