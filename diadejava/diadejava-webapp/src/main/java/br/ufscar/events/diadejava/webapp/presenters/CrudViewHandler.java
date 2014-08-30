package br.ufscar.events.diadejava.webapp.presenters;

import java.io.Serializable;

import br.ufscar.events.diadejava.model.DomainObject;
import br.ufscar.events.diadejava.webapp.view.CrudView;

/**
 * Handler to views that expose CRUD operations over a given type of entity.
 * 
 * @author Pedro Brigatto
 *
 * @param <T> The type of the entity the handler manipulates in a CRUD context
 */
public interface CrudViewHandler<T extends DomainObject> extends Serializable {
	
	/**
	 * 
	 * @param view
	 */
	void setView(CrudView<T> view);
	
	/**
	 * Called whenever an action to create a brand new record is generated in 
	 * the application
	 * 
	 * @param T Draft that contains the data of the record to be saved
	 */
	void onCreate(T draft);
	
	/**
	 * Called whenever an action to update an existing record is generated in 
	 * the application
	 * 
	 * @param draft
	 */
	void onUpdate(T draft);
	
	/**
	 * Called whenever an action to delete a selected record is generated in 
	 * the application
	 * 
	 * @param draft
	 */
	void onDelete(T draft);
	
	/**
	 * Called whenever an action to load entities from the back-end is generated 
	 * in the application
	 * 
	 * @param criteria A set of fields to compose the criteria to filter data
	 */
	void onLoadData(String ... criteria);
}
