package br.ufscar.events.diadejava.webapp.presenters;

import java.io.Serializable;

import com.vaadin.navigator.View;

/**
 * Generic definition of a view handler in the application
 * 
 * @author Pedro Brigatto
 */
public interface ViewHandler extends Serializable {
	
	/**
	 * Defines the view the presenter will manage
	 * 
	 * @param view The view to be managed by the presenter
	 */
	void setView(View view);
}
