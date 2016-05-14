package com.sgnpj.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.sgnpj.model.Audiencia;
import com.sgnpj.model.Processo;
import com.sgnpj.model.TipoAudiencia;
import com.sgnpj.model.TipoPessoa;
import com.sgnpj.repository.Processos;
import com.sgnpj.service.CadastroAudienciaService;

@Named
@ViewScoped
public class CadastrarAudienciaBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroAudienciaService audienciaService;
	
	@Inject
	private Processos processos;
	
	@Produces
	private Audiencia audiencia;
	
	
	
	public CadastrarAudienciaBean() {
		this.audiencia = new Audiencia();
	}

	public void salvar(){
		this.audiencia = audienciaService.salvar(audiencia);
	}
	
	
	public List<Processo> completarProcesso(Integer numeroProcesso){
		List<Processo> processos = this.processos.porNumeroProcesso(numeroProcesso);  
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
	
	/* End Getters and Setters */
	
}
