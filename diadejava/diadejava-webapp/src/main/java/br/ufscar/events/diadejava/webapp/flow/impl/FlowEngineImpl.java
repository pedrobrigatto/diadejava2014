package br.ufscar.events.diadejava.webapp.flow.impl;

import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import br.ufscar.events.diadejava.model.DomainObject;
import br.ufscar.events.diadejava.service.exceptions.ServiceLookupException;
import br.ufscar.events.diadejava.webapp.controller.AppControllerFactory;
import br.ufscar.events.diadejava.webapp.flow.FlowEngine;
import br.ufscar.events.diadejava.webapp.presenters.CrudViewHandler;
import br.ufscar.events.diadejava.webapp.presenters.EditViewHandler;
import br.ufscar.events.diadejava.webapp.presenters.ViewHandler;
import br.ufscar.events.diadejava.webapp.view.CrudView;
import br.ufscar.events.diadejava.webapp.view.NavigableView;
import br.ufscar.events.diadejava.webapp.view.PopupView;
import br.ufscar.events.diadejava.webapp.view.listeners.EntityChangeListener;

/**
 * Engine used to build the MVP units and control the flow as users 
 * navigate through all the views in the application. 
 * 
 * @author Pedro Brigatto
 */
public class FlowEngineImpl implements FlowEngine {

	private static final String SETTINGS = "settings.properties";

	private static enum ConfigKeys {
		GENERAL_CONCEPTS, SUPPORTED_ENTITIES, BASE_PATH_PRESENTERS, BASE_PATH_VIEWS,
		MODAL_EDITION_ENTITIES, BASE_PATH_EDITPRESENTERS, BASE_PATH_EDIT_MODAL_VIEWS
	};

	private Properties systemProperties;

	public FlowEngineImpl() {
		systemProperties = new Properties();
		try {
			systemProperties.load(this.getClass().
					getClassLoader().getResourceAsStream(SETTINGS));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void initApplication() throws ServiceLookupException {
		String basePresenter = systemProperties.getProperty(ConfigKeys.BASE_PATH_PRESENTERS.name());
		String baseView = systemProperties.getProperty(ConfigKeys.BASE_PATH_VIEWS.name());
		Set<NavigableView> views = new HashSet<NavigableView>();

		initGeneralViews(views, systemProperties.getProperty( 
				ConfigKeys.GENERAL_CONCEPTS.name()).split(","), baseView, basePresenter);
		initCrudViews(views, systemProperties.getProperty( 
				ConfigKeys.SUPPORTED_ENTITIES.name()).split(","), baseView, basePresenter);
		AppControllerFactory.getDefaultController().initApplication(views);
	}

	private void initGeneralViews(Set<NavigableView> views,
			String[] generalConcepts, String baseViews, String basePresenters) throws ServiceLookupException {
		
		NavigableView view = null;
		ViewHandler viewHandler = null;

		for (String concept : generalConcepts) {
			try {
				view = (NavigableView) Class.forName(String.format(baseViews, concept)).newInstance();
				view.setNavigationListener(AppControllerFactory.getDefaultController());
				view.build();
				viewHandler = (ViewHandler) Class.forName(String.format(basePresenters, concept)).newInstance();
				viewHandler.setView(view);
				views.add(view);
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
	private <T extends DomainObject> void initCrudViews(Set<NavigableView> views, 
			String[] entities, String baseViews, String basePresenters) throws ServiceLookupException {
		
		CrudViewHandler<T> viewHandler = null;
		CrudView<T> view = null;

		for (String entity : entities) {
			try {
				view = (CrudView<T>) Class.forName(String.format(baseViews, entity)).newInstance();
				view.setNavigationListener(AppControllerFactory.getDefaultController());
				view.build();
				viewHandler = (CrudViewHandler<T>) Class.forName(String.format(basePresenters, entity)).newInstance();
				viewHandler.setView(view);
				views.add(view);
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public <T extends DomainObject> void startEdition(T entity, boolean editMode, EntityChangeListener<T> changeListener) {
		String basePresenter = systemProperties.getProperty(ConfigKeys.BASE_PATH_EDITPRESENTERS.name());
		String baseView = systemProperties.getProperty(ConfigKeys.BASE_PATH_EDIT_MODAL_VIEWS.name());
		
		EditViewHandler<T> viewHandler = null;
		PopupView<T> view = null;
		
		try {
			view = (PopupView<T>) Class.forName(
					String.format(baseView, entity.getClass().getSimpleName())).newInstance();
			view.setEditMode(editMode);
			
			viewHandler = (EditViewHandler<T>) Class.forName(
					String.format(basePresenter, entity.getClass().getSimpleName())).newInstance();
			viewHandler.setView(view);
			viewHandler.setChangeListener(changeListener);
			AppControllerFactory.getDefaultController().startEdition(view, editMode);
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | ServiceLookupException e) {
			e.printStackTrace();
		}
	}

	@Override
	public <T extends DomainObject> void endEdition(T entity, boolean editMode) {
		try {
			AppControllerFactory.getDefaultController().finishEdition(editMode);
		} catch (ServiceLookupException e) {
			e.printStackTrace();
		}
	}

	@Override
	public <T extends DomainObject> void cancelEdition(T entity,
			boolean editMode) {
		try {
			AppControllerFactory.getDefaultController().cancelEdition(editMode);;
		} catch (ServiceLookupException e) {
			e.printStackTrace();
		}
	}
}
