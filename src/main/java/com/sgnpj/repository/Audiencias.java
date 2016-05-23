package com.sgnpj.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.sgnpj.model.Audiencia;
import com.sgnpj.repository.filter.AudienciaFilter;

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

	@SuppressWarnings("unchecked")
	public List<Audiencia> filtrados(AudienciaFilter filtro) {
		
		Session session = manager.unwrap(Session.class);
		
		Criteria criteria = session.createCriteria(Audiencia.class)
				//INNER JOIN COM 
				.createAlias("advogadoResponsavel", "ad")
				
				.createAlias("estagiario", "es")
				
				.createAlias("processo", "pr");
		
		if (filtro.getDataAudienciaInicial() != null) {
			criteria.add(Restrictions.ge("dataAudiencia", filtro.getDataAudienciaInicial()));
		}
		
		if (filtro.getDataAudienciaFinal() != null) {
			criteria.add(Restrictions.le("dataAudiencia", filtro.getDataAudienciaFinal()));
		}
		
		if(filtro.getNumeroProcesso() != null){
				criteria.add(Restrictions.eq("pr.numeroProcesso", filtro.getNumeroProcesso()));
		}
		
		if (filtro.getStatusAudiencia() != null && filtro.getStatusAudiencia().length > 0) {
			// adicionamos uma restrição "in", passando um array de constantes da enum StatusPedido
			criteria.add(Restrictions.in("stauAudiencia", filtro.getStatusAudiencia()));
		}
		
		return criteria.addOrder(Order.asc("pr.numeroProcesso")).list(); 
	}

}
