package com.sgnpj.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "audiencia")
public class Audiencia implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Date dataAudiencia;
	private String horaAudiencia;
	private String endereco;
	private String estado;
	private String cidade;
	private Advogado advogadoResponsavel;
	private String juizResponsavel;
	private Estagiario estagiario;
	private Processo processo;
	private TipoAudiencia tipoAudiencia;

	public Audiencia() {
	}

	public Audiencia(Long id, Date dataAudiencia, String horaAudiencia,
			String endereco, String cidade, String juizResponsavel,
			TipoAudiencia tipoAudiencia) {
		this.id = id;
		this.dataAudiencia = dataAudiencia;
		this.horaAudiencia = horaAudiencia;
		this.endereco = endereco;
		this.cidade = cidade;
		this.juizResponsavel = juizResponsavel;
		this.tipoAudiencia = tipoAudiencia;
	}

	public Audiencia(Long id, Date dataAudiencia, String horaAudiencia,
			String endereco, String cidade, Advogado advogadoResponsavel,
			String juizResponsavel, Estagiario estagiario, Processo processo,
			TipoAudiencia tipoAudiencia) {
		this.id = id;
		this.dataAudiencia = dataAudiencia;
		this.horaAudiencia = horaAudiencia;
		this.endereco = endereco;
		this.cidade = cidade;
		this.advogadoResponsavel = advogadoResponsavel;
		this.juizResponsavel = juizResponsavel;
		this.estagiario = estagiario;
		this.processo = processo;
		this.tipoAudiencia = tipoAudiencia;
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
	@Temporal(TemporalType.DATE)
	@Column(name = "data_audiencia", nullable = false)
	public Date getDataAudiencia() {
		return dataAudiencia;
	}

	public void setDataAudiencia(Date dataAudiencia) {
		this.dataAudiencia = dataAudiencia;
	}

	@NotBlank
	@Column(name = "hora_audiencia", nullable = false)
	public String getHoraAudiencia() {
		return horaAudiencia;
	}

	public void setHoraAudiencia(String horaAudiencia) {
		this.horaAudiencia = horaAudiencia;
	}

	@NotBlank
	@Column(name = "endereco", nullable = false, length = 80)
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@NotBlank
	@Column(name = "estado", nullable = false, length = 3)
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@NotBlank
	@Column(name = "cidade", nullable = false, length = 80)
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "advogado_id", nullable = false)
	public Advogado getAdvogadoResponsavel() {
		return advogadoResponsavel;
	}

	public void setAdvogadoResponsavel(Advogado advogadoResponsavel) {
		this.advogadoResponsavel = advogadoResponsavel;
	}

	@NotBlank
	@Column(name = "juiz_responsavel", nullable = false, length = 80)
	public String getJuizResponsavel() {
		return juizResponsavel;
	}

	public void setJuizResponsavel(String juizResponsavel) {
		this.juizResponsavel = juizResponsavel;
	}

	@ManyToOne
	@JoinColumn(name = "estagiario_id", nullable = false)
	public Estagiario getEstagiario() {
		return estagiario;
	}

	public void setEstagiario(Estagiario estagiario) {
		this.estagiario = estagiario;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "processo_id", nullable = false)
	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 40)
	public TipoAudiencia getTipoAudiencia() {
		return tipoAudiencia;
	}

	public void setTipoAudiencia(TipoAudiencia tipoAudiencia) {
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

	@Override
	public String toString() {
		return "Audiencia [id=" + id + ", dataAudiencia=" + dataAudiencia
				+ ", horaAudiencia=" + horaAudiencia + ", endereco=" + endereco
				+ ", estado=" + estado + ", cidade=" + cidade
				+ ", advogadoResponsavel=" + advogadoResponsavel
				+ ", juizResponsavel=" + juizResponsavel + ", estagiario="
				+ estagiario + ", processo=" + processo + ", tipoAudiencia="
				+ tipoAudiencia + "]";
	}

}
