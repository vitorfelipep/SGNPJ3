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

import com.sgnpj.model.Advogado;
import com.sgnpj.model.AreaAtuacao;
import com.sgnpj.model.Assistido;
import com.sgnpj.model.AssistidoContraParte;
import com.sgnpj.model.Atendimento;
import com.sgnpj.model.EstadoCivilAssistido;
import com.sgnpj.model.Estagiario;
import com.sgnpj.model.StatusAtendimento;
import com.sgnpj.model.TipoEndereco;
import com.sgnpj.model.TipoPessoa;
import com.sgnpj.repository.Advogados;
import com.sgnpj.repository.AssistidoPartesContrarias;
import com.sgnpj.repository.Assistidos;
import com.sgnpj.repository.Estagiarios;
import com.sgnpj.service.CadastrarAssistidoParteContraria;
import com.sgnpj.service.CadastrarAtendimentoService;
import com.sgnpj.service.CadastrarPessoaFisicaService;
import com.sgnpj.service.CadastrarPessoaJuridicaService;
import com.sgnpj.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroAtendimentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastrarAtendimentoService cadastrarAtendimentoService;
	
	@Inject
	private CadastrarAssistidoParteContraria cadastrarParteContrariaService;
	
	@Inject
	private CadastrarPessoaFisicaService pessoaFisicaService;

	@Inject
	private CadastrarPessoaJuridicaService pessoaJuridicaService;

	@Inject
	private Assistidos assistidos;

	@Inject
	private Advogados advogados;

	@Inject
	private Estagiarios estagiarios;

	@Inject
	private AssistidoPartesContrarias contraPartes;

	@Produces
	@EdicaoAtendimento
	private Atendimento atendimento;

	@Inject
	private Assistido assistido;

	@Inject
	private Estagiario estagiario;

	@Inject
	private Advogado advogado;

	@Inject
	private AssistidoContraParte contraParte;

	private TipoPessoa tipoPessoaContraParte;

	// Objeto responsavel por renderizar os campos de pessoa fisica
	private Boolean tipoPessoaFisicaContraParte;
	// Objeto responsavel por renderizar os campos de pessoa juridica
	private Boolean tipoPessoaJuridicaContraParte;

	private Boolean painelCadastroNewContraParte;

	private Boolean renderizarAutoCompleteContraParte;

	// opcaoContraParte para ver se cadastro um novo
	// assistido contra-parte ou não.
	private String opcaoContraParte;

	public CadastroAtendimentoBean() {
		this.atendimento = new Atendimento();
		this.atendimento.setDataAtendimento(new Date());
		this.setTipoPessoaFisicaContraParte(true);
		this.setRenderizarAutoCompleteContraParte(true);
		this.setPainelCadastroNewContraParte(false);

	}

	public void inicializar() {

		if (this.contraParte.getId() != null) {
			if (this.contraParte.getPessoaFisica() != null) {
				this.setTipoPessoaFisicaContraParte(true);
				this.setTipoPessoaJuridicaContraParte(false);
			} else {
				this.setTipoPessoaFisicaContraParte(false);
				this.setTipoPessoaJuridicaContraParte(true);
			}
		}
	}

	public void salvarAssistido() {
		
		try{
			System.out.println(this.assistido);
			this.contraParte.setAssistidoAutor(assistido);
			this.atendimento.setAdvogado(advogado);
			this.atendimento.setAssistido(assistido);
			if(this.contraParte.getId() != null){
				this.atendimento.setContraParte(contraParte);
			}else{
				if(this.contraParte.getTipoAssistido() == null){
					this.contraParte.setTipoAssistido("REU");
					this.contraParte.setTipoEndereco(TipoEndereco.CASA_PROPRIA);
					if (contraParte.getPessoaFisica().getCpf() != null) {
						this.contraParte.setPessoaFisica(pessoaFisicaService
								.salvar(contraParte.getPessoaFisica()));
						this.contraParte.setPessoaJuridica(null);
					} else {
						this.contraParte.setPessoaJuridica(pessoaJuridicaService
								.salvar(contraParte.getPessoaJuridica()));
						this.contraParte.setPessoaFisica(null);
					}
				}
				this.contraParte = cadastrarParteContrariaService.salvar(contraParte);
				this.atendimento.setContraParte(contraParte);
			}
			this.atendimento.setEstagiario(estagiario);
			
			this.atendimento = cadastrarAtendimentoService.salvar(atendimento);
			
			FacesUtil.addInfoMesage("Atendimento cadastrado com sucesso!");
			
		}finally{
			limparForm();
		}
	}

	private void limparForm() {
		
		this.assistido = new Assistido();
		this.advogado = new Advogado();
		this.contraParte = new AssistidoContraParte();
		this.atendimento = new Atendimento();
		
	}

	public List<Assistido> completarAssistido(String nome) {
		List<Assistido> assistidos = this.assistidos.porNome(nome);
		return assistidos;
	}

	public List<Advogado> completarAdvogado(String nome) {
		List<Advogado> advogado = this.advogados.porNome(nome);
		return advogado;
	}

	public List<Estagiario> completarEstagiario(String nome) {
		List<Estagiario> estagiario = this.estagiarios.porNome(nome);
		return estagiario;
	}

	public List<AssistidoContraParte> completarContraParte(String nome) {
		List<AssistidoContraParte> contraPartes = this.contraPartes
				.porNome(nome);
		return contraPartes;
	}

	public AreaAtuacao[] getAreaAtendimento() {
		return AreaAtuacao.values();
	}

	public StatusAtendimento[] getStatusAtendimento() {
		return StatusAtendimento.values();
	}

	// Lista de enums do tipo area de atuação
	public TipoPessoa[] getTiposPessoa() {
		return TipoPessoa.values();
	}

	public EstadoCivilAssistido[] getEstadoCivil() {
		return EstadoCivilAssistido.values();
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

	@Transient
	public void verificarOpcaoContraParte(ValueChangeEvent event) {

		String opcao = event.getNewValue().toString();

		if ("SIM".equals(opcao)) {
			setRenderizarAutoCompleteContraParte(false);
			setPainelCadastroNewContraParte(true);
			System.out.println(opcao);
		} else {
			setRenderizarAutoCompleteContraParte(true);
			setPainelCadastroNewContraParte(false);
			System.out.println(opcao);
		}

	}

	/*
	 * ------------------------ Getters and Setters
	 * ------------------------------------------------
	 */

	public CadastrarAtendimentoService getCadastrarAtendimentoService() {
		return cadastrarAtendimentoService;
	}

	public void setCadastrarAtendimentoService(
			CadastrarAtendimentoService cadastrarAtendimentoService) {
		this.cadastrarAtendimentoService = cadastrarAtendimentoService;
	}

	public Assistidos getAssistidos() {
		return assistidos;
	}

	public void setAssistidos(Assistidos assistidos) {
		this.assistidos = assistidos;
	}

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	public Assistido getAssistido() {
		return assistido;
	}

	public void setAssistido(Assistido assistido) {
		this.assistido = assistido;
	}

	public Estagiario getEstagiario() {
		return estagiario;
	}

	public void setEstagiario(Estagiario estagiario) {
		this.estagiario = estagiario;
	}

	public Advogado getAdvogado() {
		return advogado;
	}

	public void setAdvogado(Advogado advogado) {
		this.advogado = advogado;
	}

	public TipoPessoa getTipoPessoaContraParte() {
		return tipoPessoaContraParte;
	}

	public void setTipoPessoaContraParte(TipoPessoa tipoPessoaContraParte) {
		this.tipoPessoaContraParte = tipoPessoaContraParte;
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

	public AssistidoContraParte getContraParte() {
		return contraParte;
	}

	public void setContraParte(AssistidoContraParte contraParte) {
		this.contraParte = contraParte;
	}

	public String getOpcaoContraParte() {
		return opcaoContraParte;
	}

	public void setOpcaoContraParte(String opcaoContraParte) {
		this.opcaoContraParte = opcaoContraParte;
	}

	public Boolean getPainelCadastroNewContraParte() {
		return painelCadastroNewContraParte;
	}

	public void setPainelCadastroNewContraParte(
			Boolean painelCadastroNewContraParte) {
		this.painelCadastroNewContraParte = painelCadastroNewContraParte;
	}

	public Boolean getRenderizarAutoCompleteContraParte() {
		return renderizarAutoCompleteContraParte;
	}

	public void setRenderizarAutoCompleteContraParte(
			Boolean renderizarAutoCompleteContraParte) {
		this.renderizarAutoCompleteContraParte = renderizarAutoCompleteContraParte;
	}

}
