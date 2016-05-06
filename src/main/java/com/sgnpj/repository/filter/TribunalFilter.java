package com.sgnpj.repository.filter;

import java.io.Serializable;

public class TribunalFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idTribunal;
	private String nome;

	public TribunalFilter() {

	}

	public TribunalFilter(Long idTribunal, String nome) {
		this.idTribunal = idTribunal;
		this.nome = nome;
	}

	public Long getIdTribunal() {
		return idTribunal;
	}

	public void setIdTribunal(Long idTribunal) {
		this.idTribunal = idTribunal;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
