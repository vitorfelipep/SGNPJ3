package com.sgnpj.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.sgnpj.model.Triagem;

public class Triagens implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Triagem armazenar(Triagem triagem){
		return manager.merge(triagem);
	}
	
	public Triagem porId(Long id){
		return manager.find(Triagem.class, id);
	}
}
