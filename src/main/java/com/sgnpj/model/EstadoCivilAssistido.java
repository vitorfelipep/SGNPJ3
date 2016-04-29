package com.sgnpj.model;

public enum EstadoCivilAssistido {
	
	SOLTEIRO("Solteiro(a)"), CASADO("Casado(a)"), VIUVO("Viúvo(a)"), DIVORCIADO("Divorciado(a)");
	
	private String descricao;
	
	private EstadoCivilAssistido(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
