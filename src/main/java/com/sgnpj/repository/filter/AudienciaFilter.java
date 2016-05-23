package com.sgnpj.repository.filter;

import java.io.Serializable;
import java.util.Date;

import com.sgnpj.model.StatusAudiencia;

public class AudienciaFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer numeroProcesso;

	private Date dataAudienciaInicial;

	private Date dataAudienciaFinal;

	private StatusAudiencia[] statusAudiencia;
	
	public AudienciaFilter() {
	}

	public Integer getNumeroProcesso() {
		return numeroProcesso;
	}

	public void setNumeroProcesso(Integer numeroProcesso) {
		this.numeroProcesso = numeroProcesso;
	}

	public Date getDataAudienciaInicial() {
		return dataAudienciaInicial;
	}

	public void setDataAudienciaInicial(Date dataAudienciaInicial) {
		this.dataAudienciaInicial = dataAudienciaInicial;
	}

	public Date getDataAudienciaFinal() {
		return dataAudienciaFinal;
	}

	public void setDataAudienciaFinal(Date dataAudienciaFinal) {
		this.dataAudienciaFinal = dataAudienciaFinal;
	}

	public StatusAudiencia[] getStatusAudiencia() {
		return statusAudiencia;
	}

	public void setStatusAudiencia(StatusAudiencia[] statusAudiencia) {
		this.statusAudiencia = statusAudiencia;
	}

}
