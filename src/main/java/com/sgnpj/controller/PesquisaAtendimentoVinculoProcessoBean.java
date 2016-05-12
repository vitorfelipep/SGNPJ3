package com.sgnpj.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.sgnpj.model.Atendimento;
import com.sgnpj.model.Processo;
import com.sgnpj.repository.Atendimentos;
import com.sgnpj.repository.filter.AtendimentoFilter;

@Named
@ViewScoped
public class PesquisaAtendimentoVinculoProcessoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Atendimentos atendimentosRepository;

	@Produces
	private Processo processo;

	@Inject
	private AtendimentoFilter atendimentoFilter;

	private List<Atendimento> atendimentos;

	public PesquisaAtendimentoVinculoProcessoBean() {
		this.atendimentos = new ArrayList<Atendimento>();
	}

	public Processo getProcesso() {
		return processo;
	}

	public void pesquisar() {

	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public AtendimentoFilter getAtendimentoFilter() {
		return atendimentoFilter;
	}

	public void setAtendimentoFilter(AtendimentoFilter atendimentoFilter) {
		this.atendimentoFilter = atendimentoFilter;
	}

	public List<Atendimento> getAtendimentos() {
		return atendimentos;
	}

	public void setAtendimentos(List<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}

}
