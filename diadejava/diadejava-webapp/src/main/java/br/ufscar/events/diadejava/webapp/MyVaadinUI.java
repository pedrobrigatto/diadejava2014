package br.ufscar.events.diadejava.webapp;

import javax.servlet.annotation.WebServlet;

import br.ufscar.events.diadejava.service.exceptions.ServiceLookupException;
import br.ufscar.events.diadejava.webapp.flow.FlowEngine;
import br.ufscar.events.diadejava.webapp.flow.FlowEngineFactory;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

@Title("É Dia De Java 2014")
@Theme("diadejava")
@SuppressWarnings("serial")
/**
 * This will be the single entry point in the application, where all the requests
 * will arrive and be handled. 
 * 
 * @author Pedro Brigatto
 */
public class MyVaadinUI extends UI {

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class, widgetset = "br.ufscar.events.diadejava.webapp.AppWidgetSet")
    public static class Servlet extends VaadinServlet {}

    @Override
    protected void init(VaadinRequest request) {
    	try {
			FlowEngine engine = FlowEngineFactory.getDefaultEngine();
			engine.initApplication();
		} catch (ServiceLookupException e) {
			e.printStackTrace();
		}
    }
}
