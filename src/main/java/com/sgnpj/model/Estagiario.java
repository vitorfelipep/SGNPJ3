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

@Entity
@Table(name = "estagiario")
public class Estagiario implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private Integer matricula;
	private AreaAtuacao areaDesignada;
	private Date dataInicio;
	private String telefoneContato;
	private String celularContato;
	private Situacao situacao;
	private String periodo;
	private String turma;
	private String turno;
	private Date dataNascimento;
	private Date dataTerminoEstagio;
	private String codOab;
	private String obsEstagio;
	private Usuario usuario;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotBlank
	@Column(nullable = false, length = 80)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(nullable = false, length = 11)
	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	public AreaAtuacao getAreaDesignada() {
		return areaDesignada;
	}

	public void setAreaDesignada(AreaAtuacao areaDesignada) {
		this.areaDesignada = areaDesignada;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_inicio", nullable = false)
	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	@NotBlank
	@Column(nullable = false, length = 80)
	public String getTelefoneContato() {
		return telefoneContato;
	}

	public void setTelefoneContato(String telefoneContato) {
		this.telefoneContato = telefoneContato;
	}

	@Column(nullable = true, length = 80)
	public String getCelularContato() {
		return celularContato;
	}

	public void setCelularContato(String celularContato) {
		this.celularContato = celularContato;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	@Column(nullable = false, length = 20)
	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	@Column(nullable = false, length = 20)
	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}

	@Column(nullable = false, length = 20)
	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "data_termino", nullable = true)
	public Date getDataTerminoEstagio() {
		return dataTerminoEstagio;
	}

	public void setDataTerminoEstagio(Date dataTerminoEstagio) {
		this.dataTerminoEstagio = dataTerminoEstagio;
	}

	@Column(nullable = false, unique = true, length = 25)
	public String getCodOab() {
		return codOab;
	}

	public void setCodOab(String codOab) {
		this.codOab = codOab;
	}

	@Column(columnDefinition = "text", nullable = false)
	public String getObsEstagio() {
		return obsEstagio;
	}

	public void setObsEstagio(String obsEstagio) {
		this.obsEstagio = obsEstagio;
	}

	@OneToOne
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((matricula == null) ? 0 : matricula.hashCode());
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
		Estagiario other = (Estagiario) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}

}
