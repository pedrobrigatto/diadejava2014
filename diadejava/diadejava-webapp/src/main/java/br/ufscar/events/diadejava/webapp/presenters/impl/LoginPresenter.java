package br.ufscar.events.diadejava.webapp.presenters.impl;

import br.ufscar.events.diadejava.model.User;
import br.ufscar.events.diadejava.service.exceptions.RecordNotFoundException;
import br.ufscar.events.diadejava.service.exceptions.ServiceLookupException;
import br.ufscar.events.diadejava.service.factory.DaoFactory;
import br.ufscar.events.diadejava.webapp.controller.AppControllerFactory;
import br.ufscar.events.diadejava.webapp.view.LoginView;
import br.ufscar.events.diadejava.webapp.view.NavigableView.NotificationType;
import br.ufscar.events.diadejava.webapp.view.listeners.LoginHandler;

import com.vaadin.navigator.View;

/**
 * Hander to login operations in the application.
 * 
 * Works close to an implementation of a login view, handling all the sensible 
 * events originated in there, making connections with the back-end when it is the
 * case and consuming services from there and reflecting the outcomes in the view
 * it presents and manages.
 * 
 * @author Pedro Brigatto
 */
public class LoginPresenter implements LoginHandler {
	
	private static final long serialVersionUID = 699822468273293843L;
	
	private LoginView view;
	private LoginCallback loginCallback;

	public LoginPresenter() {}
	
	public LoginPresenter (LoginView view) {
		this.view = view;
	}

	@Override
	public void doLogin(User user) {
		try {
			User foundUser = DaoFactory.getDao(User.class).findByTemplate(user);
			if (foundUser.equals(user)) {
				loginCallback.loginSucceeded();
			} else {
				loginCallback.loginFailed();
				view.notifyUser("Wrong credentials. Would you kindly try again?", NotificationType.ERROR);
			}	
		} catch (RecordNotFoundException e) {
			view.notifyUser("Credentials do not match with any valid user, sorry", NotificationType.ERROR);
		} catch (ServiceLookupException e) {
			view.notifyUser("The system could not perform this operation", NotificationType.ERROR);
		}
	}

	@Override
	public void setView(View view) {
		this.view = (LoginView) view;
		this.view.setLoginHandler(this);
		try {
			setLoginCallback(AppControllerFactory.getDefaultController());
		} catch (ServiceLookupException e) {
			e.printStackTrace();
		}
	}
	
	private void setLoginCallback(LoginCallback callback) {
		this.loginCallback = callback;
	}
}
