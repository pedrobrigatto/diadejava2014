package br.ufscar.events.diadejava.model;

import java.io.Serializable;

/**
 * Generic representation of a domain object in the solution.
 * 
 * @author Pedro Brigatto
 */
public class DomainObject implements Serializable {

	private static final long serialVersionUID = -6662600887063510801L;
	
	private Long id;
	private String name;
	private String description;
	
	public DomainObject(){}
	
	public DomainObject(Long id) {
		this.id = id;
	}
	
	public DomainObject(String name) {
		this.name = name;
	}
	
	public DomainObject(String name, String description) {
		this(name);
		this.description = description;
	}	

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Long getId() {
		return id;
	}
}
