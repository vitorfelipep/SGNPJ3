package com.sgnpj.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.sgnpj.model.Estagiario;

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

//	@SuppressWarnings("unchecked")
//	public List<Estagiario> filtrados(EstagiarioFilter filtro) {
//		Session session = manager.unwrap(Session.class);
//		Criteria criteria = session.createCriteria(Advogado.class)
//				//INNER JOIN COM A TABELA USUÁRIO
//				.createAlias("usuario", "u");
//		
//		if(StringUtils.isNotBlank(filtro.getCodigo_OAB())){
//			criteria.add(Restrictions.eq("codigo_OAB", filtro.getCodigo_OAB()));
//		}
//		
//		if(StringUtils.isNotBlank(filtro.getNome())){
//			criteria.add(Restrictions.ilike("u.nome", filtro.getNome(), MatchMode.ANYWHERE));
//		}
//		
//		return criteria.addOrder(Order.asc("u.nome")).list(); 
//	}

}
