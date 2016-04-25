package com.sgnpj.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "advogado")
public class Advogado implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id_advogado;
	private String codigo_OAB;
	private Usuario usuario;
	private Date dataCadastro;
	private AreaAtuacao areaAtuacao;
	private Situacao situacao;
	private String endereco;
	private Integer numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private String estado;
	private String cep;
	private String cpf;
	private String telefoneContato;
	private String celularContato;

	public Advogado() {
		// TODO Auto-generated constructor stub
	}

	public Advogado(Integer id_advogado, String codigo_OAB, Usuario usuario,
			Date dataCadastro, AreaAtuacao areaAtuacao, Situacao situacao,
			String endereco, Integer numero, String complemento, String bairro,
			String cidade, String estado, String cep, String cpf,
			String telefoneContato, String celularContato) {
		this.id_advogado = id_advogado;
		this.codigo_OAB = codigo_OAB;
		this.usuario = usuario;
		this.dataCadastro = dataCadastro;
		this.areaAtuacao = areaAtuacao;
		this.situacao = situacao;
		this.endereco = endereco;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
		this.cpf = cpf;
		this.telefoneContato = telefoneContato;
		this.celularContato = celularContato;
	}

	@Id
	@GeneratedValue
	public Integer getId_advogado() {
		return id_advogado;
	}

	public void setId_advogado(Integer id_advogado) {
		this.id_advogado = id_advogado;
	}

	@NotBlank
	@Column(nullable = false, unique = true, length = 25)
	public String getCodigo_OAB() {
		return codigo_OAB;
	}

	public void setCodigo_OAB(String codigo_OAB) {
		this.codigo_OAB = codigo_OAB;
	}

	@OneToOne
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_cadastro", nullable = false)
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 50)
	public AreaAtuacao getAreaAtuacao() {
		return areaAtuacao;
	}

	public void setAreaAtuacao(AreaAtuacao areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 10)
	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	@NotBlank
	@Column(nullable = false, length = 80)
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Column(nullable = true, length = 80)
	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	@NotBlank
	@Column(nullable = false, length = 80)
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	@NotNull
	@Column(nullable = false, length = 80)
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@NotBlank
	@Column(nullable = false, length = 2)
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@NotBlank
	@Column(nullable = false, length = 9)
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@NotEmpty
	@CPF(message = "inválido!")
	@Column(nullable = false)
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@NotEmpty
	@Column(nullable = false)
	public String getTelefoneContato() {
		return telefoneContato;
	}

	public void setTelefoneContato(String telefoneContato) {
		this.telefoneContato = telefoneContato;
	}

	@NotEmpty
	@Column(nullable = false)
	public String getCelularContato() {
		return celularContato;
	}

	public void setCelularContato(String celularContato) {
		this.celularContato = celularContato;
	}

	@NotNull
	@Column(nullable = false)
	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
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
		Advogado other = (Advogado) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}

}
