package br.ufscar.events.diadejava.webapp.view;

import br.ufscar.events.diadejava.webapp.view.NavigableView.NotificationType;
import br.ufscar.events.diadejava.webapp.view.listeners.LoginHandler;

/**
 * Exposes the behavior to be followed by any view in the system that takes
 * the role of being the very first experience of the user with the application,
 * which is always the access to it.
 * 
 * @author Pedro Brigatto
 */
public interface LoginView {
	
	String ID = "";
	String FRIENDLY_ID = "loginview";
	
	/**
	 * Defines the agent responsible for managing events related to login operations.
	 * 
	 * @param listener The object to handle such events.
	 */
	void setLoginHandler(LoginHandler listener);
	
	/**
	 * Prompts a message to the user to indicate an event that has happened.
	 * 
	 * @param message The message to be displayed
	 * @param messageType Determines the visual approach to be applied to the message 
	 */
	void notifyUser(String message, NotificationType messageType);
}
