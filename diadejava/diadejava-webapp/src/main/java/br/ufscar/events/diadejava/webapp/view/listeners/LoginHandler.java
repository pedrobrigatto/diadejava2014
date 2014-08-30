package br.ufscar.events.diadejava.webapp.view.listeners;

import br.ufscar.events.diadejava.model.User;
import br.ufscar.events.diadejava.webapp.presenters.ViewHandler;

/**
 * Used to react to events happening inside the context of the attempt to
 * get access to the system.
 * 
 * @author Pedro Brigatto
 */
public interface LoginHandler extends ViewHandler {
	
	/**
	 * Initiates the verification and validation of the credentials 
	 * provided by a person. 
	 * 
	 * @param user The probable user of the system
	 * @param callback Object to handle the verification and authentication outcome
	 */
	void doLogin(final User user);
	
	/**
	 * Used to react to the result of the login operation.
	 * 
	 * @author Pedro Brigatto
	 */
	public interface LoginCallback {
		
		/**
		 * Indicates the user has failed to log into the system. 
		 * This may be due to unavailability of the system or maybe because
		 * the credentials provided are invalid. 
		 */
		void loginFailed();
		
		/** Indicates the user has successfully entered the application. */
		void loginSucceeded();
	}
}
