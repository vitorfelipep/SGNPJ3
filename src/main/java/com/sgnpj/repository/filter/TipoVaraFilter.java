package com.sgnpj.repository.filter;

import java.io.Serializable;
import java.util.Date;

import com.sgnpj.model.Comarca;

public class TipoVaraFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idTipoVara;
	private Date dataInicial;
	private Date dataFinal;
	private String nomeVara;
	private Comarca comarca;

	public TipoVaraFilter() {
		this.comarca = new Comarca();
	}

	public TipoVaraFilter(Long idTipoVara, Date dataInicial, Date dataFinal,
			String nomeVara, Comarca comarca) {
		this.idTipoVara = idTipoVara;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.nomeVara = nomeVara;
		this.comarca = comarca;
	}

	public Long getIdTipoVara() {
		return idTipoVara;
	}

	public void setIdTipoVara(Long idTipoVara) {
		this.idTipoVara = idTipoVara;
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

	public String getNomeVara() {
		return nomeVara;
	}

	public void setNomeVara(String nomeVara) {
		this.nomeVara = nomeVara;
	}

	public Comarca getComarca() {
		return comarca;
	}

	public void setComarca(Comarca comarca) {
		this.comarca = comarca;
	}

	@Override
	public String toString() {
		return "TipoVaraFilter [idTipoVara=" + idTipoVara + ", dataInicial="
				+ dataInicial + ", dataFinal=" + dataFinal + ", nomeVara="
				+ nomeVara + ", comarca=" + comarca + "]";
	}

}
