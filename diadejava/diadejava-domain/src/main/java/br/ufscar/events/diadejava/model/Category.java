package br.ufscar.events.diadejava.model;

/**
 * Categories to be applied to books in the system.
 * 
 * @author Pedro Brigatto
 * 
 * @see Book for more details on what a book represents in the solution
 */
public class Category extends DomainObject {

	private static final long serialVersionUID = 3133759915387678976L;

	public Category(String name) {
		super(name);
	}

	public Category(String name, String description) {
		super(name, description);
	}
}
