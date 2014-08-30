package br.ufscar.events.diadejava.webapp.view;

import java.io.Serializable;

import com.vaadin.ui.Window;

import br.ufscar.events.diadejava.model.DomainObject;
import br.ufscar.events.diadejava.webapp.presenters.EditViewHandler;
import br.ufscar.events.diadejava.webapp.view.NavigableView.NotificationType;

/**
 * Definition of a view to be displayed as a modal pop-up window to let end users
 * edit a record having the full context just behind it. 
 * 
 * @author Pedro Brigatto
 *
 * @param <T> The type of data the view manipulates
 */
public interface PopupView<T extends DomainObject> extends Serializable {
	
	/**
	 * Builds the window used to hold the view.
	 * 
	 * @return The window the view will be displayed into.
	 */
	Window createWindow();
	
	/**
	 * Defines an agent responsible to act upon all events generated in the view.
	 * 
	 * @param handler The object to handle the events generated in the view
	 */
	void setViewHandler (EditViewHandler<T> handler);
	
	/**
	 * Saves a record in the back-end. 
	 * 
	 * @param entity The entity to be saved in the back-end.
	 * @param updateMode <code>true</code> if the operation is an update. 
	 *                   <code>false</code> if a brand new record is being created
	 */
	void save(T entity, boolean updateMode);
	
	/**
	 * Cancels the operation of either an record update or generation.
	 * 
	 * @param entity Data that was being edited when cancellation happens
	 */
	void cancel(T entity);
	
	/**
	 * Defines whether the view is being used to update an existing record or to
	 * create a new record in the system.
	 * 
	 * @param editMode <code>true</code> if the view is used to update a record
	 *                 <code>false</code> if the view is used to create a record
	 */
	void setEditMode(boolean editMode);
	
	/**
	 * Indicates the purpose of the user when interacting with the pop-up view, 
	 * editing a record.
	 * 
	 * @return <code>true</code> if the record already exists and is being edited
	 *         <code>false</code> if the record is pretty new and is being added
	 */
	boolean isEditMode();
	
	/**
	 * Indicates operation outcomes to the end user.
	 * 
	 * @param message The message to be displayed.
	 * @param messageType Determines how the message is displayed to the user
	 */
	void notifyUser(String message, NotificationType messageType);
}
