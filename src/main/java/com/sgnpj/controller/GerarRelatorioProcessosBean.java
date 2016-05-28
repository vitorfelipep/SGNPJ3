package com.sgnpj.controller;

/**
 * @author Vitor Felipe
 * @since Version 1.0.10 SNAPSHOT 
 * 
 * Classe reposnsavel por gerar relatório dos processos criados no sistema.
 */

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.sgnpj.model.Processo;
import com.sgnpj.model.StatusProcesso;
import com.sgnpj.repository.Processos;
import com.sgnpj.repository.filter.ProcessoFilter;
import com.sgnpj.util.jsf.FacesUtil;
import com.sgnpj.util.report.ExecutorRelatorio;

@Named
@ViewScoped
public class GerarRelatorioProcessosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Processos processosRepository;

	@Inject
	private ProcessoFilter filtro;
	
	@Inject
	private EntityManager manager;
	
	// Injeção para emitir relatórios do jasper Reports
	@Inject
	private FacesContext facesContext;

	@Inject
	private HttpServletResponse response;


	private List<Processo> processos;

	public GerarRelatorioProcessosBean() {

	}

	public void pesquisar() {
		this.processos = processosRepository.filtrados(filtro);
	}
	
	public void gerarRelatorioPorProcesso(String id) {
		if(org.apache.commons.lang3.StringUtils.isNotEmpty(id)){
			Long idProcesso = new Long(id);
			
			Session session = manager.unwrap(Session.class);
			Map<String, Object> parametros = new HashMap<>();
			
			parametros.put("IdProcesso", idProcesso);
			
			ExecutorRelatorio executor = new ExecutorRelatorio(
					"/relatorios/RelatorioProcesso.jasper", this.response,
					parametros, "Processo_detalhado.pdf");

			session.doWork(executor);
			
			if(executor.isRelatorioGerado()){
				facesContext.responseComplete();			
			}else{
				FacesUtil.addErrorMesage("A execução do relatório não retornou dados.");
			}
			
		}
	}
	
	public void gerarRelatorioProcessoGeral() {

			Session session = manager.unwrap(Session.class);
			Map<String, Object> parametros = new HashMap<>();
			
			parametros.put("dataInicial", filtro.getDataAberturaInicial());
			parametros.put("dataFinal", filtro.getDataAberturaFinal());
			
			ExecutorRelatorio executor = new ExecutorRelatorio(
					"/relatorios/RelatorioProcessoGeral.jasper", this.response,
					parametros, "Processos.pdf");

			session.doWork(executor);
			
			if(executor.isRelatorioGerado()){
				facesContext.responseComplete();			
			}else{
				FacesUtil.addErrorMesage("A execução do relatório não retornou dados.");
			}
			
		
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
