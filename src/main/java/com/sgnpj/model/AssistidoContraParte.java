package com.sgnpj.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "assistido_contra_parte")
public class AssistidoContraParte implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String tipoAssistido;
	private String cep;
	private String estado;
	private String cidade;
	private String bairro;
	private String logradouro;
	private Integer numero;
	private String complemento;
	private TipoEndereco tipoEndereco;
	private String email;
	private String telefoneResidencial;
	private String celularPessoal;
	private String telefoneTrabalho;
	private String celularTrabalho;
	private PessoaFisica pessoaFisica;
	private PessoaJuridica pessoaJuridica;
	private Assistido assistidoAutor;

	public AssistidoContraParte() {
		this.pessoaFisica = new PessoaFisica();
		this.pessoaJuridica = new PessoaJuridica();
	}

	public AssistidoContraParte(Long id, String nome, String tipoAssistido,
			String cep, String estado, String cidade, String bairro,
			String logradouro, Integer numero, String complemento,
			TipoEndereco tipoEndereco, String email,
			String telefoneResidencial, String celularPessoal,
			String telefoneTrabalho, String celularTrabalho,
			PessoaFisica pessoaFisica, PessoaJuridica pessoaJuridica,
			Assistido assistidoAutor) {
		this.id = id;
		this.nome = nome;
		this.tipoAssistido = tipoAssistido;
		this.cep = cep;
		this.estado = estado;
		this.cidade = cidade;
		this.bairro = bairro;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.tipoEndereco = tipoEndereco;
		this.email = email;
		this.telefoneResidencial = telefoneResidencial;
		this.celularPessoal = celularPessoal;
		this.telefoneTrabalho = telefoneTrabalho;
		this.celularTrabalho = celularTrabalho;
		this.pessoaFisica = pessoaFisica;
		this.pessoaJuridica = pessoaJuridica;
		this.assistidoAutor = assistidoAutor;
	}

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotBlank
	@Column(nullable = false, length = 60)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@NotEmpty
	@Column(nullable = false, length = 8)
	public String getTipoAssistido() {
		return tipoAssistido;
	}

	public void setTipoAssistido(String tipoAssistido) {
		this.tipoAssistido = tipoAssistido;
	}

	@NotBlank
	@Column(nullable = false, length = 9)
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@NotBlank
	@Column(nullable = false, length = 2)
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@NotEmpty
	@Column(nullable = false, length = 80)
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@NotBlank
	@Column(nullable = false, length = 80)
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	@NotBlank
	@Column(nullable = false, length = 80)
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	@NotNull
	@Column(nullable = false)
	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	@Column(nullable = true, length = 60)
	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 30)
	public TipoEndereco getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(TipoEndereco tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}

	@NotBlank
	@Email(message = "inválido!")
	@Column(nullable = false, unique = true, length = 150)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	@OneToOne
	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	@OneToOne
	public PessoaJuridica getPessoaJuridica() {
		return pessoaJuridica;
	}

	public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}

	@ManyToOne
	@JoinColumn(name = "assistido_id", nullable = true)
	public Assistido getAssistidoAutor() {
		return assistidoAutor;
	}

	public void setAssistidoAutor(Assistido assistidoAutor) {
		this.assistidoAutor = assistidoAutor;
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
		AssistidoContraParte other = (AssistidoContraParte) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
