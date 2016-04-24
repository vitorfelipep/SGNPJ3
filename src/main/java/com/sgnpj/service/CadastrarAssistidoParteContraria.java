package com.sgnpj.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.sgnpj.model.AssistidoContraParte;
import com.sgnpj.repository.AssistidoPartesContrarias;
import com.sgnpj.util.jpa.Transactional;

public class CadastrarAssistidoParteContraria implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private AssistidoPartesContrarias partesContrarias;
	
	@Transactional
	public AssistidoContraParte salvar(AssistidoContraParte parteContraria){
		AssistidoContraParte parteContrariaExistente = new AssistidoContraParte();
		
		if(parteContrariaExistente.getId() != null){
			parteContrariaExistente =  partesContrarias.porId(parteContraria.getId());
		}
		
		if(parteContrariaExistente.getId() != null && !parteContrariaExistente.equals(parteContraria)){
			throw new AdvogadoCadastroException("Já existe um assistido parte contraria (réu ou autor) com este id.");
		}
		
		return partesContrarias.armazenar(parteContraria);
	}

}
