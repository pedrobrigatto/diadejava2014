package br.ufscar.events.diadejava.model;

/**
 * Represents a book in the system.
 * 
 * @author Pedro Brigatto
 */
public class Book extends DomainObject {
	
	private static final long serialVersionUID = -5913209354929127013L;
	private String isbn;
	private Category category;
	private int pages;
	
	public Book() {}

	public Book(String name) {
		super(name);
	}
	
	public Book(String name, String description) {
		super(name, description);
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}
}
