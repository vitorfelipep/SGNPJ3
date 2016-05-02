package com.sgnpj.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.sgnpj.model.Telefone;
import com.sgnpj.repository.Telefones;
import com.sgnpj.util.jpa.Transactional;

public class CadastrarTelefoneService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Telefones telefones;
	
	@Transactional
	public Telefone salvar(Telefone telefone) {
		Telefone telefoneExistente = new Telefone();
		
		if(telefoneExistente.getId_telefone() != null){
			telefoneExistente =  telefones.porId(telefone.getId_telefone());
		}
		
		if(telefoneExistente.getId_telefone() != null && !telefoneExistente.equals(telefone)){
			throw new AdvogadoCadastroException("Já existe uma triagem com este id.");
		}
		
		return telefones.armazenar(telefone);
	}
	
	

}
