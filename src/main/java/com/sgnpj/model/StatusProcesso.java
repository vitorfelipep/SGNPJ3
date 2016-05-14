package com.sgnpj.model;

public enum StatusProcesso {
	
	EM_ANDAMENTO("Em andamento"),
	ARQUIVADO("Processo arquivado"),
	CANCELADO("Processo Cancelado");
	
	private String descricao;
	
	private StatusProcesso(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
}
