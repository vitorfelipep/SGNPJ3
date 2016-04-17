package com.sgnpj.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.sgnpj.model.Assistido;
import com.sgnpj.repository.Assistidos;
import com.sgnpj.util.jpa.Transactional;

public class CadastrarAssisistidoService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Assistidos assistidos;
	
	@Transactional
	public Assistido salvar(Assistido assistido){
		Assistido assistidoExistente = new Assistido();
		
		if(assistido.getId() != null){
			assistidoExistente =  assistidos.porId(assistido.getId());
		}
		
		if(assistidoExistente.getId() != null && !assistidoExistente.equals(assistido)){
			throw new AdvogadoCadastroException("Já existe um advogado com este id.");
		}
		
		return assistidos.armazenar(assistido);
	}
}
