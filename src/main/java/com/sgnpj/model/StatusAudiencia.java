package com.sgnpj.model;

public enum StatusAudiencia {
	
	INICIADA("Audiencia Marcada"), 
	FINALIZADA("Audiencia finalizada"),
	CANCELADA("Audiencia Cancelada");
	
private String descricao;
	
	private StatusAudiencia(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
