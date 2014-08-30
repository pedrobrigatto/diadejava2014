package br.ufscar.events.diadejava.service.factory;

import br.ufscar.events.diadejava.model.DomainObject;
import br.ufscar.events.diadejava.service.Dao;
import br.ufscar.events.diadejava.service.exceptions.ServiceLookupException;

/**
 * Produces objects that are responsible for interacting with the data store, 
 * executing the services related to data manipulation.
 * 
 * @author Pedro Brigatto
 */
public final class DaoFactory {

	private static final String BASENAME = "br.ufscar.events.diadejava.service.impl.%sDao";
	public static DaoFactory factory;
	
	private DaoFactory() {}
	
	@SuppressWarnings("unchecked")
	public static <T extends DomainObject> Dao<T> getDao(Class<T> entityType) 
			throws ServiceLookupException {
		try {
			return (Dao<T>) Class.forName(String.format(
					BASENAME,entityType.getSimpleName())).newInstance();
		} catch (InstantiationException|IllegalAccessException|ClassNotFoundException e) {
			throw new ServiceLookupException("Service unavailable in the moment");
		}
	}
}
