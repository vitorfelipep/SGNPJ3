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

import com.sgnpj.model.Audiencia;
import com.sgnpj.model.StatusAudiencia;
import com.sgnpj.repository.Audiencias;
import com.sgnpj.repository.filter.AudienciaFilter;
import com.sgnpj.util.jsf.FacesUtil;
import com.sgnpj.util.report.ExecutorRelatorio;

@Named
@ViewScoped
public class PesquisarAudienciaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Audiencias audienciasRepository;

	@Inject
	private AudienciaFilter filtro;
	
	@Inject
	private EntityManager manager;
	
	// Injeção para emitir relatórios do jasper Reports
	@Inject
	private FacesContext facesContext;

	@Inject
	private HttpServletResponse response;
	
	private List<Audiencia> audiencias;
	
	

	public PesquisarAudienciaBean() {

		this.audiencias = new ArrayList<Audiencia>();
	}
	
	public void pesquisar() {
		System.out.println(this.filtro.getDataAudienciaFinal());
		
		this.audiencias = audienciasRepository.filtrados(this.filtro);
	}
	
	public void gerarRelatorioPdf(String id){
		Session session = manager.unwrap(Session.class);
		Map<String, Object> parametros = new HashMap<>();
		
		if(!"".equals(id)){
			Long idAudiencia = new Long(id);
			if(idAudiencia != null){
				parametros.put("idAudiencia", idAudiencia);
		
				ExecutorRelatorio executor = new ExecutorRelatorio(
						"/relatorios/RelatorioAudiencia.jasper", this.response,
						parametros, "Audiencia.pdf");
	
				session.doWork(executor);
				
				if(executor.isRelatorioGerado()){
					facesContext.responseComplete();			
				}else{
					FacesUtil.addErrorMesage("A execução do relatório não retornou dados.");
				}
			}
		}
		
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
