package br.ufscar.events.diadejava.webapp.view.popups.impl;

import br.ufscar.events.diadejava.model.Book;
import br.ufscar.events.diadejava.service.exceptions.PersistenceException;
import br.ufscar.events.diadejava.webapp.view.AbstractPopupView;
import br.ufscar.events.diadejava.webapp.view.NavigableView.NotificationType;
import br.ufscar.events.diadejava.webapp.view.util.ComponentFactory;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * Default implementation of the pop-up view used to edit either a brand new or
 * an existing book in the application. 
 * 
 * @author Pedro Brigatto
 */
public class BookEditViewImpl extends AbstractPopupView<Book> {

	private static final long serialVersionUID = 8324602577966666807L;
	private static final String REGEX_INTEGER = "^[0-9]*$";
	
	public static final String ID = "editbook";
	
	private static final String ALIAS_MAIN_LAYOUT = "mainlayout";
	private static final String ALIAS_NAME = "name";
	private static final String ALIAS_ISBN = "isbn";
	private static final String ALIAS_PAGES = "pages";
	private static final String ALIAS_DESCRIPTION = "description";
	private static final String ALIAS_CANCEL = "cancel";
	private static final String ALIAS_SAVE = "save";
	
	private VerticalLayout contentLayout;
	private HorizontalLayout actionContainer;
	private TextField name;
	private TextField description;
	private TextField pages;
	private TextField isbn;
	private Button save;
	private Button cancel;
	
	public BookEditViewImpl() {}

	@Override
	public void save(Book entity, boolean updateMode) {
		try {
			viewHandler.save(entity, updateMode);
		} catch (PersistenceException e) {
			notifyUser(String.format("We got problems in the operation, book not %s",
					updateMode ? " updated" : "saved"), NotificationType.ERROR);
		}
	}

	@Override
	public void cancel(Book entity) {
		viewHandler.cancel(entity, isEditMode());
	}

	@Override
	protected void initComponents() {
		name = ComponentFactory.getFactory().createComponent(ID, ALIAS_NAME, TextField.class);
		description = ComponentFactory.getFactory().createComponent(ID, ALIAS_DESCRIPTION, TextField.class);
		isbn = ComponentFactory.getFactory().createComponent(ID, ALIAS_ISBN, TextField.class);
		pages = ComponentFactory.getFactory().createComponent(ID, ALIAS_PAGES, TextField.class);
		save = ComponentFactory.getFactory().createComponent(ID, ALIAS_SAVE, Button.class);
		cancel = ComponentFactory.getFactory().createComponent(ID, ALIAS_CANCEL, Button.class);
		
		name.setCaption("Título: ");
		description.setCaption("Descrição: ");
		isbn.setCaption("ISBN: ");
		pages.setCaption("Número de páginas: ");
		save.setCaption("SALVAR");
		cancel.setCaption("CANCELAR");
		
		name.setWidth(75, Unit.PERCENTAGE);
		description.setWidth(75, Unit.PERCENTAGE);
		isbn.setWidth(75, Unit.PERCENTAGE);
		pages.setWidth(75, Unit.PERCENTAGE);
		
		this.actionContainer = ComponentFactory.getFactory().createContainer(ID, 
				ALIAS_MAIN_LAYOUT, HorizontalLayout.class, save, cancel);
		this.actionContainer.setWidth(40, Unit.PERCENTAGE);
		this.actionContainer.setComponentAlignment(save,  Alignment.MIDDLE_CENTER);
		this.actionContainer.setComponentAlignment(cancel,  Alignment.MIDDLE_CENTER);
		
		this.contentLayout = ComponentFactory.getFactory().createContainer(ID, 
				ALIAS_MAIN_LAYOUT, VerticalLayout.class, name, description, isbn, pages, actionContainer);
		this.contentLayout.setComponentAlignment(name,  Alignment.MIDDLE_CENTER);
		this.contentLayout.setComponentAlignment(description,  Alignment.MIDDLE_CENTER);
		this.contentLayout.setComponentAlignment(isbn,  Alignment.MIDDLE_CENTER);
		this.contentLayout.setComponentAlignment(pages,  Alignment.MIDDLE_CENTER);
		this.contentLayout.setComponentAlignment(actionContainer,  Alignment.MIDDLE_CENTER);
	}

	@SuppressWarnings("serial")
	@Override
	protected void configureEventHandling() {
		save.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				save(getFormData(), isEditMode());
			}
		});
		
		cancel.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				cancel(getFormData());
			}
		});
	}

	@Override
	protected void updateLayout() {
		this.mainLayout  = ComponentFactory.getFactory().createContainer(ID, 
				ALIAS_MAIN_LAYOUT, VerticalLayout.class, this.contentLayout);
		((VerticalLayout)this.mainLayout).setComponentAlignment(this.contentLayout, Alignment.MIDDLE_CENTER);
	}
	
	private Book getFormData() {
		Book book = new Book();
		book.setName(name.getValue());
		book.setDescription(description.getValue());
		String inputForPages = pages.getValue();
		
		if (inputForPages != null && !"".equals(inputForPages) && inputForPages.matches(REGEX_INTEGER)) {
			book.setPages(Integer.parseInt(pages.getValue()));
		}
		
		book.setIsbn(isbn.getValue());
		return book;
	}
}
