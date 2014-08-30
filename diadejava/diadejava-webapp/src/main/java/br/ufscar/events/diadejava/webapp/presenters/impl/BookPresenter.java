package br.ufscar.events.diadejava.webapp.presenters.impl;

import br.ufscar.events.diadejava.model.Book;
import br.ufscar.events.diadejava.service.exceptions.ServiceLookupException;
import br.ufscar.events.diadejava.webapp.flow.FlowEngineFactory;
import br.ufscar.events.diadejava.webapp.presenters.CrudViewHandler;
import br.ufscar.events.diadejava.webapp.view.AbstractCrudView;
import br.ufscar.events.diadejava.webapp.view.CrudView;
import br.ufscar.events.diadejava.webapp.view.NavigableView.NotificationType;
import br.ufscar.events.diadejava.webapp.view.listeners.EntityChangeListener;

/**
 * Handles the operations related to books, happening in a CRUD context.
 * 
 * @author Pedro Brigatto
 */
public class BookPresenter implements CrudViewHandler<Book>, EntityChangeListener<Book> {

	private static final long serialVersionUID = 6210781091673053525L;
	
	private AbstractCrudView<Book> view;

	@Override
	public void onCreate(Book draft) {
		try {
			FlowEngineFactory.getDefaultEngine().startEdition(draft, false, this);
		} catch (ServiceLookupException e) {
			view.notifyUser("The service is unavailable at this moment", NotificationType.ERROR);
		}
	}

	@Override
	public void onUpdate(Book draft) {
		try {
			FlowEngineFactory.getDefaultEngine().startEdition(draft, true, this);
		} catch (ServiceLookupException e) {
			view.notifyUser("The service is unavailable at this moment", NotificationType.ERROR);
		}
	}

	@Override
	public void onDelete(Book draft) {
		view.notifyUser("To be implemented", NotificationType.INFORMATION);
	}

	@Override
	public void onLoadData(String ... criteria) {
		view.notifyUser("To be implemented", NotificationType.INFORMATION);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setView(CrudView<Book> view) {
		this.view = (AbstractCrudView<Book>) view;
		this.view.setViewHandler(this);
	}

	@Override
	public void entityChanged(Book entity, ChangeType changeType) {
		// TODO Reload data on the view.
	}
}
