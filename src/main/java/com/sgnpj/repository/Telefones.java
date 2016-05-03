package com.sgnpj.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.sgnpj.model.Assistido;
import com.sgnpj.model.Telefone;

public class Telefones implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Telefone armazenar(Telefone telefone){
		return manager.merge(telefone);
	}
	
	public Telefone porId(Long id){
		return manager.find(Telefone.class, id);
	}

	public Telefone porIdAssistido(Assistido assistido) {
		return manager.createQuery("from Telefone tel where tel.assistido.id = :idAssistido ", Telefone.class)
						.setParameter("idAssistido", assistido.getId())
						.getSingleResult();
	}

}
