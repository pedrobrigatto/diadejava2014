package br.ufscar.events.diadejava.service.data;

import java.util.HashSet;
import java.util.Set;

import br.ufscar.events.diadejava.model.Book;

/**
 * A cache to provide data to the application in the case there's just a need
 * to test the functionalities related to the UI. If data can be mocked just 
 * to validate the other parts of the solution, use this implementation.
 * 
 * @author Pedro Brigatto
 */
public class DataCache {
	
	private static DataCache cache;
	private Set<Book> books;

	private DataCache() {}
	
	public static DataCache getCache() {
		if (cache == null) {
			cache = new DataCache();
		}
		return cache;
	}

	public Set<Book> getBooks() {
		if (books == null) {
			books = new HashSet<Book>();
		}
		return books;
	}
	
	public boolean addBook(Book book) {
		return getBooks().add(book);
	}
	
	public boolean deleteBook(Book book) {
		return getBooks().remove(book);
	}
	
	public boolean updateBook(Book book) {
		
		for (Book each : getBooks()) {
			if (each.getName().equals(book.getName())) {
				getBooks().remove(each);
				return getBooks().add(book); 
			}
		}
		return false;
	}
}
