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

import com.sgnpj.model.AssistidoContraParte;

public class AssistidoPartesContrarias implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public AssistidoContraParte armazenar(AssistidoContraParte parteContraria){
		return manager.merge(parteContraria);
	}
	
	public AssistidoContraParte porId(Long id){
		return manager.find(AssistidoContraParte.class, id);
	}

	public AssistidoContraParte porFkAssistido(Long id) {
		// TODO Auto-generated method stub
		return null;
	}	
	
	@SuppressWarnings("unchecked")
	public List<AssistidoContraParte> porNome(String nome) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(AssistidoContraParte.class);
		
		if(StringUtils.isNotBlank(nome)){
			criteria.add(Restrictions.ilike("nome", nome.toUpperCase(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
	}
}
