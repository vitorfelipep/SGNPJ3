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

import com.sgnpj.model.TipoVara;
import com.sgnpj.repository.filter.TipoVaraFilter;

public class Varas implements Serializable{

	private static final long serialVersionUID = 2L;
	
	@Inject
	private EntityManager manager;
	
	public TipoVara armazenar(TipoVara tribunal){
		return manager.merge(tribunal);
	}
	
	public TipoVara porId(Long id){
		return manager.find(TipoVara.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<TipoVara> filtrados(TipoVaraFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(TipoVara.class)
				.createAlias("cormarca", "c");
		
		
		if (filtro.getDataInicial() != null) {
			criteria.add(Restrictions.ge("dataCadastro", filtro.getDataInicial()));
		}
		
		if (filtro.getDataFinal() != null) {
			criteria.add(Restrictions.le("dataCadastro", filtro.getDataFinal()));
		}
		
		if(filtro.getIdTipoVara() != null){
			criteria.add(Restrictions.eq("id", filtro.getIdTipoVara()));
		}
		
		if(StringUtils.isNotBlank(filtro.getNomeVara())){
			criteria.add(Restrictions.ilike("nome", filtro.getNomeVara(), MatchMode.ANYWHERE));
		}
		
		if(filtro.getComarca() != null){
			criteria.add(Restrictions.eq("c.id", filtro.getComarca().getId()));
		}
				
		return criteria.addOrder(Order.asc("nome")).list(); 
	}

	@SuppressWarnings("unchecked")
	public List<TipoVara> porNome(String nome) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(TipoVara.class);
		
		if(StringUtils.isNotBlank(nome)){
			criteria.add(Restrictions.ilike("nome", nome.toUpperCase(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
	}

}
