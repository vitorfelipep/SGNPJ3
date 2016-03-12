package com.sgnpj.model;

public enum TipoPessoa {
	FISICA("Fisíca"), JURIDICA("Júridica");
	
	private String descricao;
	
	private TipoPessoa(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
