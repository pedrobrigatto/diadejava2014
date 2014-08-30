package br.ufscar.events.diadejava.webapp.flow;

import br.ufscar.events.diadejava.service.exceptions.ServiceLookupException;

/**
 * 
 * @author Pedro Brigatto
 */
public class FlowEngineFactory {
	
	private static final String BASENAME = "%s.impl.%sImpl";
	
	private FlowEngineFactory() {}
	
	/**
	 * 
	 * @return
	 * @throws ServiceLookupException
	 */
	public static FlowEngine getDefaultEngine() throws ServiceLookupException {
		try {
			return (FlowEngine) Class.forName(String.format(BASENAME, FlowEngineFactory.class.getPackage().getName(),
					FlowEngine.class.getSimpleName())).newInstance();
		} catch (ClassNotFoundException|InstantiationException|IllegalAccessException e) {
			throw new ServiceLookupException("Engine could not be started");
		}
	}
}
