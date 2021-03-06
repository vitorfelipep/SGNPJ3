package com.sgnpj.repository.filter;

import java.io.Serializable;
import java.util.Date;

import com.sgnpj.model.Advogado;
import com.sgnpj.model.Assistido;
import com.sgnpj.model.StatusAtendimento;

public class AtendimentoFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date dataInicial;
	private Date dataFinal;
	private Advogado advogado;
	private Assistido assistido;
	private StatusAtendimento[] statusAtendimento;

	public AtendimentoFilter() {
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Advogado getAdvogado() {
		return advogado;
	}

	public void setAdvogado(Advogado advogado) {
		this.advogado = advogado;
	}

	public Assistido getAssistido() {
		return assistido;
	}

	public void setAssistido(Assistido assistido) {
		this.assistido = assistido;
	}

	public StatusAtendimento[] getStatusAtendimento() {
		return statusAtendimento;
	}

	public void setStatusAtendimento(StatusAtendimento[] statusAtendimento) {
		this.statusAtendimento = statusAtendimento;
	}

}
