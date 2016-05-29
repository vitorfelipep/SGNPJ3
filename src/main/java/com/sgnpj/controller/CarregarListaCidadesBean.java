package com.sgnpj.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.sgnpj.model.Cidade;
import com.sgnpj.model.Estado;
import com.sgnpj.repository.RepositoryCidades;

@Named
@ViewScoped
public class CarregarListaCidadesBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private RepositoryCidades repositoryCidades;

	private List<Cidade> cidades;

	public CarregarListaCidadesBean() {
		this.cidades = new ArrayList<Cidade>();
	}
	
//	public List<Cidade> carregarCidades(){
//		return this.cidades = repositoryCidades.filtradoPorEstado(this.ass estado);
//	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

}
