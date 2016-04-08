package com.sgnpj.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.sgnpj.model.Estagiario;
import com.sgnpj.repository.filter.EstagiarioFiltro;
import com.sgnpj.service.EstagiarioService;

@Named
@ViewScoped
public class PesquisaEstagiarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EstagiarioService estagiarioService;

	private EstagiarioFiltro filtro;

	private List<Estagiario> estagiariosFiltrados;

	public PesquisaEstagiarioBean() {
		this.filtro = new EstagiarioFiltro();
	}

	public void pesquisar() {
		this.estagiariosFiltrados = estagiarioService.buscarEstagairio(filtro);
	}

	public EstagiarioFiltro getFiltro() {
		return filtro;
	}

	public void setFiltro(EstagiarioFiltro filtro) {
		this.filtro = filtro;
	}

	public List<Estagiario> getEstagiariosFiltrados() {
		return estagiariosFiltrados;
	}

	public void setEstagiariosFiltrados(List<Estagiario> estagiariosFiltrados) {
		this.estagiariosFiltrados = estagiariosFiltrados;
	}

}
