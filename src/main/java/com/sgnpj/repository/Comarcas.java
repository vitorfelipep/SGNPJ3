package com.sgnpj.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.sgnpj.model.Comarca;

public class Comarcas implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	
	public Comarca armazenar(Comarca comarca){
		return comarca = manager.merge(comarca);
	}
	
	public Comarca porId(Long id){
		return manager.find(Comarca.class, id);
	}


}
