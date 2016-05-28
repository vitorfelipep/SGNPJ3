package com.sgnpj.repository.filter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.sgnpj.model.Atendimento;
import com.sgnpj.model.StatusProcesso;

public class ProcessoFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date dataAberturaInicial;
	private Date dataAberturaFinal;
	private Atendimento atendimento;
	private Integer numeroProcesso;
	private StatusProcesso[] situacao;

	public ProcessoFilter() {

	}

	public ProcessoFilter(Date dataAberturaInicial, Date dataAberturaFinal,
			Atendimento atendimento, Integer numeroProcesso,
			StatusProcesso[] situacao) {
		this.dataAberturaInicial = dataAberturaInicial;
		this.dataAberturaFinal = dataAberturaFinal;
		this.atendimento = atendimento;
		this.numeroProcesso = numeroProcesso;
		this.situacao = situacao;
	}

	public ProcessoFilter(Date dataAberturaInicial, Date dataAberturaFinal,
			Integer numeroProcesso, StatusProcesso[] situacao) {
		this.dataAberturaInicial = dataAberturaInicial;
		this.dataAberturaFinal = dataAberturaFinal;
		this.numeroProcesso = numeroProcesso;
		this.situacao = situacao;
	}

	/* getter and setters */
	
	@NotNull
	public Date getDataAberturaInicial() {
		return dataAberturaInicial;
	}

	public void setDataAberturaInicial(Date dataAberturaInicial) {
		this.dataAberturaInicial = dataAberturaInicial;
	}
	
	@NotNull(message = "Data de Final é obrigatório!")
	public Date getDataAberturaFinal() {
		return dataAberturaFinal;
	}

	public void setDataAberturaFinal(Date dataAberturaFinal) {
		this.dataAberturaFinal = dataAberturaFinal;
	}

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	public Integer getNumeroProcesso() {
		return numeroProcesso;
	}

	public void setNumeroProcesso(Integer numeroProcesso) {
		this.numeroProcesso = numeroProcesso;
	}

	public StatusProcesso[] getSituacao() {
		return situacao;
	}

	public void setSituacao(StatusProcesso[] situacao) {
		this.situacao = situacao;
	}

	/* toString */

	@Override
	public String toString() {
		return "ProcessoFilter [dataAberturaInicial=" + dataAberturaInicial
				+ ", dataAberturaFinal=" + dataAberturaFinal + ", atendimento="
				+ atendimento + ", numeroProcesso=" + numeroProcesso
				+ ", situacao=" + Arrays.toString(situacao) + "]";
	}

}
