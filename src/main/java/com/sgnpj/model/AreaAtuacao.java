package com.sgnpj.model;

public enum AreaAtuacao {
	
	PENAL("Penal"),FAMILIAR("Familiar"),CIVIL("Cívil"),TRABALHISTA("Trabalhista");
	
	private String descricao;
	
	private AreaAtuacao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

	
}
