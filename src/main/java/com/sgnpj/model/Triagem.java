package com.sgnpj.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "triagem")
public class Triagem implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Integer qtdFilhos;
	private Integer maior14;
	private Integer menor14;
	private BigDecimal rendaBruta;
	private Double rendaFamiliar;
	private String primeiroAtendimento;
	private String jafoiAtendido;
	private String qualProblemaAnterior;
	private String obsTriagem;
	private Assistido assistidotriagem;

	public Triagem() {
	}

	public Triagem(Integer qtdFilhos, Integer maior14, Integer menor14,
			BigDecimal rendaBruta, Double rendaFamiliar,
			String primeiroAtendimento, String jafoiAtendido,
			String qualProblemaAnterior, String obsTriagem,
			Assistido assistidotriagem) {
		this.qtdFilhos = qtdFilhos;
		this.maior14 = maior14;
		this.menor14 = menor14;
		this.rendaBruta = rendaBruta;
		this.rendaFamiliar = rendaFamiliar;
		this.primeiroAtendimento = primeiroAtendimento;
		this.jafoiAtendido = jafoiAtendido;
		this.qualProblemaAnterior = qualProblemaAnterior;
		this.obsTriagem = obsTriagem;
		this.assistidotriagem = assistidotriagem;
	}

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull
	@Column(nullable = false)
	public Integer getQtdFilhos() {
		return qtdFilhos;
	}

	public void setQtdFilhos(Integer qtdFilhos) {
		this.qtdFilhos = qtdFilhos;
	}

	@NotNull
	@Column(nullable = false)
	public Integer getMaior14() {
		return maior14;
	}

	public void setMaior14(Integer maior14) {
		this.maior14 = maior14;
	}

	@NotNull
	@Column(nullable = false)
	public Integer getMenor14() {
		return menor14;
	}

	public void setMenor14(Integer menor14) {
		this.menor14 = menor14;
	}

	@NotNull
	@Column(name = "renda_bruta", nullable = false, precision = 10, scale = 2)
	public BigDecimal getRendaBruta() {
		return rendaBruta;
	}

	public void setRendaBruta(BigDecimal rendaBruta) {
		this.rendaBruta = rendaBruta;
	}

	@NotNull
	@Column(name = "renda_familiar", nullable = false, precision = 10, scale = 2)
	public Double getRendaFamiliar() {
		return rendaFamiliar;
	}

	public void setRendaFamiliar(Double rendaFamiliar) {
		this.rendaFamiliar = rendaFamiliar;
	}

	@NotBlank
	@Column(name = "primeiro_atendimento", nullable = false, length = 3)
	public String getPrimeiroAtendimento() {
		return primeiroAtendimento;
	}

	public void setPrimeiroAtendimento(String primeiroAtendimento) {
		this.primeiroAtendimento = primeiroAtendimento;
	}

	@Column(columnDefinition = "text", nullable = false)
	public String getObsTriagem() {
		return obsTriagem;
	}

	public void setObsTriagem(String obsTriagem) {
		this.obsTriagem = obsTriagem;
	}

	@OneToOne
	@JoinColumn(name = "id_assistido_triagem")
	public Assistido getAssistidotriagem() {
		return assistidotriagem;
	}

	public void setAssistidotriagem(Assistido assistidotriagem) {
		this.assistidotriagem = assistidotriagem;
	}

	@NotBlank
	@Column(name = "ja_atendido", nullable = true, length = 3)
	public String getJafoiAtendido() {
		return jafoiAtendido;
	}

	public void setJafoiAtendido(String jafoiAtendido) {
		this.jafoiAtendido = jafoiAtendido;
	}

	@NotBlank
	@Column(name = "problema_anterior", nullable = true, length = 60)
	public String getQualProblemaAnterior() {
		return qualProblemaAnterior;
	}

	public void setQualProblemaAnterior(String qualProblemaAnterior) {
		this.qualProblemaAnterior = qualProblemaAnterior;
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
