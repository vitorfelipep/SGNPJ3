package com.sgnpj.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.sgnpj.model.Audiencia;
import com.sgnpj.model.Processo;
import com.sgnpj.model.StatusAudiencia;
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
	@EdicaoAudiencia
	private Audiencia audiencia;

	private List<Audiencia> audienciasCadastradas;

	public CadastrarAudienciaBean() {
		this.audiencia = new Audiencia();
		this.audienciasCadastradas = new ArrayList<Audiencia>();

	}

	public void inicializar() {
		if (FacesUtil.isNotPostBack()) {

		}
	}

	@PostConstruct
	public void init() {
		this.audienciasCadastradas = audiencias.findAll();
	}

	public void salvar() {

		if (this.audiencia.getId() == null) {
			try {
				this.audiencia = audienciaService.salvar(audiencia);
				if (this.audiencia.getId() != null) {
					this.audienciasCadastradas = audiencias.findAll();
				}
			} finally {
				// limparFormCadastroAudiencia();
				FacesUtil.addInfoMesage("Audiencia cadastrada com sucesso!");
			}
		} else {
			try {
				this.audiencia = audienciaService.salvar(audiencia);
				this.audienciasCadastradas = audiencias.findAll();
			} finally {
				limparFormCadastroAudiencia();
				FacesUtil.addInfoMesage("Audiencia alterada com sucesso!");
			}
		}
	}

	public void limparFormCadastroAudiencia() {
		this.audiencia = new Audiencia();
	}

	// Atualizar a comarca quando ela for alterado
	public void audienciaAlterada(@Observes AudienciaAlteradoEvent event) {
		this.audiencia = event.getAudiencia();
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

	// Lista de enums do tipo de audiência.
	public StatusAudiencia[] getStatusAudiencia() {
		return StatusAudiencia.values();
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

	public boolean isEditando() {
		return this.audiencia.getId() != null;
	}

}
