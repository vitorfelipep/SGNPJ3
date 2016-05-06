package com.sgnpj.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.sgnpj.model.Tribunal;
import com.sgnpj.repository.filter.TribunalFilter;

public class Tribunais implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Tribunal armazenar(Tribunal tribunal){
		return manager.merge(tribunal);
	}
	
	public Tribunal porId(Long id){
		return manager.find(Tribunal.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Tribunal> porNome(String nome) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Tribunal.class);
		
		if(StringUtils.isNotBlank(nome)){
			criteria.add(Restrictions.ilike("nome", nome.toUpperCase(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
	}

	@SuppressWarnings("unchecked")
	public List<Tribunal> filtrados(TribunalFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Tribunal.class);
		
		if(filtro.getIdTribunal() != null){
			criteria.add(Restrictions.eq("id", filtro.getIdTribunal()));
		}
		
		if(StringUtils.isNotBlank(filtro.getNome())){
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
				
		return criteria.addOrder(Order.asc("nome")).list(); 
	}

}
