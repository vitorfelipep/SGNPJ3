package com.sgnpj.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.sgnpj.model.Tribunal;
import com.sgnpj.repository.Tribunais;
import com.sgnpj.repository.filter.TribunalFilter;

@Named
@ViewScoped
public class PesquisaTribunalBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Tribunais tribunais;

	@Inject
	private TribunalFilter filtro;

	private List<Tribunal> tribunaisLista;

	public PesquisaTribunalBean() {
		this.tribunaisLista = new ArrayList<Tribunal>();
	}

	public void pesquisar() {
		this.tribunaisLista = tribunais.filtrados(filtro);
	}

	public Tribunais getTribunais() {
		return tribunais;
	}

	public void setTribunais(Tribunais tribunais) {
		this.tribunais = tribunais;
	}

	public TribunalFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(TribunalFilter filtro) {
		this.filtro = filtro;
	}

	public List<Tribunal> getTribunaisLista() {
		return tribunaisLista;
	}

	public void setTribunaisLista(List<Tribunal> tribunaisLista) {
		this.tribunaisLista = tribunaisLista;
	}

}
