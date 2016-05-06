package com.sgnpj.repository.filter;

import java.io.Serializable;
import java.util.Date;

public class EstagiarioFiltro implements Serializable{

	private static final long serialVersionUID = 1L;

	private String numOab;
	private Integer matricula;
	private String nome;
	private Date dataInicial;
	private Date dataFinal;

	public EstagiarioFiltro() {
	}

	public EstagiarioFiltro(String numOab, Integer matricula, String nome,
			Date dataInicial, Date dataFinal) {
		this.numOab = numOab;
		this.matricula = matricula;
		this.nome = nome;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
	}

	public String getNumOab() {
		return numOab;
	}

	public void setNumOab(String numOab) {
		this.numOab = numOab;
	}

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

}
