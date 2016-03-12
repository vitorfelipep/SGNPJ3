package com.sgnpj.model;

import java.io.Serializable;
import java.util.Date;

public class Estagiario implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private Integer matricula;
	private String areaDesignada;
	private Date dataInicio;
	private String telefoneContato;
	private String celularContato;
	private String situacao;
	private String periodo;
	private String turma;
	private String turno;
	private Date dataNascimento;
	private Date dataTerminoEstagio;
	private String codOab;
	private String ObsEstagio;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public String getAreaDesignada() {
		return areaDesignada;
	}

	public void setAreaDesignada(String areaDesignada) {
		this.areaDesignada = areaDesignada;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getTelefoneContato() {
		return telefoneContato;
	}

	public void setTelefoneContato(String telefoneContato) {
		this.telefoneContato = telefoneContato;
	}

	public String getCelularContato() {
		return celularContato;
	}

	public void setCelularContato(String celularContato) {
		this.celularContato = celularContato;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Date getDataTerminoEstagio() {
		return dataTerminoEstagio;
	}

	public void setDataTerminoEstagio(Date dataTerminoEstagio) {
		this.dataTerminoEstagio = dataTerminoEstagio;
	}

	public String getCodOab() {
		return codOab;
	}

	public void setCodOab(String codOab) {
		this.codOab = codOab;
	}

	public String getObsEstagio() {
		return ObsEstagio;
	}

	public void setObsEstagio(String obsEstagio) {
		ObsEstagio = obsEstagio;
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
