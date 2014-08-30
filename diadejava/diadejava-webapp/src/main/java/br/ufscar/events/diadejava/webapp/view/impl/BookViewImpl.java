package br.ufscar.events.diadejava.webapp.view.impl;

import java.util.Set;

import br.ufscar.events.diadejava.model.Book;
import br.ufscar.events.diadejava.webapp.presenters.CrudViewHandler;
import br.ufscar.events.diadejava.webapp.util.Constants;
import br.ufscar.events.diadejava.webapp.view.AbstractCrudView;
import br.ufscar.events.diadejava.webapp.view.BookView;
import br.ufscar.events.diadejava.webapp.view.util.ComponentFactory;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * Main view to let users visualize, edit and delete books.
 * 
 * @author Pedro Brigatto
 */
public class BookViewImpl extends AbstractCrudView<Book> implements BookView {
	
	private static final String ALIAS_MAIN_LAYOUT = "mainlayout"; 
	private static final String ALIAS_OUTER_ACTIONS = "outer_actions";
	private static final String ALIAS_ACTIONS = "actions";
	private static final String ALIAS_BOOK_TABLE = "books";
	private static final String ALIAS_LOGO = "logo";
	private static final String ALIAS_SEARCH = "search";
	private static final String ALIAS_ADD = "add";
	private static final String ALIAS_DELETE = "delete";
	
	private static final Integer BOOK_PAGE_LENGTH = 15;

	private static final long serialVersionUID = 7001165813999719843L;
	
	private Image logo;
	private HorizontalLayout outerActionLayout;
	private HorizontalLayout actionLayout;
	private Table bookTable;
	private TextField searchText;
	private Button filterBooks;
	private Button addBook;
	private Button deleteBook;
	
	private BeanItemContainer<Book> bookContainer;

	public BookViewImpl() {}
	
	public BookViewImpl(CrudViewHandler<Book> viewHandler) {
		this.viewHandler = viewHandler;
	}

	@Override
	protected void initComponents() {
		mainLayout = ComponentFactory.getFactory().createComponent(ID, ALIAS_MAIN_LAYOUT, VerticalLayout.class);
		bookTable = ComponentFactory.getFactory().createComponent(ID, ALIAS_BOOK_TABLE, Table.class);
		searchText = ComponentFactory.getFactory().createComponent(ID, ALIAS_SEARCH, TextField.class);
		filterBooks = ComponentFactory.getFactory().createComponent(ID, ALIAS_SEARCH, Button.class);
		addBook = ComponentFactory.getFactory().createComponent(ID, ALIAS_ADD, Button.class);
		deleteBook = ComponentFactory.getFactory().createComponent(ID, ALIAS_DELETE, Button.class);
		logo = ComponentFactory.getFactory().createComponent(ID, ALIAS_LOGO, Image.class);
		logo.setIcon(new ThemeResource("img/diadejava_logo.jpg"));
		initActionElements();
		configureTable();
	}
	
	private void initActionElements() {
		addBook.setWidth(40f, Unit.PIXELS);
		deleteBook.setWidth(40f, Unit.PIXELS);
		addBook.setCaption("+");
		deleteBook.setCaption("-");
		filterBooks.setCaption("Filter");
		filterBooks.setWidth(100, Unit.PIXELS);
		searchText.setInputPrompt("Digite parte do título do livro ... ");
		searchText.setWidth(220, Unit.PIXELS);
		
		actionLayout = ComponentFactory.getFactory().createContainer(
				ID, ALIAS_ACTIONS, HorizontalLayout.class, addBook, deleteBook, searchText, filterBooks);
		actionLayout.setSpacing(true);
		outerActionLayout = ComponentFactory.getFactory().createContainer(
				ID, ALIAS_OUTER_ACTIONS, HorizontalLayout.class, actionLayout);
		outerActionLayout.setWidth(100, Unit.PERCENTAGE);
		outerActionLayout.setComponentAlignment(actionLayout, Alignment.MIDDLE_RIGHT);
	}
	
	private void configureTable() {
		bookContainer = new BeanItemContainer<Book>(Book.class);
		bookTable.setContainerDataSource(bookContainer);
		bookTable.setVisibleColumns("name", "isbn", "category");
		bookTable.setColumnHeaders("Título", "ISBN", "Categoria");
		bookTable.setImmediate(true);
		bookTable.setSizeFull();
		bookTable.setSelectable(true);
		bookTable.setMultiSelect(false);
		bookTable.setSizeFull();
		bookTable.setPageLength(BOOK_PAGE_LENGTH);
	}

	@SuppressWarnings("serial")
	@Override
	protected void configureEventHandling() {
		addBook.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				create();
			}
		});
		
		deleteBook.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				delete();
			}
		});
		
		filterBooks.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				loadData();
			}
		});
		
		bookTable.addItemClickListener(new ItemClickListener() {
			@Override
			public void itemClick(ItemClickEvent event) {
				update();
			}
		});
	}

	@Override
	protected void updateLayout() {
		mainLayout.addComponent(logo);
		((VerticalLayout)mainLayout).setComponentAlignment(logo, Alignment.MIDDLE_CENTER);
		mainLayout.addComponent(outerActionLayout);
		mainLayout.addComponent(bookTable);
		((VerticalLayout)mainLayout).setSpacing(true);
		((VerticalLayout)mainLayout).setMargin(new MarginInfo(true, true, true, true));
	}

	@Override
	public void reloadData(Set<Book> elements) {

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
	public void enter(ViewChangeEvent event) {}

	@Override
	public NavigationListener getNavigationListener() {
		return this.navigationListener;
	}

	@SuppressWarnings("unchecked") @Override
	public void create() {
		getViewHandler().onCreate(new Book());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update() {
		getViewHandler().onUpdate(((BeanItemContainer<Book>)
				BookViewImpl.this.bookTable.getContainerDataSource()).
				getItem(BookViewImpl.this.bookTable.getValue()).getBean());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void delete() {
		if (BookViewImpl.this.bookTable.getValue() != null) {
			getViewHandler().onDelete(((BeanItemContainer<Book>) 
					this.bookTable.getContainerDataSource()).getItem(this.bookTable.getValue()).getBean());
		} else {
			notifyUser("Select a book to delete and try again", NotificationType.INFORMATION);
		}
	}

	@Override
	public void loadData() {
		getViewHandler().onLoadData(String.format(
				Constants.BASE_FILTER, Constants.FILTER_NAME, searchText.getValue()));
	}
}
