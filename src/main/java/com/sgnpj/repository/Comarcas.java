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

import com.sgnpj.model.Comarca;
import com.sgnpj.repository.filter.ComarcaFilter;

public class Comarcas implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	
	public Comarca armazenar(Comarca comarca){
		return comarca = manager.merge(comarca);
	}
	
	public Comarca porId(Long id){
		return manager.find(Comarca.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Comarca> filtrados(ComarcaFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Comarca.class)
				//INNER JOIN COM A TABELA TRIBUNAL
				.createAlias("tribunal", "t");
		
		if(filtro.getId() != null){
			criteria.add(Restrictions.eq("id", filtro.getId()));
		}
		
		if(StringUtils.isNotBlank(filtro.getNome())){
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		if(filtro.getTribunal() != null){
			criteria.add(Restrictions.ilike("t.nome", filtro.getTribunal().getNome(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("nome")).list(); 
	}


}
