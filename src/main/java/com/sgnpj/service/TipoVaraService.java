package com.sgnpj.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.sgnpj.model.TipoVara;
import com.sgnpj.repository.Varas;
import com.sgnpj.util.jpa.Transactional;

public class TipoVaraService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Varas varas;
	
	@Transactional
	public TipoVara salvar(TipoVara vara) {
		TipoVara varaExistente = new TipoVara();
		
		if(varaExistente.getId() != null){
			varaExistente =  varas.porId(vara.getId());
		}
		
		if(varaExistente.getId() != null && !varaExistente.equals(vara)){
			throw new VaraCadastroException("Já existe um tribunal com este id!");
		}
		
		return varas.armazenar(vara);
	}

}
