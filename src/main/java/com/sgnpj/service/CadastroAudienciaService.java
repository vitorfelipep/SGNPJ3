package com.sgnpj.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.sgnpj.model.Audiencia;
import com.sgnpj.repository.Audiencias;
import com.sgnpj.util.jpa.Transactional;

public class CadastroAudienciaService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Audiencias audiencias;
	
	@Transactional
	public Audiencia salvar(Audiencia audiencia) {
		Audiencia audienciaExistente = new Audiencia();
		
		if(audienciaExistente.getId() != null){
			audienciaExistente =  audiencias.porId(audienciaExistente.getId());
		}
		
		if(audienciaExistente.getId() != null && !audienciaExistente.equals(audiencia)){
			throw new AdvogadoCadastroException("Já existe uma audiencia com este id.");
		}
		
		return audiencias.armazenar(audiencia);
	}

}
