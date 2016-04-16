package com.sgnpj.model;

public enum TipoAudiencia {
	
	INSTRUÇÃO("Instrução"), JULGAMENTO("Julgamento");
	
	private String descricao;
	
	private TipoAudiencia(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
