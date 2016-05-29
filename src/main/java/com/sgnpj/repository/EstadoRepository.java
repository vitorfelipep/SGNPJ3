package com.sgnpj.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.sgnpj.model.Estado;

public class EstadoRepository implements Serializable{

	private static final long serialVersionUID = 1L;
	

	@Inject
	private EntityManager manager;
	
	@SuppressWarnings("unchecked")
	public List<Estado> findAll() {
		Session session = manager.unwrap(Session.class);
		
		Criteria criteria = session.createCriteria(Estado.class);
		
		return criteria.addOrder(Order.asc("nome")).list();
	}

	public Estado porId(Integer id) {
		return manager.find(Estado.class, id);
	}

}
