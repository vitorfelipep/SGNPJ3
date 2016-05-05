package com.sgnpj.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.sgnpj.model.Comarca;
import com.sgnpj.repository.Comarcas;
import com.sgnpj.util.jpa.Transactional;

public class CadastroComarcaService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Comarcas comarcas;
	
	@Transactional
	public Comarca salvar(Comarca comarca) {
		Comarca comarcaExistente = new Comarca();
		
		if(comarcaExistente.getId() != null){
			comarcaExistente =  comarcas.porId(comarcaExistente.getId());
		}
		
		if(comarcaExistente.getId() != null && !comarcaExistente.equals(comarca)){
			throw new AdvogadoCadastroException("Já existe um tribunal com este id.");
		}
		
		return comarcas.armazenar(comarca);
	}


}
