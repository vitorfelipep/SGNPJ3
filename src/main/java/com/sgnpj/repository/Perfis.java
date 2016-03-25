package com.sgnpj.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.sgnpj.model.Perfil;

public class Perfis implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	
	public List<Perfil> listarPerfis() {
		return manager.createQuery("from Perfil where descricao not in ('Estagiario') ",
				Perfil.class).getResultList();
	}
	
	
	public Perfil porId(Long id){
		return manager.find(Perfil.class, id);
	}

}
