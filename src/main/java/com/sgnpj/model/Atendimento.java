package com.sgnpj.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	private Assistido assistido;
	private Advogado advogado;
	private Estagiario estagiario;
	private StatusAtendimento statusAtendimento;
	private String atendimentoRelato;
	private Date dataAtendimento;
	private String areaAtendimento;
	private List<Processo> processos;

	public Atendimento() {
	}

	public Atendimento(Long id, String atendimentoRelato, Date dataAtendimento) {
		this.id = id;
		this.atendimentoRelato = atendimentoRelato;
		this.dataAtendimento = dataAtendimento;
	}

	public Atendimento(Long id, Assistido assistido, Advogado advogado,
			Estagiario estagiario, StatusAtendimento statusAtendimento,
			String atendimentoRelato, Date dataAtendimento,
			String areaAtendimento, List<Processo> processos) {
		this.id = id;
		this.assistido = assistido;
		this.advogado = advogado;
		this.estagiario = estagiario;
		this.statusAtendimento = statusAtendimento;
		this.atendimentoRelato = atendimentoRelato;
		this.dataAtendimento = dataAtendimento;
		this.areaAtendimento = areaAtendimento;
		this.processos = processos;
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
	@ManyToOne
	@JoinColumn(name = "assitido_id", nullable = false)
	public Assistido getAssistido() {
		return assistido;
	}

	public void setAssistido(Assistido assistido) {
		this.assistido = assistido;
	}

	@ManyToOne
	@JoinColumn(name = "estagiario_id", nullable = true)
	public Estagiario getEstagiario() {
		return estagiario;
	}

	public void setEstagiario(Estagiario estagiario) {
		this.estagiario = estagiario;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "advogado_id", nullable = false)
	public Advogado getAdvogado() {
		return advogado;
	}

	public void setAdvogado(Advogado advogado) {
		this.advogado = advogado;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 30)
	public StatusAtendimento getStatusAtendimento() {
		return statusAtendimento;
	}

	public void setStatusAtendimento(StatusAtendimento statusAtendimento) {
		this.statusAtendimento = statusAtendimento;
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

	@OneToMany(mappedBy = "atendimento", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	public List<Processo> getProcessos() {
		return processos;
	}

	public void setProcessos(List<Processo> processos) {
		this.processos = processos;
	}

	@NotNull
	@Column(nullable = false, length = 50)
	public String getAreaAtendimento() {
		return areaAtendimento;
	}

	public void setAreaAtendimento(String areaAtendimento) {
		this.areaAtendimento = areaAtendimento;
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
	public boolean isEmAtendimento(){
		return StatusAtendimento.EM_ANDAMENTO.equals(this.statusAtendimento);
	}
	
	@Transient
	public boolean isFinalizarAtendimento(){
		return isEmAtendimento();
	}
	
	@Transient
	@Override
	public String toString() {
		return "Atendimento [id=" + id + ", assistido=" + assistido
				+ ", advogado=" + advogado + ", estagiario=" + estagiario
				+ ", statusAtendimento=" + statusAtendimento
				+ ", atendimentoRelato=" + atendimentoRelato
				+ ", dataAtendimento=" + dataAtendimento + ", areaAtendimento="
				+ areaAtendimento + ", processos=" + processos + "]";
	}

}
