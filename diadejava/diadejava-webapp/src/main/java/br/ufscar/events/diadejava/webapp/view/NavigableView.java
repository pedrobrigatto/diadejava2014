package br.ufscar.events.diadejava.webapp.view;

import com.vaadin.navigator.View;

/**
 * The most generic definition of a view that is navigable.
 * 
 * @author Pedro Brigatto
 */
public interface NavigableView extends View {
	
	public static enum NotificationType {ERROR, WARNING, INFORMATION};
	
	/**
	 * Defines the agent responsible to handle navigation events as users 
	 * interacts with the application.
	 * 
	 * @param listener The object to handle the navigation events
	 */
	void setNavigationListener(NavigationListener listener);
	
	/**
	 * Provides a reference to the navigation listener of the view, so a navigation
	 * can start from it to any other view needed in the given context.
	 * 
	 * @return Object listening to the navigation events of the view
	 */
	NavigationListener getNavigationListener();
	
	/**
	 * Used to discover what identifier uniquely represents the view in the stack.
	 * 
	 * @return The identifier for the view
	 */
	String getViewId();
	
	/**
	 * Initializes the view.
	 * 
	 * @return The view reference, completely built and configured.
	 */
	NavigableView build();
	
	/**
	 * Prompts a message to the user to indicate an event that has happened.
	 * 
	 * @param message The message to be displayed
	 * @param messageType Determines the visual approach to be applied to the message 
	 */
	void notifyUser(String message, NotificationType messageType);
	
	/**
	 * 
	 * @author Pedro Brigatto
	 */
	public interface NavigationListener {
		
		/**
		 * 
		 * @param toViewId
		 */
		void navigationStarted(String toViewId);
	}
}
