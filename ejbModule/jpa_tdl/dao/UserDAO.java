package jpa_tdl.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jpa_tdl.entities.User;

public class UserDAO {
	@PersistenceContext
	protected EntityManager em;
	
	public void create(User user) {
		em.persist(user);
	}

	public User merge(User user) {
		return em.merge(user);
	}

	public void remove(User user) {
		em.remove(em.merge(user));
	}

	public User find(Object id) {
		return em.find(User.class, id);
	}
}
