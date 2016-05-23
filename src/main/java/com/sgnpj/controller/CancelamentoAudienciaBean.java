package com.sgnpj.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import com.sgnpj.model.Audiencia;
import com.sgnpj.service.CancelamentoAudienciaService;
import com.sgnpj.util.jsf.FacesUtil;


@Named
@RequestScoped
public class CancelamentoAudienciaBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CancelamentoAudienciaService cancelamentoAudienciaService;
	
	@Inject
	private Event<AudienciaAlteradoEvent> audienciaAlteradaEvent;
	
	@Inject
	@EdicaoAudiencia
	private Audiencia audiencia;
	
	public CancelamentoAudienciaBean() {
		this.audiencia = new Audiencia();
	}
	
	public void cancelarAudiencia(){
		this.audiencia = this.cancelamentoAudienciaService.cancelar(this.audiencia);
		this.audienciaAlteradaEvent.fire(new AudienciaAlteradoEvent(this.audiencia));
		
		FacesUtil.addInfoMesage("Audiencia cancelada com sucesso!");
	}

}
