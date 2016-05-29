package com.sgnpj.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.sgnpj.model.Estado;
import com.sgnpj.repository.EstadoRepository;

@Named
@ViewScoped
public class CarregarEstadosBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EstadoRepository repositorioEstado;

	private List<Estado> estados;

	{
		this.estados = new ArrayList<Estado>();
	}

	@PostConstruct
	public void init() {
		this.estados = repositorioEstado.findAll();
	}

	public CarregarEstadosBean() {

	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

}
