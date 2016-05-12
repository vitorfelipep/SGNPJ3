package com.sgnpj.model;

public enum SituacaoAssitido {
	
	APROVADO("Aprovado"), NAO_APROVADO("Não Aprovado"),EM_APROVACAO("Em aprovação");
	
	private String descricao;
	
	private SituacaoAssitido(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
