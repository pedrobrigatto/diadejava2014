package br.ufscar.events.diadejava.webapp.view;

import br.ufscar.events.diadejava.model.DomainObject;
import br.ufscar.events.diadejava.webapp.presenters.EditViewHandler;
import br.ufscar.events.diadejava.webapp.view.NavigableView.NotificationType;

import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Window;

/**
 * The most abstract implementation of a pop-up view.
 * By 'pop-up view' it can be understood 'any window the user can interact with', 
 * displayed always over navigable views in a modal shape.
 * 
 * This class, so, defines the general behavior every pop-up window must have and, 
 * at the same time, implements the set of operations and defines the set of
 * attributes common to every view of this nature.
 * 
 * @author Pedro Brigatto
 *
 * @param <T> The type of data being manipulated in the view
 */
public abstract class AbstractPopupView<T extends DomainObject> extends CustomComponent implements PopupView<T> {

	private static final long serialVersionUID = 3518519737219972192L;
	
	protected AbstractLayout mainLayout;
	protected EditViewHandler<T> viewHandler;
	protected boolean editMode;

	public AbstractPopupView() {}
	
	public AbstractPopupView<T> build() {
		initComponents();
		configureEventHandling();
		updateLayout();
		setCompositionRoot(mainLayout);
		return this;
	}
	
	/**
	 * Initializes all the components, configuring the relevant attributes and 
	 * other characteristics like position, etc.
	 */
	protected abstract void initComponents();
	
	/**
	 * Configures all the event handling of every component which events are 
	 * relevant in the user experience.
	 */
	protected abstract void configureEventHandling();
	
	/**
	 * Used when the components of the view, already constructed and configured, 
	 * are ready to be accommodated in the main layout.
	 */
	protected abstract void updateLayout();

	@Override
	public Window createWindow() {
		Window window = new Window("Edit Record");
		window.setWidth(40, Unit.PERCENTAGE);
		window.setHeight(60, Unit.PERCENTAGE);
		window.setContent(this.build());
		window.setModal(true);
		window.setClosable(true);
		window.setResizable(false);
		return window;
	}

	@Override
	public void setViewHandler(EditViewHandler<T> viewHandler) {
		this.viewHandler = viewHandler;
	}

	@Override
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}
	
	@Override
	public boolean isEditMode() {
		return this.editMode;
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
}
