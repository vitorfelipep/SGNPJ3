package com.sgnpj.model;

public enum StatusProcesso {
	
	EM_ANDAMENTO("Em_Andamento"),
	FECHADO("Fechado"),
	CANCELADO("Cancelado");
	
	private String descricao;
	
	private StatusProcesso(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
}
