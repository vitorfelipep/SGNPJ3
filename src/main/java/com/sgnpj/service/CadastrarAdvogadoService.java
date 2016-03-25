package com.sgnpj.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.sgnpj.model.Advogado;
import com.sgnpj.repository.Advogados;
import com.sgnpj.util.jpa.Transactional;

public class CadastrarAdvogadoService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Advogados advogados;
	
	@Transactional
	public Advogado salvar(Advogado advogado){
		Advogado advogadoExistente = new Advogado();
		
		if(advogado.getId_advogado() != null){
			 advogadoExistente =  advogados.porId(advogado.getId_advogado());
		}
		
		if(advogadoExistente != null && advogadoExistente.equals(advogado)){
			throw new AdvogadoCadastroException("Já existe um advogado com este id.");
		}
		
		return advogados.armazenar(advogado);
	}

}
