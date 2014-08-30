package br.ufscar.events.diadejava.webapp.view.impl;

import br.ufscar.events.diadejava.model.User;
import br.ufscar.events.diadejava.webapp.view.AbstractNavigableView;
import br.ufscar.events.diadejava.webapp.view.LoginView;
import br.ufscar.events.diadejava.webapp.view.listeners.LoginHandler;
import br.ufscar.events.diadejava.webapp.view.util.ComponentFactory;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * Default implementation to the login view. 
 * 
 * @author Pedro Brigatto
 */
public class LoginViewImpl extends AbstractNavigableView implements LoginView {
	
	private static final long serialVersionUID = -787866967186204323L;
	
	private static final String ALIAS_CONTENT = "content";
	private static final String ALIAS_LAYOUT = "mainlayout";
	private static final String ALIAS_ACTIONS = "actions";
	private static final String ALIAS_USERNAME = "username";
	private static final String ALIAS_LOGO = "logo";
	private static final String ALIAS_PWD = "pwd";
	private static final String ALIAS_LOGIN = "login";
	private static final String ALIAS_CLEAR = "clean";

	private LoginHandler loginHandler;
	
	private Image logo;
	private VerticalLayout contentLayout;
	private HorizontalLayout actionsContainer;
	private TextField username;
	private PasswordField password;
	private Button doLogin;
	private Button doClean;

	public LoginViewImpl() {}
	
	public LoginViewImpl(LoginHandler handler) {
		this();
		this.loginHandler = handler;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		username.focus();
	}

	@Override
	protected void initComponents() {
		
		logo = ComponentFactory.getFactory().createComponent(FRIENDLY_ID, ALIAS_LOGO, Image.class);
		logo.setIcon(new ThemeResource("img/logo_ufscar_x.png"));
		
		username = ComponentFactory.getFactory().createComponent(FRIENDLY_ID, ALIAS_USERNAME, TextField.class);
		username.setCaption("Username:");
		username.setWidth(400, Unit.PIXELS);
		username.setInputPrompt("Type your username here");
		
		password = ComponentFactory.getFactory().createComponent(FRIENDLY_ID, ALIAS_PWD, PasswordField.class); 
		password.setCaption("Password:");
		password.setWidth(400, Unit.PIXELS);
		password.setInputPrompt("Type your password here");
		
		doLogin = ComponentFactory.getFactory().createComponent(FRIENDLY_ID, ALIAS_LOGIN, Button.class);
		doClean = ComponentFactory.getFactory().createComponent(FRIENDLY_ID, ALIAS_CLEAR, Button.class);
		doLogin.setCaption("Login");
		doClean.setCaption("Clean");
		
		actionsContainer = ComponentFactory.getFactory().createContainer(FRIENDLY_ID, 
				ALIAS_ACTIONS, HorizontalLayout.class, doLogin, doClean);
		actionsContainer.setSpacing(true);
	}

	@SuppressWarnings("serial")
	@Override
	protected void configureEventHandling() {
		doLogin.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				loginHandler.doLogin(getFormData());
			}
		});
		
		doClean.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				username.setValue("");
				password.setValue("");
			}
		});
	}

	@Override
	protected void updateLayout() {
		this.contentLayout = ComponentFactory.getFactory().createContainer(FRIENDLY_ID, 
				ALIAS_CONTENT, VerticalLayout.class, logo, username, password, actionsContainer);
		this.contentLayout.setWidth(400, Unit.PIXELS);
		this.contentLayout.setSpacing(true);
		this.contentLayout.setMargin(new MarginInfo(true, true, true, false));
		this.contentLayout.setComponentAlignment(logo, Alignment.MIDDLE_CENTER);
		this.contentLayout.setComponentAlignment(username, Alignment.MIDDLE_CENTER);
		this.contentLayout.setComponentAlignment(password, Alignment.MIDDLE_CENTER);
		this.contentLayout.setComponentAlignment(actionsContainer, Alignment.MIDDLE_CENTER);
		
		this.mainLayout = ComponentFactory.getFactory().createContainer(FRIENDLY_ID, 
				ALIAS_LAYOUT, VerticalLayout.class, contentLayout);
		((VerticalLayout)this.mainLayout).setMargin(new MarginInfo(true, true, true, false));
		((VerticalLayout)this.mainLayout).setComponentAlignment(contentLayout, Alignment.MIDDLE_CENTER);
		mainLayout.setSizeFull();
	}

	public Button getDoLogin() {
		return doLogin;
	}

	public void setDoLogin(Button doLogin) {
		this.doLogin = doLogin;
	}

	public Button getDoClean() {
		return doClean;
	}

	public void setDoClean(Button doClean) {
		this.doClean = doClean;
	}
	
	private User getFormData() {
		User user = new User();
		user.setUsername(username.getValue());
		user.setPassword(password.getValue());
		return user;
	}

	@Override
	public void setNavigationListener(NavigationListener listener) {
		this.navigationListener = listener;
	}

	@Override
	public String getViewId() {
		return ID;
	}

	@Override
	public void setLoginHandler(LoginHandler listener) {
		this.loginHandler = listener;
	}
}
