package com.sgnpj.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.mapping.Array;

import com.sgnpj.model.Atendimento;
import com.sgnpj.repository.Atendimentos;
import com.sgnpj.repository.filter.ProcuracaoFilter;

@Named
@ViewScoped
public class GerarRelatorioProcuracaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProcuracaoFilter filtro;

	@Inject
	private Atendimentos atendimentos;

	private List<Atendimento> listaAtendimentos;

	public GerarRelatorioProcuracaoBean() {
		this.listaAtendimentos = new ArrayList<Atendimento>();
	}

	public void pesquisar() {
		this.listaAtendimentos = atendimentos.filtradosParaProcuracao(filtro);
	}

	public ProcuracaoFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(ProcuracaoFilter filtro) {
		this.filtro = filtro;
	}

	public Atendimentos getAtendimentos() {
		return atendimentos;
	}

	public void setAtendimentos(Atendimentos atendimentos) {
		this.atendimentos = atendimentos;
	}

	public List<Atendimento> getListaAtendimentos() {
		return listaAtendimentos;
	}

	public void setListaAtendimentos(List<Atendimento> listaAtendimentos) {
		this.listaAtendimentos = listaAtendimentos;
	}

}
