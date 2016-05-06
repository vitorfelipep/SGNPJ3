package com.sgnpj.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.sgnpj.model.Comarca;
import com.sgnpj.model.Tribunal;
import com.sgnpj.repository.Comarcas;
import com.sgnpj.repository.Tribunais;
import com.sgnpj.repository.filter.ComarcaFilter;

@Named
@ViewScoped
public class PesquisaComarcaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Comarcas comarcas;

	@Inject
	private Tribunais tribunais;

	@Inject
	private ComarcaFilter filtro;

	private List<Comarca> listaComarcas;
	
	
	public PesquisaComarcaBean() {
		this.listaComarcas = new ArrayList<Comarca>();
	}

	public void pesquisar() {
		this.listaComarcas = comarcas.filtrados(filtro);
	}
	
	public List<Tribunal> completarTribunal(String nome) {
		List<Tribunal> tribunais = this.tribunais.porNome(nome);
		return tribunais;
	}
	
	public ComarcaFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(ComarcaFilter filtro) {
		this.filtro = filtro;
	}

	public List<Comarca> getListaComarcas() {
		return listaComarcas;
	}

	public void setListaComarcas(List<Comarca> listaComarcas) {
		this.listaComarcas = listaComarcas;
	}

}
