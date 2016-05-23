package com.sgnpj.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.sgnpj.model.Audiencia;
import com.sgnpj.model.StatusAudiencia;
import com.sgnpj.repository.Audiencias;
import com.sgnpj.repository.filter.AudienciaFilter;

@Named
@ViewScoped
public class PesquisarAudienciaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Audiencias audienciasRepository;

	@Inject
	private AudienciaFilter filtro;

	private List<Audiencia> audiencias;

	public PesquisarAudienciaBean() {

		this.audiencias = new ArrayList<Audiencia>();
	}
	
	public void pesquisar() {
		System.out.println(this.filtro.getDataAudienciaFinal());
		
		this.audiencias = audienciasRepository.filtrados(this.filtro);
	}

	// Lista de enums do tipo de audiência.
	public StatusAudiencia[] getStatusAudiencia() {
		return StatusAudiencia.values();
	}

	public AudienciaFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(AudienciaFilter filtro) {
		this.filtro = filtro;
	}

	public List<Audiencia> getAudiencias() {
		return audiencias;
	}

	public void setAudiencias(List<Audiencia> audiencias) {
		this.audiencias = audiencias;
	}

}
