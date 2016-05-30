package com.sgnpj.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.sgnpj.model.Cidade;

public class RepositoryCidades implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	@SuppressWarnings("unchecked")
	public List<Cidade> findAll() {
		Session session = manager.unwrap(Session.class);
		
		Criteria criteria = session.createCriteria(Cidade.class);
		
		return criteria.addOrder(Order.asc("nome")).list();
	}

	public Cidade porId(Integer id) {
		return manager.find(Cidade.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Cidade> filtradoPorEstado(String estado) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Cidade.class)
				//INNER JOIN COM A TABELA USUÁRIO
				.createAlias("estado", "e");
		
		if(estado != null){
			criteria.add(Restrictions.eq("e.nome", estado));
		}
		
		return criteria.addOrder(Order.asc("nome")).list(); 
	}
	
	@SuppressWarnings("unchecked")
	public List<Cidade> filtradoPorUf(String uf) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Cidade.class)
				//INNER JOIN COM A TABELA USUÁRIO
				.createAlias("estado", "e");
		
		if(uf != null){
			criteria.add(Restrictions.eq("e.uf", uf));
		}
		
		return criteria.addOrder(Order.asc("nome")).list(); 
	}

}
