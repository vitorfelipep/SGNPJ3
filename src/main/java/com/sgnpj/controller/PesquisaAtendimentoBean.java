package com.sgnpj.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.sgnpj.model.Atendimento;
import com.sgnpj.model.StatusAtendimento;
import com.sgnpj.repository.Atendimentos;
import com.sgnpj.repository.filter.AtendimentoFilter;

@Named
@ViewScoped
public class PesquisaAtendimentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Atendimentos atendimentos;
	
	@Inject
	private AtendimentoFilter atendimentoFilter;

	private List<Atendimento> atendimentoFiltrados;

	public PesquisaAtendimentoBean() {
		this.atendimentoFiltrados = new ArrayList<Atendimento>();
	}
	
	//oesquisa de atendimento
	public void pesquisar(){
		this.atendimentoFiltrados = atendimentos.filtrados(atendimentoFilter);
	}
	
	// Lista de enums do tipo area de atuação
	public StatusAtendimento[] getStatusAtendimento() {
		return StatusAtendimento.values();
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
