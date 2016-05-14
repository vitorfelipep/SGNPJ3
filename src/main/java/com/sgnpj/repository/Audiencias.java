package com.sgnpj.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.sgnpj.model.Audiencia;

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

}
