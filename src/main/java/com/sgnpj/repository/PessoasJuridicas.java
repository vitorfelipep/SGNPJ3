package com.sgnpj.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.sgnpj.model.PessoaJuridica;

public class PessoasJuridicas implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public PessoaJuridica armazenar(PessoaJuridica pessoaJuridica){
		return manager.merge(pessoaJuridica);
	}
	
	public PessoaJuridica porId(Long id){
		return manager.find(PessoaJuridica.class, id);
	}

}
