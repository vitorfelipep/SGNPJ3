package com.sgnpj.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.sgnpj.model.PessoaFisica;
import com.sgnpj.repository.PessoasFisicas;
import com.sgnpj.util.jpa.Transactional;

public class CadastrarPessoaFisicaService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private PessoasFisicas pessoasFisicas;
	
	@Transactional
	public PessoaFisica salvar(PessoaFisica pessoaFisica){
		PessoaFisica pessoaFisicaExistente = new PessoaFisica();
		
		if(pessoaFisicaExistente.getId() != null){
			pessoaFisicaExistente =  pessoasFisicas.porId(pessoaFisica.getId());
		}
		
		if(pessoaFisicaExistente.getId() != null && !pessoaFisicaExistente.equals(pessoaFisica)){
			throw new AdvogadoCadastroException("Já existe um advogado com este id.");
		}
		
		return pessoasFisicas.armazenar(pessoaFisica);
	}

}
