package com.sgnpj.model;

public enum StatusAtendimento {
	
	EM_ATENDIMENTO("Em atendimento"),
	EM_APROVACAO("Atendimento em aprovação"),
	EM_ANDAMENTO("Em andamento"),
	CANCELADO("Atendimento cancelado"),
	FINALIZADO("Atendimento finalizado");
	
	
	private String descricao;
	
	private StatusAtendimento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
