package br.ufscar.events.diadejava.webapp.view;

/**
 * General contract of a view to be used in full screen mode and expose all the 
 * common CRUD operations over a given type of record.
 * 
 * @author Pedro Brigatto
 */
public interface CrudView<T> extends NavigableView {
	
	/** Starts creating a brand new record to be saved in the system. */
	void create();
	
	/**
	 * Starts updating an existing record of the type covered being covered in 
	 * the given scenario.
	 */
	void update();
	
	/** Deletes a selected record from the system. */
	void delete();
	
	/** Loads data into the view. */
	void loadData();
}
