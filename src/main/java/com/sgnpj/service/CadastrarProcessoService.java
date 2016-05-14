package com.sgnpj.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.sgnpj.model.Processo;
import com.sgnpj.repository.Processos;
import com.sgnpj.util.jpa.Transactional;

public class CadastrarProcessoService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Processos processos;
	
	@Transactional
	public Processo salvar(Processo processo) {
		Processo processoExistente = new Processo();
		
		if(processoExistente.getId() != null){
			processoExistente =  processos.porId(processoExistente.getId());
		}
		
		if(processoExistente.getId() != null && !processoExistente.equals(processo)){
			throw new AdvogadoCadastroException("Já existe um processo com este id.");
		}
		
		return processos.armazenar(processo);
	}

	public Processo porId(Long id) {
		return processos.porIdAtendimento(id); 
	}

}
