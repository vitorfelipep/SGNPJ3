package com.sgnpj.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.sgnpj.model.Atendimento;
import com.sgnpj.repository.Atendimentos;
import com.sgnpj.util.jpa.Transactional;

public class CadastrarAtendimentoService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Atendimentos atendimentos;
	
	@Transactional
	public Atendimento salvar(Atendimento atendimento){
		Atendimento atendimentoExistente = new Atendimento();
		
		if(atendimentoExistente.getId() != null){
			atendimentoExistente =  atendimentos.porId(atendimento.getId());
		}
		
		if(atendimentoExistente.getId() != null && !atendimentoExistente.equals(atendimento)){
			throw new AdvogadoCadastroException("Já existe um advogado com este id.");
		}
		
		return atendimentos.armazenar(atendimento);
	}
	
}
