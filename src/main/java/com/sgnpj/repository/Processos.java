package com.sgnpj.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.sgnpj.model.Processo;
import com.sgnpj.repository.filter.ProcessoFilter;

public class Processos implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Processo armazenar(Processo processo){
		return manager.merge(processo);
	}
	
	public Processo porId(Long id){
		return manager.find(Processo.class, id);
	}
 
	public Processo porIdAtendimento(Long id) {
		return manager.createQuery("from Processo as pro"
				+ "	left join Atendimento as at  where at.id = :id  ", Processo.class)
				.setParameter("id", id)
				.getSingleResult();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Processo> findAll() {
		Session session = manager.unwrap(Session.class);
		
		Criteria criteria = session.createCriteria(Processo.class);
		
		return criteria.addOrder(Order.asc("dataAbertura")).list(); 
	}

	@SuppressWarnings("unchecked")
	public List<Processo> filtrados(ProcessoFilter filtro) {
		
		Session session = manager.unwrap(Session.class);
		
		Criteria criteria = session.createCriteria(Processo.class)
				//INNER JOIN COM 
				.createAlias("atendimento", "at")
				
				.createAlias("vara", "va");
		
		if (filtro.getDataAberturaInicial() != null) {
			criteria.add(Restrictions.ge("dataAbertura", filtro.getDataAberturaInicial()));
		}
		
		if (filtro.getDataAberturaFinal() != null) {
			criteria.add(Restrictions.le("dataAbertura", filtro.getDataAberturaFinal()));
		}
		
		if(filtro.getAtendimento() != null){
			if(StringUtils.isNotBlank(filtro.getAtendimento().getAssistido().getNome())){
				criteria.add(Restrictions.eq("at.id", filtro.getAtendimento().getId()));
			}
		}
		
		if(filtro.getNumeroProcesso() != null){
			criteria.add(Restrictions.eq("numeroProcesso", filtro.getNumeroProcesso()));
		}
		
		
		if (filtro.getSituacao() != null && filtro.getSituacao().length > 0) {
			// adicionamos uma restrição "in", passando um array de constantes da enum StatusPedido
			criteria.add(Restrictions.in("situacao", filtro.getSituacao()));
		}
		
		return criteria.addOrder(Order.asc("dataAbertura")).list(); 
	}


	@SuppressWarnings("unchecked")
	public List<Processo> porNumeroProcesso(Integer numeroProcesso) {
		Session session = manager.unwrap(Session.class);
		
		Criteria criteria = session.createCriteria(Processo.class)
				.createAlias("atendimento", "at");
		
		if(numeroProcesso != 0){
			criteria.add(Restrictions.eq("numeroProcesso", numeroProcesso));
		}
		 
		return criteria.addOrder(Order.asc("dataAbertura")).list();
	}
	
	

}
