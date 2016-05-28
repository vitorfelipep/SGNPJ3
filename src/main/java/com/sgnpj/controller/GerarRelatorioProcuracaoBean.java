package com.sgnpj.controller;

import java.io.Serializable;
import java.util.ArrayList;
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

import com.sgnpj.model.Atendimento;
import com.sgnpj.repository.Atendimentos;
import com.sgnpj.repository.filter.ProcuracaoFilter;
import com.sgnpj.util.jsf.FacesUtil;
import com.sgnpj.util.report.ExecutorRelatorio;

@Named
@ViewScoped
public class GerarRelatorioProcuracaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProcuracaoFilter filtro;
	
	@Inject
	private EntityManager manager;
	
	// Injeção para emitir relatórios do jasper Reports
	@Inject
	private FacesContext facesContext;

	@Inject
	private HttpServletResponse response;

	@Inject
	private Atendimentos atendimentos;

	private List<Atendimento> listaAtendimentos;

	public GerarRelatorioProcuracaoBean() {
		this.listaAtendimentos = new ArrayList<Atendimento>();
	}
	
	public void pesquisar() {	
		this.listaAtendimentos = atendimentos.filtradosParaProcuracao(filtro);
	}
	
	public void gerarRelatorioProcuracao(String id){
		if(org.apache.commons.lang3.StringUtils.isNotEmpty(id)){
			Long idAssistido = new Long(id);
			
			Session session = manager.unwrap(Session.class);
			Map<String, Object> parametros = new HashMap<>();
			
			parametros.put("idAssistido", idAssistido);
			
			ExecutorRelatorio executor = new ExecutorRelatorio(
					"/relatorios/RelatorioProcuracao.jasper", this.response,
					parametros, "Procuracao.pdf");

			session.doWork(executor);
			
			if(executor.isRelatorioGerado()){
				facesContext.responseComplete();			
			}else{
				FacesUtil.addErrorMesage("A execução do relatório não retornou dados.");
			}
			
		}
	}

	public ProcuracaoFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(ProcuracaoFilter filtro) {
		this.filtro = filtro;
	}

	public Atendimentos getAtendimentos() {
		return atendimentos;
	}

	public void setAtendimentos(Atendimentos atendimentos) {
		this.atendimentos = atendimentos;
	}

	public List<Atendimento> getListaAtendimentos() {
		return listaAtendimentos;
	}

	public void setListaAtendimentos(List<Atendimento> listaAtendimentos) {
		this.listaAtendimentos = listaAtendimentos;
	}

}
