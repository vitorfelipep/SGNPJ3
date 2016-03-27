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

import com.sgnpj.model.Advogado;
import com.sgnpj.repository.filter.AdvogadoFilter;

public class Advogados implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	
	public Advogado armazenar(Advogado advogado){
		return advogado = manager.merge(advogado);
	}
	
	public Advogado porId(Integer id){
		return manager.find(Advogado.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Advogado> filtrados(AdvogadoFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Advogado.class)
				//INNER JOIN COM A TABELA USUÁRIO
				.createAlias("usuario", "u");
		
		if(StringUtils.isNotBlank(filtro.getCodigo_OAB())){
			criteria.add(Restrictions.eq("codigo_OAB", filtro.getCodigo_OAB()));
		}
		
		if(StringUtils.isNotBlank(filtro.getNome())){
			criteria.add(Restrictions.ilike("u.nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("u.nome")).list(); 
	}
}
