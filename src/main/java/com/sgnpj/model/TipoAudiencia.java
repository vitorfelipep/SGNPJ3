package com.sgnpj.model;

public enum TipoAudiencia {
	
	INSTRUÇÃO("Instrução"), CONCILIACAO("Conciliação"), JULGAMENTO("Julgamento");
	
	private String descricao;
	
	private TipoAudiencia(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
