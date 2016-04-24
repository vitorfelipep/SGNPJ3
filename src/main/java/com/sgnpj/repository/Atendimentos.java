package com.sgnpj.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.sgnpj.model.Atendimento;

public class Atendimentos implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Atendimento armazenar(Atendimento atendimento){
		return manager.merge(atendimento);
	}
	
	public Atendimento porId(Long id){
		return manager.find(Atendimento.class, id);
	}

}
