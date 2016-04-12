package com.sgnpj.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "atendimento")
public class Atendimento implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private List<Assistido> assistidos;
	private List<Advogado> advogados;
	private Processo processo;
	private String atendimentoRelato;
	private Date dataAtendimento;

	public Atendimento() {
	}

	public Atendimento(Long id, String atendimentoRelato, Date dataAtendimento) {
		this.id = id;
		this.atendimentoRelato = atendimentoRelato;
		this.dataAtendimento = dataAtendimento;
	}

	public Atendimento(Long id, List<Assistido> assistidos,
			List<Advogado> advogados, Processo processo,
			String atendimentoRelato, Date dataAtendimento) {
		this.id = id;
		this.assistidos = assistidos;
		this.advogados = advogados;
		this.processo = processo;
		this.atendimentoRelato = atendimentoRelato;
		this.dataAtendimento = dataAtendimento;
	}

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Assistido> getAssistidos() {
		return assistidos;
	}

	public void setAssistidos(List<Assistido> assistidos) {
		this.assistidos = assistidos;
	}

	@ManyToOne
	@JoinColumn(name = "processo_id", nullable = false)
	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Advogado> getAdvogados() {
		return advogados;
	}

	public void setAdvogados(List<Advogado> advogados) {
		this.advogados = advogados;
	}
	
	@Column(columnDefinition = "text", nullable = false)
	public String getAtendimentoRelato() {
		return atendimentoRelato;
	}

	public void setAtendimentoRelato(String atendimentoRelato) {
		this.atendimentoRelato = atendimentoRelato;
	}
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_atendimento", nullable = false)
	public Date getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(Date dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
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
		Atendimento other = (Atendimento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Transient
	public void adicionarAssistido(Assistido a) {
		if (this.assistidos == null) {
			this.assistidos = new ArrayList<Assistido>();
		}

		this.assistidos.add(a);
	}

	@Transient
	public void adicionarAdvogados(Advogado ad) {
		if (this.advogados == null) {
			this.advogados = new ArrayList<Advogado>();
		}

		this.advogados.add(ad);
	}

}
