package com.sgnpj.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Triagem implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Integer qtdFilhos;
	private Integer maior14;
	private Integer menor14;
	private BigDecimal rendaBruta;
	private Double rendaFamiliar;
	private String primeiroAtendimento;
	private String obsTriagem;
	private List<Assistido> assistidotriagem  = new ArrayList<>();;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQtdFilhos() {
		return qtdFilhos;
	}

	public void setQtdFilhos(Integer qtdFilhos) {
		this.qtdFilhos = qtdFilhos;
	}

	public Integer getMaior14() {
		return maior14;
	}

	public void setMaior14(Integer maior14) {
		this.maior14 = maior14;
	}

	public Integer getMenor14() {
		return menor14;
	}

	public void setMenor14(Integer menor14) {
		this.menor14 = menor14;
	}

	public BigDecimal getRendaBruta() {
		return rendaBruta;
	}

	public void setRendaBruta(BigDecimal rendaBruta) {
		this.rendaBruta = rendaBruta;
	}

	public Double getRendaFamiliar() {
		return rendaFamiliar;
	}

	public void setRendaFamiliar(Double rendaFamiliar) {
		this.rendaFamiliar = rendaFamiliar;
	}

	public String getPrimeiroAtendimento() {
		return primeiroAtendimento;
	}

	public void setPrimeiroAtendimento(String primeiroAtendimento) {
		this.primeiroAtendimento = primeiroAtendimento;
	}

	public String getObsTriagem() {
		return obsTriagem;
	}

	public void setObsTriagem(String obsTriagem) {
		this.obsTriagem = obsTriagem;
	}

	public List<Assistido> getAssistidotriagem() {
		return assistidotriagem;
	}

	public void setAssistidotriagem(List<Assistido> assistidotriagem) {
		this.assistidotriagem = assistidotriagem;
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
		Triagem other = (Triagem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
