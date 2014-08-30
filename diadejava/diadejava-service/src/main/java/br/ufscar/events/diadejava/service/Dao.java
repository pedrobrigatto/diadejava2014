package br.ufscar.events.diadejava.service;

import java.util.Set;

import br.ufscar.events.diadejava.model.DomainObject;
import br.ufscar.events.diadejava.service.exceptions.PersistenceException;
import br.ufscar.events.diadejava.service.exceptions.RecordNotFoundException;

/**
 * Represents the objects responsible for the manipulation of
 * records in the data store.
 * 
 * @author Pedro Brigatto
 * @param <T> The type of the entity being manipulated by the DAO
 */
public interface Dao<T extends DomainObject> {
	
	/**
	 * Saves a brand new record in the data store.
	 * 
	 * @param entity
	 * @return
	 * @throws PersistenceException if the object cannot be persisted for some reason
	 */
	T save(T entity) throws PersistenceException;
	
	/**
	 * Updates an existing entity in the data store.
	 * 
	 * @param entity Object containing the fresh data to be updated in the data store.
	 * @return The entity already updated in the data store. 
	 * @throws PersistenceException if the object cannot be persisted for some reason
	 */
	T update(T entity) throws PersistenceException;
	
	/**
	 * Removes the given entity from the data store.
	 * 
	 * @param entity Entity to be removed
	 * @return The removed entity if it could be successfully be removed from the data store
	 * @throws PersistenceException if the object cannot be deleted for some reason
	 */
	T delete(T entity) throws PersistenceException;
	
	/**
	 * Looks for a record in the data store based on a template with some fields that
	 * must match to the ones of the desired element.
	 * 
	 * @param template Object containing the sensible data of the desired element
	 * @return The element matching the data provided through the template
	 * @throws RecordNotFoundException If no entity matches the template data
	 */
	T findByTemplate(T template) throws RecordNotFoundException;
	
	/**
	 * Lists all the elements available in the data store, related to the entity 
	 * type of interest.
	 * 
	 * @return The whole list of entities of the given type available in the data store.
	 */
	Set<T> listAll();
	
	/**
	 * Lists all the entities in the data store, of the given type, matching the
	 * collection of criterias provided to the search.
	 * 
	 * @param criteria The criteria used to filter data in the store.
	 * @return The collection of all records matching the criteria provided
	 */
	Set<T> list(String ... criteria);
}
