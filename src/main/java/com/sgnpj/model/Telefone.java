package com.sgnpj.model;

import java.io.Serializable;
import java.util.List;

public class Telefone implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String telefoneResidencial;
	private String celularPessoal;
	private String telefoneTrabalho;
	private String celularTrabalho;
	private List<Assistido> assistido;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTelefoneResidencial() {
		return telefoneResidencial;
	}

	public void setTelefoneResidencial(String telefoneResidencial) {
		this.telefoneResidencial = telefoneResidencial;
	}

	public String getCelularPessoal() {
		return celularPessoal;
	}

	public void setCelularPessoal(String celularPessoal) {
		this.celularPessoal = celularPessoal;
	}

	public String getTelefoneTrabalho() {
		return telefoneTrabalho;
	}

	public void setTelefoneTrabalho(String telefoneTrabalho) {
		this.telefoneTrabalho = telefoneTrabalho;
	}

	public String getCelularTrabalho() {
		return celularTrabalho;
	}

	public void setCelularTrabalho(String celularTrabalho) {
		this.celularTrabalho = celularTrabalho;
	}

	public List<Assistido> getAssistido() {
		return assistido;
	}

	public void setAssistido(List<Assistido> assistido) {
		this.assistido = assistido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Telefone other = (Telefone) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
