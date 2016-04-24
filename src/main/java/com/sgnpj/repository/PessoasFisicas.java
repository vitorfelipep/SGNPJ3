package com.sgnpj.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.sgnpj.model.PessoaFisica;

public class PessoasFisicas implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public PessoaFisica armazenar(PessoaFisica pessoaFisica){
		return manager.merge(pessoaFisica);
	}
	
	public PessoaFisica porId(Long id){
		return manager.find(PessoaFisica.class, id);
	}

}
