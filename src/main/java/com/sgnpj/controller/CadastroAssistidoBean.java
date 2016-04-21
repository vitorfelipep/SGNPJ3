package com.sgnpj.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

import com.sgnpj.model.Advogado;
import com.sgnpj.model.AreaAtuacao;
import com.sgnpj.model.Assistido;
import com.sgnpj.model.Atendimento;
import com.sgnpj.model.EstadoCivilAssistido;
import com.sgnpj.model.SituacaoAssitido;
import com.sgnpj.model.Telefone;
import com.sgnpj.model.TipoPessoa;
import com.sgnpj.model.Triagem;
import com.sgnpj.model.Usuario;
import com.sgnpj.repository.Advogados;
import com.sgnpj.security.Seguranca;
import com.sgnpj.security.UsuarioSistema;
import com.sgnpj.service.CadastrarAssisistidoService;
import com.sgnpj.util.jsf.FacesUtil;

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

	@Inject
	private Telefone telefone;

	private String tipoAssistido;

	private Date dataAtendimento;

	// para verificar qual o tipo de pessoa a ser atendida (fisica ou juridica)
	private TipoPessoa tipoPessoa;

	private TipoPessoa tipoPessoaContraParte;

	// Objeto responsavel por renderizar os campos de pessoa fisica
	private Boolean tipoPessoaFisica;
	// Objeto responsavel por renderizar os campos de pessoa juridica
	private Boolean tipoPessoaJuridica;

	// Objeto responsavel por renderizar os campos de pessoa fisica
	private Boolean tipoPessoaFisicaContraParte;
	// Objeto responsavel por renderizar os campos de pessoa juridica
	private Boolean tipoPessoaJuridicaContraParte;

	private Seguranca usuarioLogado;

	public CadastroAssistidoBean() {

		this.usuarioLogado = new Seguranca();
		this.setTipoPessoaFisica(true);
		this.setTipoPessoaFisicaContraParte(true);
		this.setTipoPessoaJuridica(false);
		this.dataAtendimento = new Date();
		this.assistido = new Assistido();
		this.assistido.setContraParte(new Assistido());
		this.assistido.setTriagem(new Triagem());
	}

	public void salvar() {
		UsuarioSistema usuario = this.usuarioLogado.getUsuarioLogadoNoSistema();
		Usuario usu = usuario.getUsuario();
		System.out.println(usu);
		System.out.println(this.assistido.getNome());
		assistido.setTipoAssistido(tipoAssistido);
		assistido.getContraParte().setTipoAssistido("REU");
		assistido.getContraParte().setSituacao(SituacaoAssitido.ATENDIDO);
		assistido.setSituacao(SituacaoAssitido.EM_APROVACAO);
		assistidoService.salvar(assistido);

		FacesUtil.addInfoMesage("Dados Salvo com sucesso!");
	}

	public List<Advogado> completarAdvogado(String nome) {
		List<Advogado> advogado = this.advogados.porNome(nome);
		return advogado;
	}

	// Lista de enums do tipo area de atuação
	public TipoPessoa[] getTiposPessoa() {
		return TipoPessoa.values();
	}

	// Lista de enums do tipo area de atuação
	public SituacaoAssitido[] getSituacaoAssistido() {
		return SituacaoAssitido.values();
	}

	public AreaAtuacao[] getAreaAtendimento() {
		return AreaAtuacao.values();
	}

	public EstadoCivilAssistido[] getEstadoCivil() {
		return EstadoCivilAssistido.values();
	}

	@Transient
	public void verificarTipoPessoa(ValueChangeEvent event) {

		String tipo = event.getNewValue().toString();

		if ("FISICA".equals(tipo)) {
			this.setTipoPessoaFisica(true);
			this.setTipoPessoaJuridica(false);
		} else {
			this.setTipoPessoaFisica(false);
			this.setTipoPessoaJuridica(true);
		}

	}

	@Transient
	public void verificarTipoPessoaContraParte(ValueChangeEvent event) {

		String tipo = event.getNewValue().toString();

		if ("FISICA".equals(tipo)) {
			this.setTipoPessoaFisicaContraParte(true);
			this.setTipoPessoaJuridicaContraParte(false);
		} else {
			this.setTipoPessoaFisicaContraParte(false);
			this.setTipoPessoaJuridicaContraParte(true);
		}

	}

	/*
	 * --------------------------------------------------------Getters and
	 * Setters ------------------------------------------------------------
	 */

	public CadastrarAssisistidoService getAssistidoService() {
		return assistidoService;
	}

	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
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

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public Boolean getTipoPessoaFisica() {
		return tipoPessoaFisica;
	}

	public void setTipoPessoaFisica(Boolean tipoPessoaFisica) {
		this.tipoPessoaFisica = tipoPessoaFisica;
	}

	public Boolean getTipoPessoaJuridica() {
		return tipoPessoaJuridica;
	}

	public void setTipoPessoaJuridica(Boolean tipoPessoaJuridica) {
		this.tipoPessoaJuridica = tipoPessoaJuridica;
	}

	public Boolean getTipoPessoaFisicaContraParte() {
		return tipoPessoaFisicaContraParte;
	}

	public void setTipoPessoaFisicaContraParte(
			Boolean tipoPessoaFisicaContraParte) {
		this.tipoPessoaFisicaContraParte = tipoPessoaFisicaContraParte;
	}

	public Boolean getTipoPessoaJuridicaContraParte() {
		return tipoPessoaJuridicaContraParte;
	}

	public void setTipoPessoaJuridicaContraParte(
			Boolean tipoPessoaJuridicaContraParte) {
		this.tipoPessoaJuridicaContraParte = tipoPessoaJuridicaContraParte;
	}

	public Seguranca getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Seguranca usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public TipoPessoa getTipoPessoaContraParte() {
		return tipoPessoaContraParte;
	}

	public void setTipoPessoaContraParte(TipoPessoa tipoPessoaContraParte) {
		this.tipoPessoaContraParte = tipoPessoaContraParte;
	}

	/*
	 * -------------------------------------------------------- Fim Getters and
	 * Setters ------------------------------------------------------------
	 */
}
