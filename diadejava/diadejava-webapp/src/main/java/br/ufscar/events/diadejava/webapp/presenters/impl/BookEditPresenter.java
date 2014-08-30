package br.ufscar.events.diadejava.webapp.presenters.impl;

import br.ufscar.events.diadejava.model.Book;
import br.ufscar.events.diadejava.service.exceptions.PersistenceException;
import br.ufscar.events.diadejava.service.exceptions.ServiceLookupException;
import br.ufscar.events.diadejava.service.factory.DaoFactory;
import br.ufscar.events.diadejava.webapp.flow.FlowEngineFactory;
import br.ufscar.events.diadejava.webapp.presenters.EditViewHandler;
import br.ufscar.events.diadejava.webapp.view.NavigableView.NotificationType;
import br.ufscar.events.diadejava.webapp.view.PopupView;
import br.ufscar.events.diadejava.webapp.view.listeners.EntityChangeListener;
import br.ufscar.events.diadejava.webapp.view.listeners.EntityChangeListener.ChangeType;

/**
 * 
 * @author Pedro Brigatto
 */
public class BookEditPresenter implements EditViewHandler<Book> {

	private static final long serialVersionUID = 7402359403831350755L;
	
	private PopupView<Book> view;
	private EntityChangeListener<Book> changeListener;

	public BookEditPresenter() {}
	
	public BookEditPresenter(PopupView<Book> view) {
		this.view = view;
	}
	
	public BookEditPresenter(PopupView<Book> view, EntityChangeListener<Book> changeListener) {
		this.view = view;
		this.changeListener = changeListener;
	}

	@Override
	public void save(Book entity, boolean editMode) throws PersistenceException {
		try {
			if (editMode) {
				DaoFactory.getDao(Book.class).update(entity);
			} else {
				DaoFactory.getDao(Book.class).save(entity);
			}
			changeListener.entityChanged(entity, editMode ? ChangeType.UPDATE : ChangeType.CREATION);
			FlowEngineFactory.getDefaultEngine().endEdition(entity, editMode);
		} catch (ServiceLookupException e1) {
			view.notifyUser("The service is unavailable at this moment", NotificationType.ERROR);
		}
	}

	@Override
	public void cancel(Book entity, boolean editMode) {
		try {
			FlowEngineFactory.getDefaultEngine().cancelEdition(entity, editMode);
		} catch (ServiceLookupException e) {
			view.notifyUser("The service is unavailable at this moment", NotificationType.ERROR);
		}
	}

	@Override
	public void setView(PopupView<Book> view) {
		this.view = view;
		this.view.setViewHandler(this);
	}

	@Override
	public void setChangeListener(EntityChangeListener<Book> changeListener) {
		this.changeListener = changeListener;
	}
}
