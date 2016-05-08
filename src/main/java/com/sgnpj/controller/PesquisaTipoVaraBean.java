package com.sgnpj.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.sgnpj.model.Comarca;
import com.sgnpj.model.TipoVara;
import com.sgnpj.repository.Comarcas;
import com.sgnpj.repository.Varas;
import com.sgnpj.repository.filter.TipoVaraFilter;

@Named
@ViewScoped
public class PesquisaTipoVaraBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Varas varas;
	
	@Inject
	private Comarcas comarcas;

	@Inject
	private TipoVaraFilter filtro;

	private List<TipoVara> listaVaras;

	public PesquisaTipoVaraBean() {
		this.listaVaras = new ArrayList<TipoVara>();
	}

	public void pesquisar() {
		this.listaVaras = varas.filtrados(filtro);
	}
	
	public List<Comarca> completarComarca(String nome) {
		List<Comarca> comarcas = this.comarcas.porNome(nome);
		return comarcas;
	}
	
	public TipoVaraFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(TipoVaraFilter filtro) {
		this.filtro = filtro;
	}

	public List<TipoVara> getListaVaras() {
		return listaVaras;
	}

	public void setListaVaras(List<TipoVara> listaVaras) {
		this.listaVaras = listaVaras;
	}

}
