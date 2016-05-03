package com.sgnpj.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.sgnpj.model.Atendimento;
import com.sgnpj.repository.Atendimentos;
import com.sgnpj.repository.filter.AtendimentoFilter;

public class PesquisaAtendimentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Atendimentos atendimentos;

	private AtendimentoFilter atendimentoFilter;

	private List<Atendimento> atendimentoFiltrados;

	public PesquisaAtendimentoBean() {
		this.atendimentoFilter = new AtendimentoFilter();
		this.atendimentoFiltrados = new ArrayList<Atendimento>();
	}
	
	
	public void pesquisar(){
		this.atendimentoFiltrados = atendimentos.filtrados(atendimentoFilter);
	}

	public Atendimentos getAtendimentos() {
		return atendimentos;
	}

	public void setAtendimentos(Atendimentos atendimentos) {
		this.atendimentos = atendimentos;
	}

	public AtendimentoFilter getAtendimentoFilter() {
		return atendimentoFilter;
	}

	public void setAtendimentoFilter(AtendimentoFilter atendimentoFilter) {
		this.atendimentoFilter = atendimentoFilter;
	}

	public List<Atendimento> getAtendimentoFiltrados() {
		return atendimentoFiltrados;
	}

	public void setAtendimentoFiltrados(List<Atendimento> atendimentoFiltrados) {
		this.atendimentoFiltrados = atendimentoFiltrados;
	}

}
