package com.sgnpj.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.sgnpj.model.Tribunal;
import com.sgnpj.repository.Tribunais;
import com.sgnpj.util.jpa.Transactional;

public class CadastrarTribunalService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Tribunais tribunais;
	
	@Transactional
	public Tribunal salvar(Tribunal tribunal) {
		Tribunal tribunalExistente = new Tribunal();
		
		if(tribunalExistente.getId() != null){
			tribunalExistente =  tribunais.porId(tribunalExistente.getId());
		}
		
		if(tribunalExistente.getId() != null && !tribunalExistente.equals(tribunal)){
			throw new AdvogadoCadastroException("Já existe um tribunal com este id.");
		}
		
		return tribunais.armazenar(tribunal);
	}

}
