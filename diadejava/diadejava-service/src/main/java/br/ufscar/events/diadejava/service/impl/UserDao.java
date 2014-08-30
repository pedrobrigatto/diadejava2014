package br.ufscar.events.diadejava.service.impl;

import java.util.Set;

import br.ufscar.events.diadejava.model.User;
import br.ufscar.events.diadejava.service.Dao;
import br.ufscar.events.diadejava.service.exceptions.PersistenceException;
import br.ufscar.events.diadejava.service.exceptions.RecordNotFoundException;

public class UserDao implements Dao<User> {

	public UserDao() {}

	public User save(User entity) throws PersistenceException {
		return null;
	}

	public User update(User entity) throws PersistenceException {
		return null;
	}

	public User delete(User entity) throws PersistenceException {
		return null;
	}

	public Set<User> listAll() {
		return null;
	}

	public Set<User> list(String... criteria) {
		return null;
	}

	public User findByTemplate(User template) throws RecordNotFoundException {
		return template;
	}
}
