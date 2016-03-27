package com.sgnpj.repository.filter;

import java.io.Serializable;

public class AdvogadoFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigo_OAB;
	private String nome;

	public String getCodigo_OAB() {
		return codigo_OAB;
	}

	public void setCodigo_OAB(String codigo_OAB) {
		this.codigo_OAB = codigo_OAB;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
