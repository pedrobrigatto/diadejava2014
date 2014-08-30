package br.ufscar.events.diadejava.webapp.view;

import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

/**
 * This is the most abstract implementation of a navigable view.
 * By 'navigable view' it can be understood 'any view that user can navigate from and
 * to during his/her experience with the application.
 * 
 * This class, so, defines the general behavior every navigable view must implement
 * and, at the same time, implements the set of operations and defines the set of
 * attributes common to every view of this nature.
 *  
 * @author Pedro Brigatto
 */
public abstract class AbstractNavigableView extends CustomComponent implements NavigableView {

	private static final long serialVersionUID = 6583140783807918087L;
	
	protected AbstractLayout mainLayout;
	protected NavigationListener navigationListener;

	public AbstractNavigableView() {}
	
	/** Used to configure all the components of the view. */
	protected abstract void initComponents();

	@Override
	public void setNavigationListener(NavigationListener navigationListener) {
		this.navigationListener = navigationListener;
	}

	@Override
	public NavigationListener getNavigationListener() {
		return this.navigationListener;
	}

	@Override
	public NavigableView build() {
		initComponents();
		configureEventHandling();
		updateLayout();
		setCompositionRoot(mainLayout);
		return this;
	}

	@Override
	public void notifyUser(String message, NotificationType messageType) {
		if (NotificationType.ERROR == messageType) {
			Notification.show("Error!", message, Type.ERROR_MESSAGE);
		} else if (NotificationType.INFORMATION == messageType) {
			Notification.show("Info", message, Type.HUMANIZED_MESSAGE);
		} else if (NotificationType.WARNING == messageType) {
			Notification.show("Warning!", message, Type.WARNING_MESSAGE);
		}
	}

	/**
	 * Used to prepare the components that should be handled when an event 
	 * is raised by them.
	 */
	protected abstract void configureEventHandling();
	
	/**
	 * Used to finally load all the already created and configured components
	 * in the main container.
	 */
	protected abstract void updateLayout();
}
