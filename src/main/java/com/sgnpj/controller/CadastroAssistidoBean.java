package com.sgnpj.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.sgnpj.model.AssistidoContraParte;
import com.sgnpj.model.Atendimento;
import com.sgnpj.model.EstadoCivilAssistido;
import com.sgnpj.model.Estagiario;
import com.sgnpj.model.Processo;
import com.sgnpj.model.SituacaoAssitido;
import com.sgnpj.model.Telefone;
import com.sgnpj.model.TipoEndereco;
import com.sgnpj.model.TipoPessoa;
import com.sgnpj.model.Triagem;
import com.sgnpj.model.Usuario;
import com.sgnpj.repository.Advogados;
import com.sgnpj.security.Seguranca;
import com.sgnpj.security.UsuarioSistema;
import com.sgnpj.service.CadastrarAssisistidoService;
import com.sgnpj.service.CadastrarAssistidoParteContraria;
import com.sgnpj.service.CadastrarAtendimentoService;
import com.sgnpj.service.CadastrarPessoaFisicaService;
import com.sgnpj.service.CadastrarPessoaJuridicaService;
import com.sgnpj.service.CadastrarTriagemService;
import com.sgnpj.service.EstagiarioService;
import com.sgnpj.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroAssistidoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastrarAssisistidoService assistidoService;

	@Inject
	private CadastrarPessoaFisicaService pessoaFisicaService;

	@Inject
	private CadastrarPessoaJuridicaService pessoaJuridicaService;

	@Inject
	private CadastrarAssistidoParteContraria parteContrariaService;

	@Inject
	private CadastrarTriagemService triagemService;

	@Inject
	private CadastrarAtendimentoService cadastrarAtendimentoService;
	
	@Inject
	private EstagiarioService estagiarioService;

	@Produces
	@AssistidoEdicao
	private Assistido assistido;

	@Inject
	private Advogado advogado;

	@Inject
	private Estagiario estagiario;

	@Inject
	private Advogados advogados;

	@Inject
	private Atendimento atendimento;

	@Inject
	private AssistidoContraParte contraParte;

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
		this.assistido.setTriagem(new Triagem());
	}

	public void salvar() {
		UsuarioSistema usuario = this.usuarioLogado.getUsuarioLogadoNoSistema();
		Usuario usu = usuario.getUsuario();
		
		System.out.println("Usuario sessão: " + usu.getNome());
		System.out.println("Assistido: " + this.assistido.getNome());
		System.out.println("Advogado: " + this.advogado.getUsuario().getNome());
		
		if(usu.getEstagiario() != null){
			this.estagiario = estagiarioService.porIdEstagiario(usu.getId());
		}
		
		this.assistido.setTipoAssistido(tipoAssistido);
		
		this.contraParte.setTipoAssistido("REU");
		this.contraParte.setTipoEndereco(TipoEndereco.CASA_PROPRIA);

		//this.assistido.adicionarAssistidoContraParte(contraParte);
		if(tipoPessoa.getDescricao().equals("Fisíca")){
			// Salvando a pessoa fisica
			if (assistido.getPessoaFisica() != null) {
				this.assistido.setPessoaFisica(pessoaFisicaService.salvar(assistido.getPessoaFisica()));
				this.assistido.setPessoaJuridica(null);
			}
		}else{
			if (assistido.getPessoaJuridica() != null) {
				this.assistido.setPessoaJuridica(pessoaJuridicaService.salvar(assistido.getPessoaJuridica()));
				this.assistido.setPessoaFisica(null);
			}
		}
		
		if(assistido.getTriagem() != null){
			this.assistido.setTriagem(triagemService.salvar(assistido.getTriagem()));
		}
		
		if(contraParte.getPessoaFisica() != null){
			this.contraParte.setPessoaFisica(pessoaFisicaService.salvar(contraParte.getPessoaFisica()));
			this.contraParte.setPessoaJuridica(null);
		}else{
			this.contraParte.setPessoaJuridica(pessoaJuridicaService.salvar(contraParte.getPessoaJuridica()));
			this.contraParte.setPessoaFisica(null);
		}
		
		//Salvo o assisitido 
		this.assistido = assistidoService.salvar(assistido);
		
		this.contraParte.setAssistidoAutor(assistido);
		
		//Coloco relaciono os ultimos objetos
		if(this.assistido.getPessoaFisica() != null){
			this.assistido.getPessoaFisica().setAssitido(assistido);
			this.assistido.setPessoaFisica(pessoaFisicaService.salvar(this.assistido.getPessoaFisica()));
		}else{
			this.assistido.getPessoaJuridica().setAssistido(assistido);
			this.assistido.setPessoaJuridica(pessoaJuridicaService.salvar(this.assistido.getPessoaJuridica()));
		}
		this.assistido.getTriagem().setAssistidotriagem(assistido);
		this.assistido.setTriagem(triagemService.salvar(this.assistido.getTriagem()));
		
		this.contraParte = parteContrariaService.salvar(contraParte);
		
		this.assistido.adicionarAssistidoContraParte(contraParte);
		
		this.assistido = assistidoService.salvar(assistido);
		
		//String atendimento = atendimento.getAtendimentoRelato();
		this.atendimento.setAdvogado(advogado);
		if(this.atendimento.getEstagiario() == null){
			this.atendimento.setEstagiario(null);
		}
		this.atendimento.setAssistido(assistido);
		
		if(this.atendimento.getProcessos() == null){
			List<Processo> p = new ArrayList<Processo>();
			this.atendimento.setProcessos(p);
		}
		
		this.atendimento.setDataAtendimento(new Date());
		
		this.atendimento = cadastrarAtendimentoService.salvar(atendimento);
		
		System.out.println(new SimpleDateFormat("dd/MM/yyyy").format(atendimento.getDataAtendimento()));

		FacesUtil.addInfoMesage("O Assistido Sr(a) " + this.assistido.getNome() + " Salvo com sucesso!");
		FacesUtil.addInfoMesage("O inicio do atendimento iniciado!");
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

	public TipoEndereco[] getTipoEnderecos() {
		return TipoEndereco.values();
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

	public CadastrarPessoaFisicaService getPessoaFisicaService() {
		return pessoaFisicaService;
	}

	public void setPessoaFisicaService(
			CadastrarPessoaFisicaService pessoaFisicaService) {
		this.pessoaFisicaService = pessoaFisicaService;
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

	public AssistidoContraParte getContraParte() {
		return contraParte;
	}

	public void setContraParte(AssistidoContraParte contraParte) {
		this.contraParte = contraParte;
	}

	public CadastrarAssistidoParteContraria getParteContrariaService() {
		return parteContrariaService;
	}

	public void setParteContrariaService(
			CadastrarAssistidoParteContraria parteContrariaService) {
		this.parteContrariaService = parteContrariaService;
	}

	public CadastrarPessoaJuridicaService getPessoaJuridicaService() {
		return pessoaJuridicaService;
	}

	public void setPessoaJuridicaService(
			CadastrarPessoaJuridicaService pessoaJuridicaService) {
		this.pessoaJuridicaService = pessoaJuridicaService;
	}

	public CadastrarTriagemService getTriagemService() {
		return triagemService;
	}

	public void setTriagemService(CadastrarTriagemService triagemService) {
		this.triagemService = triagemService;
	}

	public CadastrarAtendimentoService getCadastrarAtendimentoService() {
		return cadastrarAtendimentoService;
	}

	public void setCadastrarAtendimentoService(
			CadastrarAtendimentoService cadastrarAtendimentoService) {
		this.cadastrarAtendimentoService = cadastrarAtendimentoService;
	}

	public Estagiario getEstagiario() {
		return estagiario;
	}

	public void setEstagiario(Estagiario estagiario) {
		this.estagiario = estagiario;
	}

	/*
	 * -------------------------------------------------------- Fim Getters and
	 * Setters ------------------------------------------------------------
	 */
}