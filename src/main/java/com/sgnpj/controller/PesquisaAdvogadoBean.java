package com.sgnpj.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.sgnpj.model.Advogado;
import com.sgnpj.repository.Advogados;
import com.sgnpj.repository.filter.AdvogadoFilter;

@Named
@ViewScoped
public class PesquisaAdvogadoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Advogados advogados;

	private AdvogadoFilter filtro;

	private List<Advogado> advogadosFiltrados;

	private Advogado advogadoSelecionado;

	public PesquisaAdvogadoBean() {
		this.filtro = new AdvogadoFilter();
	}
	
	public void pesquisar(){
		this.advogadosFiltrados = advogados.filtrados(filtro);
	}

	public AdvogadoFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(AdvogadoFilter filtro) {
		this.filtro = filtro;
	}

	public List<Advogado> getAdvogadosFiltrados() {
		return advogadosFiltrados;
	}

	public void setAdvogadosFiltrados(List<Advogado> advogadosFiltrados) {
		this.advogadosFiltrados = advogadosFiltrados;
	}

	public Advogado getAdvogadoSelecionado() {
		return advogadoSelecionado;
	}

	public void setAdvogadoSelecionado(Advogado advogadoSelecionado) {
		this.advogadoSelecionado = advogadoSelecionado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
