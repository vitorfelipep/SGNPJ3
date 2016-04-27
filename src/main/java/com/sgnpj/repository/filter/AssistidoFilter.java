package com.sgnpj.repository.filter;

import java.io.Serializable;

import com.sgnpj.model.SituacaoAssitido;

public class AssistidoFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nomeAssistido;
	private SituacaoAssitido situacaoAssistido;
	private String tipoAssistido;
	private String cpf;
	private String rg;
	private String cnpj;

	public AssistidoFilter() {
	}

	public AssistidoFilter(String nomeAssistido,
			SituacaoAssitido situacaoAssistido, String tipoAssistido,
			String cpf, String rg, String cnpj) {
		this.nomeAssistido = nomeAssistido;
		this.situacaoAssistido = situacaoAssistido;
		this.tipoAssistido = tipoAssistido;
		this.cpf = cpf;
		this.rg = rg;
		this.cnpj = cnpj;
	}

	public String getNomeAssistido() {
		return nomeAssistido;
	}

	public void setNomeAssistido(String nomeAssistido) {
		this.nomeAssistido = nomeAssistido;
	}

	public SituacaoAssitido getSituacaoAssistido() {
		return situacaoAssistido;
	}

	public void setSituacaoAssistido(SituacaoAssitido situacaoAssistido) {
		this.situacaoAssistido = situacaoAssistido;
	}

	public String getTipoAssistido() {
		return tipoAssistido;
	}

	public void setTipoAssistido(String tipoAssistido) {
		this.tipoAssistido = tipoAssistido;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

}
