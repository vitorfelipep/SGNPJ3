package com.sgnpj.repository.filter;

import java.io.Serializable;

import com.sgnpj.model.Assistido;
import com.sgnpj.model.SituacaoAssitido;

public class FiltroHipossuficiencia implements Serializable {

	private static final long serialVersionUID = 1L;

	private String email;

	private Assistido assistido;

	private SituacaoAssitido situacaoAssistido;

	public FiltroHipossuficiencia() {

	}

	public FiltroHipossuficiencia(String email, Assistido assistido,
			SituacaoAssitido situacaoAssistido) {
		this.email = email;
		this.assistido = assistido;
		this.situacaoAssistido = situacaoAssistido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Assistido getAssistido() {
		return assistido;
	}

	public void setAssistido(Assistido assistido) {
		this.assistido = assistido;
	}

	public SituacaoAssitido getSituacaoAssistido() {
		return situacaoAssistido;
	}

	public void setSituacaoAssistido(SituacaoAssitido situacaoAssistido) {
		this.situacaoAssistido = situacaoAssistido;
	}

}
