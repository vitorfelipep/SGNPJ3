package com.sgnpj.controller;

/**
 * @author Vitor Felipe
 * @since Version 1.0.10 SNAPSHOT 
 * 
 * Classe reposnsavel por gerar relatório dos processos criados no sistema.
 */

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.sgnpj.model.Processo;
import com.sgnpj.model.StatusProcesso;
import com.sgnpj.repository.Processos;
import com.sgnpj.repository.filter.ProcessoFilter;

@Named
@ViewScoped
public class GerarRelatorioProcessosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Processos processosRepository;

	@Inject
	private ProcessoFilter filtro;

	private List<Processo> processos;

	public GerarRelatorioProcessosBean() {

	}

	public void pesquisar() {
		this.processos = processosRepository.filtrados(filtro);
	}
	
	public void gerarRelatorioPorProcesso(String id) {
		 
	}
	
	/**
	 * 
	 * @return StatusProcesso
	 */
	// Lista de enums do tipo status processo
	public StatusProcesso[] getStatusProcesso() {
		return StatusProcesso.values();
	}

	/* Getters and Setters */
	public Processos getProcessosRepository() {
		return processosRepository;
	}

	public void setProcessosRepository(Processos processosRepository) {
		this.processosRepository = processosRepository;
	}

	public ProcessoFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(ProcessoFilter filtro) {
		this.filtro = filtro;
	}

	public List<Processo> getProcessos() {
		return processos;
	}

	public void setProcessos(List<Processo> processos) {
		this.processos = processos;
	}

}
