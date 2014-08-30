package br.ufscar.events.diadejava.webapp.view.util;

import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.AbstractLayout;

/**
 * Factory used to create and configure components used in the pages.
 * 
 * @author Pedro Brigatto
 */
public class ComponentFactory {
	
	private static final String PATTERN_ID = "%s_%s_%s";
	private static ComponentFactory factory;
	
	private ComponentFactory() {}
	
	public static ComponentFactory getFactory() {
		if (factory == null) {
			factory = new  ComponentFactory();
		}
		return factory;
	}
	
	/**
	 * 
	 * @param viewId
	 * @param alias
	 * @param type
	 * @return
	 */
	public <T extends AbstractComponent> T createComponent(
			String viewId, String alias, Class<T> type) {
		AbstractComponent component = null;
		
		try {
			component = (AbstractComponent) type.newInstance();
			configureComponent(component, viewId, alias);
			
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return type.cast(component);
	}
	
	/**
	 * 
	 * @param viewId
	 * @param alias
	 * @param containerType
	 * @param children
	 * @return
	 */
	public <T extends AbstractLayout> T createContainer(String viewId, String alias,
			Class<T> containerType, AbstractComponent ... children) {
		
		AbstractLayout container = null;
		
		try {
			container = (AbstractLayout) containerType.newInstance();
			
			if (children != null && children.length > 0) {
				for (AbstractComponent child : children) {
					container.addComponent(child);
				}
			}
			
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return containerType.cast(container);
	}

	/**
	 * 
	 * @param component
	 * @param viewId
	 * @param alias
	 */
	private void configureComponent(AbstractComponent component, String viewId,
			String alias) {
		component.setId(String.format(PATTERN_ID, viewId, 
				component.getClass().getSimpleName().toLowerCase(), alias));
	}
}
