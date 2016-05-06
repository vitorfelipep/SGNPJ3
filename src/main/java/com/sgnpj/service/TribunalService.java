package com.sgnpj.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.sgnpj.model.Tribunal;
import com.sgnpj.repository.Tribunais;
import com.sgnpj.repository.filter.TribunalFilter;

public class TribunalService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Tribunais tribunais;
	
	public TribunalService() {
		
	}
	
	public List<Tribunal> buscarEstagairio(TribunalFilter filtro){
		return tribunais.filtrados(filtro);
	}


	public Tribunal porIdTribunal(Long id) {
		return tribunais.porId(id);
	}

}
