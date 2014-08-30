package br.ufscar.events.diadejava.service.impl;

import java.util.Set;

import br.ufscar.events.diadejava.model.Book;
import br.ufscar.events.diadejava.service.Dao;
import br.ufscar.events.diadejava.service.data.DataCache;
import br.ufscar.events.diadejava.service.exceptions.PersistenceException;
import br.ufscar.events.diadejava.service.exceptions.RecordNotFoundException;

/**
 * Access object responsible for the manipulation of books in the solution.
 * 
 * @author Pedro Brigatto
 * @see Book to understand what the concept of a book stands for in the domain
 */
public class BookDao implements Dao<Book> {

	public BookDao() {}

	public Book save(Book entity) throws PersistenceException {
		if (DataCache.getCache().addBook(entity)) {
			return entity;
		} else {
			throw new PersistenceException("The book could not be saved");
		}	
	}

	public Book update(Book entity) throws PersistenceException {
		if (DataCache.getCache().updateBook(entity)) {
			return entity;
		} else {
			throw new PersistenceException("The book could not be updated");
		}
	}

	public Book delete(Book entity) throws PersistenceException {
		if (DataCache.getCache().deleteBook(entity)) {
			return entity;
		} else {
			throw new PersistenceException("The book could not be deleted");
		}
	}

	public Set<Book> listAll() {
		return DataCache.getCache().getBooks();
	}

	public Set<Book> list(String... criteria) {
		return DataCache.getCache().getBooks();
	}

	public Book findByTemplate(Book template) throws RecordNotFoundException {
		return null;
	}
}
