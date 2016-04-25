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
@Table(name = "pessoa_fisica")
public class PessoaFisica implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String cpf;
	private Date dataNascimento;
	private String identidade;
	private String orgao;
	private String cnh;
	private String carteiraTrabalho;
	private String nomePai;
	private String nomeMae;
	private EstadoCivilAssistido estadoCivil;
	private String profissao;
	private String nacionalidade;
	private String naturalidade;
	private String sexo;
	private String menorIdade;
	private Assistido assitido;

	public PessoaFisica() {
	}

	public PessoaFisica(String cpf, Date dataNascimento, String identidade,
			String orgao, String cnh, String carteiraTrabalho, String nomePai,
			String nomeMae, EstadoCivilAssistido estadoCivil, String profissao,
			String nacionalidade, String naturalidade, String sexo,
			String menorIdade, Assistido assitido) {
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.identidade = identidade;
		this.orgao = orgao;
		this.cnh = cnh;
		this.carteiraTrabalho = carteiraTrabalho;
		this.nomePai = nomePai;
		this.nomeMae = nomeMae;
		this.estadoCivil = estadoCivil;
		this.profissao = profissao;
		this.nacionalidade = nacionalidade;
		this.naturalidade = naturalidade;
		this.sexo = sexo;
		this.menorIdade = menorIdade;
		this.assitido = assitido;
	}

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotEmpty
	@CPF
	@Column(nullable = false, length = 15)
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "data_nascimento", nullable = false)
	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@NotBlank
	@Column(nullable = false, unique = true, length = 15)
	public String getIdentidade() {
		return identidade;
	}

	public void setIdentidade(String identidade) {
		this.identidade = identidade;
	}

	@Column(nullable = true, length = 20)
	public String getCnh() {
		return cnh;
	}

	public void setCnh(String cnh) {
		this.cnh = cnh;
	}

	@NotBlank
	@Column(nullable = false, unique = true, length = 15)
	public String getCarteiraTrabalho() {
		return carteiraTrabalho;
	}

	public void setCarteiraTrabalho(String carteiraTrabalho) {
		this.carteiraTrabalho = carteiraTrabalho;
	}

	@NotBlank
	@Column(name = "nome_pai", nullable = false, length = 30)
	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	@NotBlank
	@Column(name = "nome_mae", nullable = false, length = 30)
	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 40)
	public EstadoCivilAssistido getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivilAssistido estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	@NotBlank
	@Column(nullable = false, length = 60)
	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	@NotBlank
	@Column(nullable = false, length = 50)
	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	@NotBlank
	@Column(nullable = false, length = 50)
	public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	@NotBlank
	@Column(nullable = false, length = 1)
	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	@NotBlank
	@Column(nullable = true, length = 1)
	public String getMenorIdade() {
		return menorIdade;
	}

	public void setMenorIdade(String menorIdade) {
		this.menorIdade = menorIdade;
	}

	@OneToOne
	public Assistido getAssitido() {
		return assitido;
	}

	public void setAssitido(Assistido assitido) {
		this.assitido = assitido;
	}

	@NotBlank
	@Column(nullable = false, length = 10)
	public String getOrgao() {
		return orgao;
	}

	public void setOrgao(String orgao) {
		this.orgao = orgao;
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
		PessoaFisica other = (PessoaFisica) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
