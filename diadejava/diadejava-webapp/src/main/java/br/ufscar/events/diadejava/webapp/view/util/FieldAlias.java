package br.ufscar.events.diadejava.webapp.view.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Qualifier to identify view components.
 * 
 * @author Pedro Brigatto
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldAlias {
	String DEFAULT_ID = "undefined";
	String name() default DEFAULT_ID;
}
