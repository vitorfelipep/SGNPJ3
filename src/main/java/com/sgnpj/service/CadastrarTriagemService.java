package com.sgnpj.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.sgnpj.model.Triagem;
import com.sgnpj.repository.Triagens;
import com.sgnpj.util.jpa.Transactional;

public class CadastrarTriagemService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Triagens triagens;
	
	@Transactional
	public Triagem salvar(Triagem triagem){
		Triagem triagemExistente = new Triagem();
		
		if(triagemExistente.getId() != null){
			triagemExistente =  triagens.porId(triagem.getId());
		}
		
		if(triagemExistente.getId() != null && !triagemExistente.equals(triagem)){
			throw new AdvogadoCadastroException("Já existe uma triagem com este id.");
		}
		
		return triagens.armazenar(triagem);
	}

}
