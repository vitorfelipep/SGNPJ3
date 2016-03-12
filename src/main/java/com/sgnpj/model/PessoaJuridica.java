package com.sgnpj.model;

import java.io.Serializable;
import java.util.Date;

public class PessoaJuridica implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cnpj;
	private String nomeJuridico;
	private Date dataAbertura;
	private String ramoNegocio;
	private String razaoSocial;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNomeJuridico() {
		return nomeJuridico;
	}

	public void setNomeJuridico(String nomeJuridico) {
		this.nomeJuridico = nomeJuridico;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public String getRamoNegocio() {
		return ramoNegocio;
	}

	public void setRamoNegocio(String ramoNegocio) {
		this.ramoNegocio = ramoNegocio;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	
}
