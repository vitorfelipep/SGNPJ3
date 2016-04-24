package com.sgnpj.model;

public enum TipoEndereco {
	
	CASA_PROPRIA("Casa própria"), ALUGADO("Alugado"),CEDIDO("Cedido");
	
	private String descricao;
	
	private TipoEndereco(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
