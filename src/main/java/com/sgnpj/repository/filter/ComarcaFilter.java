package com.sgnpj.repository.filter;

import java.io.Serializable;

import com.sgnpj.model.Tribunal;

public class ComarcaFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private Tribunal tribunal;

	public ComarcaFilter() {
		this.tribunal = new Tribunal();
	}

	public ComarcaFilter(Long id, String nome, Tribunal tribunal) {
		this.id = id;
		this.nome = nome;
		this.tribunal = tribunal;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Tribunal getTribunal() {
		return tribunal;
	}

	public void setTribunal(Tribunal tribunal) {
		this.tribunal = tribunal;
	}

}
