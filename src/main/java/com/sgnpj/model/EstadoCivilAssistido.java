package com.sgnpj.model;

public enum EstadoCivilAssistido {
	
	SOLTEIRO("Solteiro"), CASADO("Casado"), VIUVO("Viúvo(a)");
	
	private String descricao;
	
	private EstadoCivilAssistido(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
