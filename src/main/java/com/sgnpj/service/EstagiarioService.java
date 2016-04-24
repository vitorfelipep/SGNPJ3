package com.sgnpj.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.sgnpj.model.Estagiario;
import com.sgnpj.repository.Estagiarios;
import com.sgnpj.repository.filter.EstagiarioFiltro;
import com.sgnpj.util.jpa.Transactional;

public class EstagiarioService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Estagiarios estagiarios;
	
	@Transactional
	public Estagiario salvar(Estagiario estagiario){
		
		Estagiario estagiarioExistente = new Estagiario();
		
		if(estagiario.getId() != null){
			estagiarioExistente =  estagiarios.porId(estagiario.getId());
		}
		
		if(estagiarioExistente.getMatricula() != null && !estagiarioExistente.equals(estagiario)){
			throw new EstagiarioCadastroException("Já existe um estagiario com este id.");
		}
		
		return estagiarios.armazenar(estagiario);
	}
	
	
	public List<Estagiario> buscarEstagairio(EstagiarioFiltro filtro){
		return estagiarios.filtrados(filtro);
	}


	public Estagiario porIdEstagiario(Long id) {
		return estagiarios.porId(id);
	}

}
