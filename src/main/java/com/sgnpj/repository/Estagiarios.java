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

import com.sgnpj.model.Estagiario;
import com.sgnpj.repository.filter.EstagiarioFiltro;

public class Estagiarios implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Estagiario armazenar(Estagiario estagiario){
		return estagiario = manager.merge(estagiario);
	}
	
	public Estagiario porId(Long id){
		return manager.find(Estagiario.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Estagiario> filtrados(EstagiarioFiltro filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Estagiario.class)
				//INNER JOIN COM A TABELA USUÁRIO
				.createAlias("usuario", "u");
		
		if(StringUtils.isNotBlank((filtro.getNumOab()))){
			criteria.add(Restrictions.eq("codOab", filtro.getNumOab()));
		}
		
		if(filtro.getMatricula() != null){
			criteria.add(Restrictions.eq("matricula", filtro.getMatricula()));
		}
		
		if(StringUtils.isNotBlank(filtro.getNome())){
			criteria.add(Restrictions.ilike("u.nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		if(filtro.getDataInicial() != null && filtro.getDataFinal() != null){
			criteria.add(Restrictions.between("dataInicio", filtro.getDataInicial(), filtro.getDataFinal()));
		}
		
		return criteria.addOrder(Order.asc("u.nome")).list(); 
	}

}
