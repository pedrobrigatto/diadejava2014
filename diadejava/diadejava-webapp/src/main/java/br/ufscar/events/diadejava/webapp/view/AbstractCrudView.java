package br.ufscar.events.diadejava.webapp.view;

import java.util.Set;

import br.ufscar.events.diadejava.model.DomainObject;
import br.ufscar.events.diadejava.webapp.presenters.CrudViewHandler;

import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

/**
 * Generic representation of a view offering all CRUD operations to end users.
 * By 'CRUD views' it can be understood 'very view that offers to the end user 
 * the following set of operations:
 * 
 * <ul>
 * <li>Creation of a record</li>
 * <li>Retrieval of records from the back-end</li>
 * <li>Update of an existing record</li>
 * <li>Deletion of a selected record</li>
 * </ul>
 * 
 * @author Pedro Brigatto
 *
 * @param <T> Type of data the view holds/manipulates
 */
@SuppressWarnings("rawtypes")
public abstract class AbstractCrudView<T extends DomainObject> 
extends CustomComponent implements CrudView {
	
	private static final long serialVersionUID = -6216346535667100121L;
	
	protected AbstractLayout mainLayout;
	protected NavigationListener navigationListener;
	protected CrudViewHandler viewHandler;
	
	/**
	 * Instantiates a view and keeps its identifier stored.
	 * 
	 * @param viewId The identifier to the view, used in navigation purposes
	 */
	protected AbstractCrudView() {}
	
	/** 
	 * Used to configure the view already instantiated.
	 * This can be interpreted as a lazy 'load' of the elements, used only
	 * when the components really need to be created and configured, so the 
	 * view gets finally ready to be displayed to the end user.
	 */
	@Override
	public AbstractCrudView<T> build() {
		initComponents();
		configureEventHandling();
		updateLayout();
		setCompositionRoot(mainLayout);
		return this;
	}
	
	/** Used to configure all the components of the view. */
	protected abstract void initComponents();
	
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
	 * Used to refresh the data of the view, mostly important when users are
	 * searching for data or are about to finish the operation of either saving, 
	 * updating or deleting a record. 
	 * 
	 * @param
	 * 
	 */
	public abstract void reloadData(Set<T> elements);

	protected void setMainLayout(AbstractLayout mainLayout) {
		this.mainLayout = mainLayout;
	}
	
	public CrudViewHandler getViewHandler() {
		return this.viewHandler;
	}
	
	public void setViewHandler(CrudViewHandler viewHandler) {
		this.viewHandler = viewHandler;
	}
}
