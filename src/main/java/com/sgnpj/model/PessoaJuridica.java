package com.sgnpj.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

@Entity
@Table(name = "pessoa_juridica")
public class PessoaJuridica implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String cnpj;
	private String nomeJuridico;
	private Date dataAbertura;
	private String ramoNegocio;
	private String razaoSocial;
	private Assistido assistido;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@NotBlank
	@CNPJ(message = "inválido!")
	@Column(nullable = false, length = 25)
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	@NotBlank
	@Column(nullable = false, length = 45)
	public String getNomeJuridico() {
		return nomeJuridico;
	}

	public void setNomeJuridico(String nomeJuridico) {
		this.nomeJuridico = nomeJuridico;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_cadastro", nullable = false)
	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	
	@NotBlank
	@Column(nullable = false, length = 45)
	public String getRamoNegocio() {
		return ramoNegocio;
	}

	public void setRamoNegocio(String ramoNegocio) {
		this.ramoNegocio = ramoNegocio;
	}
	
	@NotBlank
	@Column(nullable = false, length = 45)
	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	
	@OneToOne
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
		PessoaJuridica other = (PessoaJuridica) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
