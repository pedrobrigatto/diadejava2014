package br.ufscar.events.diadejava.webapp.view.listeners;

import java.io.Serializable;

import br.ufscar.events.diadejava.model.DomainObject;

/**
 * Reports changes performed over a given entity through operations such as an 
 * data update executed in the back-end. 
 *  
 * @author Pedro Brigatto
 */
public interface EntityChangeListener<T extends DomainObject> extends Serializable {
	
	public static enum ChangeType {UPDATE, CREATION, DELETION};
	
	/**
	 * Indicates an entity was changed in some way.
	 * 
	 * @param entity The entity that changed its data
	 * @param changeType How the entity was changed in the back-end
	 */
	void entityChanged(T entity, ChangeType changeType);
}
