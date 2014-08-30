package br.ufscar.events.diadejava.webapp.controller;

import java.io.Serializable;
import java.util.Set;

import br.ufscar.events.diadejava.model.DomainObject;
import br.ufscar.events.diadejava.webapp.view.NavigableView;
import br.ufscar.events.diadejava.webapp.view.NavigableView.NavigationListener;
import br.ufscar.events.diadejava.webapp.view.listeners.LoginHandler.LoginCallback;
import br.ufscar.events.diadejava.webapp.view.PopupView;

/**
 * Contract to be followed by every controller designed to control the 
 * application state and behavior.
 * 
 * @author Pedro Brigatto
 */
public interface AppController extends Serializable, NavigationListener, LoginCallback {
	
	/**
	 * Performs the application bootstrap, adding all the main views to the control
	 * of a navigator, making them available to the user.
	 * 
	 * @param mainViews
	 */
	void initApplication(Set<NavigableView> mainViews);
	
	/**
	 * Starts the edition of a record by opening a pop-up window to the user.
	 * 
	 * @param popupView The window to be displayed
	 * @param editMode <code>true</code> if an existing record will be updated
	 *                 <code>false</code> if a new record will be edited
	 */
	public <T extends DomainObject> void startEdition(PopupView<T> popupView, boolean editMode);
	
	/**
	 * Ends the operation of editing a record in the application.
	 * 
	 * @param editMode <code>true</code> if the operation is related to an existing record
	 *                 <code>false</code> if a new record is about to be persisted
	 */
	public <T extends DomainObject> void finishEdition(boolean editMode);
	
	/**
	 * Cancels the operation of editing a record in the application
	 * 
	 * @param editMode <code>true</code> if the operation was related to an existing record
	 *                 <code>false</code> if a new record was about to be persisted
	 */
	public <T extends DomainObject> void cancelEdition(boolean editMode);
}
