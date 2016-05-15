package com.sgnpj.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.sgnpj.model.Audiencia;

public class Audiencias implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Audiencia armazenar(Audiencia audiencia){
		return manager.merge(audiencia);
	}
	
	public Audiencia porId(Long id){
		return manager.find(Audiencia.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Audiencia> findAll() {
		Session session = manager.unwrap(Session.class);
		
		Criteria criteria = session.createCriteria(Audiencia.class);
		
		return criteria.addOrder(Order.asc("dataAudiencia")).list();
	}

}
