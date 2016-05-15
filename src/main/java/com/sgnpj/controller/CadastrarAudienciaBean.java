package com.sgnpj.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.sgnpj.model.Audiencia;
import com.sgnpj.model.Processo;
import com.sgnpj.model.TipoAudiencia;
import com.sgnpj.repository.Audiencias;
import com.sgnpj.repository.Processos;
import com.sgnpj.service.CadastroAudienciaService;
import com.sgnpj.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastrarAudienciaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroAudienciaService audienciaService;
	
	@Inject
	private Audiencias audiencias;

	@Inject
	private Processos processos;

	@Produces
	private Audiencia audiencia;

	private List<Audiencia> audienciasCadastradas;

	public CadastrarAudienciaBean() {
		this.audiencia = new Audiencia();
		this.audienciasCadastradas = new ArrayList<Audiencia>();
		
	}
	
	@PostConstruct
	public void init(){
		this.audienciasCadastradas = audiencias.findAll();
	}
	
	public void salvar() {
		try{
			this.audiencia = audienciaService.salvar(audiencia);
			if (this.audiencia.getId() != null) {
				this.audienciasCadastradas = audiencias.findAll();
			}
		}finally{
			limparFormCadastroAudiencia();
			FacesUtil.addInfoMesage("Audiencia cadastrada com sucesso!");
		}
	}
	
	public void limparFormCadastroAudiencia(){
		this.audiencia = new Audiencia();
	}
	
	public List<Processo> completarProcesso(Integer numeroProcesso) {
		List<Processo> processos = this.processos
				.porNumeroProcesso(numeroProcesso);
		return processos;
	}

	// Lista de enums do tipo de audiência.
	public TipoAudiencia[] getTipoAudiencia() {
		return TipoAudiencia.values();
	}

	/* Getters and Setters */
	public Audiencia getAudiencia() {
		return audiencia;
	}

	public void setAudiencia(Audiencia audiencia) {
		this.audiencia = audiencia;
	}

	public List<Audiencia> getAudienciasCadastradas() {
		return audienciasCadastradas;
	}

	public void setAudienciasCadastradas(List<Audiencia> audienciasCadastradas) {
		this.audienciasCadastradas = audienciasCadastradas;
	}

	/* End Getters and Setters */

}
