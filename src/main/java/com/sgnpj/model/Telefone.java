package com.sgnpj.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "telefone")
public class Telefone implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id_telefone;
	private String telefoneResidencial;
	private String celularPessoal;
	private String telefoneTrabalho;
	private String celularTrabalho;
	private Assistido assistido;

	@Id
	@GeneratedValue
	public Long getId_telefone() {
		return id_telefone;
	}

	public void setId_telefone(Long id_telefone) {
		this.id_telefone = id_telefone;
	}
	
	@NotEmpty
	@Column(nullable = false)
	public String getTelefoneResidencial() {
		return telefoneResidencial;
	}

	public void setTelefoneResidencial(String telefoneResidencial) {
		this.telefoneResidencial = telefoneResidencial;
	}
	
	@NotEmpty
	@Column(nullable = false)
	public String getCelularPessoal() {
		return celularPessoal;
	}

	public void setCelularPessoal(String celularPessoal) {
		this.celularPessoal = celularPessoal;
	}
	
	
	@Column(nullable = true)
	public String getTelefoneTrabalho() {
		return telefoneTrabalho;
	}

	public void setTelefoneTrabalho(String telefoneTrabalho) {
		this.telefoneTrabalho = telefoneTrabalho;
	}
	
	@Column(nullable = true)
	public String getCelularTrabalho() {
		return celularTrabalho;
	}

	public void setCelularTrabalho(String celularTrabalho) {
		this.celularTrabalho = celularTrabalho;
	}
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "assistido_id", nullable = false)
	public Assistido getAssistido() {
		return assistido;
	}
	
	public void setAssistido(Assistido assistido) {
		this.assistido = assistido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_telefone == null) ? 0 : id_telefone.hashCode());
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
		if (id_telefone == null) {
			if (other.id_telefone != null)
				return false;
		} else if (!id_telefone.equals(other.id_telefone))
			return false;
		return true;
	}

}
