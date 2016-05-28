package com.sgnpj.controller;

/**
 * @author Vitor Felipe
 * @since version 1.0
 * 
 * Manager Bean focado na criação dos relatorios de hipossuficiencia.
 * 
 */

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

import com.sgnpj.model.Assistido;
import com.sgnpj.model.SituacaoAssitido;
import com.sgnpj.repository.Assistidos;
import com.sgnpj.repository.filter.FiltroHipossuficiencia;
import com.sgnpj.util.jsf.FacesUtil;
import com.sgnpj.util.report.ExecutorRelatorio;

@Named
@ViewScoped
public class GerarRelatorioHipossuficienciaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Assistidos repositoryAssistidos;

	@Inject
	private FiltroHipossuficiencia filtro;
	
	@Inject
	private EntityManager manager;
	
	// Injeção para emitir relatórios do jasper Reports
	@Inject
	private FacesContext facesContext;

	@Inject
	private HttpServletResponse response;

	private List<Assistido> assistidos;

	public GerarRelatorioHipossuficienciaBean() {
		this.assistidos = new ArrayList<Assistido>();
	}
	
	public void pesquisar() {
		this.assistidos = repositoryAssistidos.filtradosHipossuficiecia(filtro);
	}
	
	public void gerarRelatorioHipossuficiencia(String id){
		if(org.apache.commons.lang3.StringUtils.isNotEmpty(id)){
			Long idAssistido = new Long(id);
			
			Session session = manager.unwrap(Session.class);
			Map<String, Object> parametros = new HashMap<>();
			
			parametros.put("idAssistido", idAssistido);
			
			ExecutorRelatorio executor = new ExecutorRelatorio(
					"/relatorios/RelatorioHipossuficiencia.jasper", this.response,
					parametros, "Relatorio_Hipossuficiencia.pdf");

			session.doWork(executor);
			
			if(executor.isRelatorioGerado()){
				facesContext.responseComplete();			
			}else{
				FacesUtil.addErrorMesage("A execução do relatório não retornou dados.");
			}
			
		}
	}
	
	// Lista de enums do tipo area de atuação
	public SituacaoAssitido[] getSituacaoAssistido() {
		return SituacaoAssitido.values();
	}	
	
	/* Getters And Setters */
	public Assistidos getRepositoryAssistidos() {
		return repositoryAssistidos;
	}

	public void setRepositoryAssistidos(Assistidos repositoryAssistidos) {
		this.repositoryAssistidos = repositoryAssistidos;
	}

	public FiltroHipossuficiencia getFiltro() {
		return filtro;
	}

	public void setFiltro(FiltroHipossuficiencia filtro) {
		this.filtro = filtro;
	}

	public List<Assistido> getAssistidos() {
		return assistidos;
	}

	public void setAssistidos(List<Assistido> assistidos) {
		this.assistidos = assistidos;
	}

}
