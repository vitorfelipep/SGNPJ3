package com.sgnpj.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.sgnpj.model.Advogado;
import com.sgnpj.model.AreaAtuacao;
import com.sgnpj.model.Assistido;
import com.sgnpj.model.Atendimento;
import com.sgnpj.model.TipoPessoa;
import com.sgnpj.repository.Advogados;
import com.sgnpj.service.CadastrarAssisistidoService;

@Named
@ViewScoped
public class CadastroAssistidoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastrarAssisistidoService assistidoService;

	@Produces
	@AssistidoEdicao
	private Assistido assistido;

	@Inject
	private Advogado advogado;

	@Inject
	private Advogados advogados;

	@Inject
	private Atendimento atendimento;

	private String tipoPessoa;

	private String tipoAssistido;

	private Date dataAtendimento;

	public CadastroAssistidoBean() {
		this.dataAtendimento = new Date();
	}

	public void salvar() {
		System.out.println(tipoPessoa);
	}

	public List<Advogado> completarAdvogado(String nome) {
		List<Advogado> advogado = this.advogados.porNome(nome);
		return advogado;
	}

	// Lista de enums do tipo area de atuação
	public TipoPessoa[] getTipoPessoa() {
		return TipoPessoa.values();
	}

	public AreaAtuacao[] getAreaAtendimento() {
		return AreaAtuacao.values();
	}

	/*
	 * --------------------------------------------------------Getters and
	 * Setters ------------------------------------------------------------
	 */
	public CadastrarAssisistidoService getAssistidoService() {
		return assistidoService;
	}

	public void setAssistidoService(CadastrarAssisistidoService assistidoService) {
		this.assistidoService = assistidoService;
	}

	public Assistido getAssistido() {
		return assistido;
	}

	public void setAssistido(Assistido assistido) {
		this.assistido = assistido;
	}

	public Advogado getAdvogado() {
		return advogado;
	}

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	public void setAdvogado(Advogado advogado) {
		this.advogado = advogado;
	}
	
	
	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public Date getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(Date dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	@NotEmpty
	public String getTipoAssistido() {
		return tipoAssistido;
	}

	public void setTipoAssistido(String tipoAssistido) {
		this.tipoAssistido = tipoAssistido;
	}

	/*
	 * -------------------------------------------------------- Fim Getters and
	 * Setters ------------------------------------------------------------
	 */
}
