package br.ufscar.events.diadejava.webapp.controller;

import br.ufscar.events.diadejava.service.exceptions.ServiceLookupException;

/**
 * 
 * @author Pedro Brigatto
 */
public class AppControllerFactory {
	
	private static final String BASENAME = "%s.impl.%sImpl";
	
	private static AppController controller;
	
	private AppControllerFactory() {}
	
	/**
	 * 
	 * @return
	 * @throws ServiceLookupException
	 */
	public static AppController getDefaultController() throws ServiceLookupException {
		if (controller == null) {
			try {
				controller = (AppController) Class.forName(String.format(BASENAME, AppControllerFactory.class.getPackage().getName(),
						AppController.class.getSimpleName())).newInstance();
			} catch (ClassNotFoundException|InstantiationException|IllegalAccessException e) {
				throw new ServiceLookupException("Engine could not be started");
			}
		}
		return controller;
	}
}
