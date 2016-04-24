package com.sgnpj.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.sgnpj.model.PessoaJuridica;
import com.sgnpj.repository.PessoasJuridicas;
import com.sgnpj.util.jpa.Transactional;

public class CadastrarPessoaJuridicaService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private PessoasJuridicas pessoasJuridicas;
	
	@Transactional
	public PessoaJuridica salvar(PessoaJuridica pessoaJuridica){
		PessoaJuridica pessoaJuridicaExistente = new PessoaJuridica();
		
		if(pessoaJuridicaExistente.getId() != null){
			pessoaJuridicaExistente =  pessoasJuridicas.porId(pessoaJuridica.getId());
		}
		
		if(pessoaJuridicaExistente.getId() != null && !pessoaJuridicaExistente.equals(pessoaJuridica)){
			throw new AdvogadoCadastroException("Já existe uma pessoa juridica com este id.");
		}
		
		return pessoasJuridicas.armazenar(pessoaJuridica); 
	}
}
