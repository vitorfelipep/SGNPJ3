package com.sgnpj.model;

public enum Situacao {
	ATIVO("Ativo"), INATIVO("Inativo"), EMFERIAS("Em férias"), AFASTADO("Afastado");
	
	private String descricao;
	
	private Situacao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
