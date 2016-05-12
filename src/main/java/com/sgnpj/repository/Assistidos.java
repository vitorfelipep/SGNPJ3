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

import com.sgnpj.model.Assistido;
import com.sgnpj.repository.filter.AssistidoFilter;

/**
 * 
 * @author Vitor
 *
 */

public class Assistidos implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	
	public Assistido armazenar(Assistido assistido){
		return assistido = manager.merge(assistido);
	}
	
	public Assistido porId(Long id){
		return manager.find(Assistido.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Assistido> filtrados(AssistidoFilter filtro) {
		
		Session session = manager.unwrap(Session.class);
		
		Criteria criteria = session.createCriteria(Assistido.class)
				//INNER JOIN COM 
				.createAlias("pessoaFisica", "pf")
				
				//.createAlias("pessoaJuridica", "pj")
				
				.createAlias("triagem", "tr");
		
//		if(StringUtils.isNotBlank(filtro.getProcesso())){
//			criteria.add(Restrictions.eq("codigo_OAB", filtro.getCodigo_OAB()));
//		}
		
		if(StringUtils.isNotBlank(filtro.getNomeAssistido())){
			criteria.add(Restrictions.ilike("nome", filtro.getNomeAssistido(), MatchMode.ANYWHERE));
		}
		
		if(StringUtils.isNotBlank(filtro.getCpf())){
			criteria.add(Restrictions.ilike("pf.cpf", filtro.getCpf()));
		}
		
//		if(filtro.getSituacaoAssistido() != null){
//			criteria.add(Restrictions.ilike("situacao", filtro.getSituacaoAssistido().getDescricao(), MatchMode.ANYWHERE));
//		}
		
		if(StringUtils.isNotBlank(filtro.getRg())){
			criteria.add(Restrictions.eq("pf.identidade", filtro.getRg()));
		}
		
		return criteria.addOrder(Order.asc("nome")).list(); 
	}

	@SuppressWarnings("unchecked")
	public List<Assistido> porNome(String nome) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Assistido.class);
		
		if(StringUtils.isNotBlank(nome)){
			criteria.add(Restrictions.ilike("nome", nome.toUpperCase(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
	}
}
