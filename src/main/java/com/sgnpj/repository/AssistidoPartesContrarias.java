package com.sgnpj.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

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
}
