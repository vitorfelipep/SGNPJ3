package com.sgnpj.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.sgnpj.model.Advogado;

public class Advogados implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	
	public Advogado armazenar(Advogado advogado){
		return advogado = manager.merge(advogado);
	}
	
	public Advogado porId(Integer id){
		return manager.find(Advogado.class, id);
	}
}
