package br.ufscar.events.diadejava.webapp.controller.impl;

import java.util.Set;

import br.ufscar.events.diadejava.model.DomainObject;
import br.ufscar.events.diadejava.webapp.controller.AppController;
import br.ufscar.events.diadejava.webapp.view.BookView;
import br.ufscar.events.diadejava.webapp.view.NavigableView;
import br.ufscar.events.diadejava.webapp.view.PopupView;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

/**
 * Reference implementation of an application controller that, together with
 * a flow engine, manages the state of the application and the view to UI
 * displayed to the user at any given time.
 *  
 * @author Pedro Brigatto
 */
public class AppControllerImpl implements AppController {
	
	private static final long serialVersionUID = 6475520001752489532L;
	
	private Navigator navigator;
	private Window currentWindow;

	public AppControllerImpl() {
		navigator = new Navigator(UI.getCurrent(), UI.getCurrent());
	}
	
	@Override
	public void initApplication(Set<NavigableView> mainViews) {
		for (NavigableView view : mainViews) {
			navigator.addView(view.getViewId(), view);
		}
	}

	@Override
	public void navigationStarted(String toViewId) {
		navigator.navigateTo(toViewId);
	}

	@Override
	public void loginFailed() {}

	@Override
	public void loginSucceeded() {
		navigator.navigateTo(BookView.ID);
	}

	@Override
	public <T extends DomainObject> void startEdition(PopupView<T> popupView,
			boolean editMode) {
		currentWindow = popupView.createWindow();
		UI.getCurrent().addWindow(currentWindow);
		currentWindow.center();
	}

	@Override
	public <T extends DomainObject> void finishEdition(boolean editMode) {
		currentWindow.close();
	}

	@Override
	public <T extends DomainObject> void cancelEdition(boolean editMode) {
		UI.getCurrent().removeWindow(currentWindow);
	}
}
