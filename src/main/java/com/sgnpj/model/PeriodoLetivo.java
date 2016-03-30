package com.sgnpj.model;

public enum PeriodoLetivo {
	
	PRIMEIRO_PERIODO("1º Período"),SEGUNDO_PERIODO("2º Período"),TERCEIRO_PERIODO("3º Período"),QUARTO_PERIODO("4º Período"),QUINTO_PERIODO("5º Período")
	,SEXTO_PERIODO("6º Período"),SETIMO_PERIODO("7º Período"),OITAVO_PERIODO("8º Período");
	
	private String descricao;
	
	private PeriodoLetivo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	

}
