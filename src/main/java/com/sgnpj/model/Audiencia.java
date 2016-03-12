package com.sgnpj.model;

import java.io.Serializable;
import java.util.Date;

public class Audiencia implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Date dataAudiencia;
	private String horaAudiencia;
	private String endereco;
	private String cidade;
	private Advogado advogadoResponsavel;
	private Assistido assistido;
	private String juizResponsavel;
	private String varaTribunal;
	private Estagiario estagiario;
	private Processo processo;
	private String tipoAudiencia;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataAudiencia() {
		return dataAudiencia;
	}

	public void setDataAudiencia(Date dataAudiencia) {
		this.dataAudiencia = dataAudiencia;
	}

	public String getHoraAudiencia() {
		return horaAudiencia;
	}

	public void setHoraAudiencia(String horaAudiencia) {
		this.horaAudiencia = horaAudiencia;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Advogado getAdvogadoResponsavel() {
		return advogadoResponsavel;
	}

	public void setAdvogadoResponsavel(Advogado advogadoResponsavel) {
		this.advogadoResponsavel = advogadoResponsavel;
	}

	public Assistido getAssistido() {
		return assistido;
	}

	public void setAssistido(Assistido assistido) {
		this.assistido = assistido;
	}

	public String getJuizResponsavel() {
		return juizResponsavel;
	}

	public void setJuizResponsavel(String juizResponsavel) {
		this.juizResponsavel = juizResponsavel;
	}

	public String getVaraTribunal() {
		return varaTribunal;
	}

	public void setVaraTribunal(String varaTribunal) {
		this.varaTribunal = varaTribunal;
	}

	public Estagiario getEstagiario() {
		return estagiario;
	}

	public void setEstagiario(Estagiario estagiario) {
		this.estagiario = estagiario;
	}

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public String getTipoAudiencia() {
		return tipoAudiencia;
	}

	public void setTipoAudiencia(String tipoAudiencia) {
		this.tipoAudiencia = tipoAudiencia;
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
		Audiencia other = (Audiencia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
