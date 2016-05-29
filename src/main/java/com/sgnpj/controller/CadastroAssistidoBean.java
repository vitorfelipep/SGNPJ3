package com.sgnpj.controller;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Transient;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.hibernate.Session;
import org.hibernate.validator.constraints.NotEmpty;
import org.primefaces.context.RequestContext;

import com.sgnpj.model.Advogado;
import com.sgnpj.model.AreaAtuacao;
import com.sgnpj.model.Assistido;
import com.sgnpj.model.AssistidoContraParte;
import com.sgnpj.model.Atendimento;
import com.sgnpj.model.Cidade;
import com.sgnpj.model.EstadoCivilAssistido;
import com.sgnpj.model.Estagiario;
import com.sgnpj.model.Processo;
import com.sgnpj.model.SituacaoAssitido;
import com.sgnpj.model.StatusAtendimento;
import com.sgnpj.model.Telefone;
import com.sgnpj.model.TipoEndereco;
import com.sgnpj.model.TipoPessoa;
import com.sgnpj.model.Triagem;
import com.sgnpj.model.Usuario;
import com.sgnpj.repository.Advogados;
import com.sgnpj.repository.Atendimentos;
import com.sgnpj.repository.Dao;
import com.sgnpj.repository.RepositoryCidades;
import com.sgnpj.repository.Telefones;
import com.sgnpj.security.Seguranca;
import com.sgnpj.security.UsuarioSistema;
import com.sgnpj.service.CadastrarAssisistidoService;
import com.sgnpj.service.CadastrarAssistidoParteContraria;
import com.sgnpj.service.CadastrarAtendimentoService;
import com.sgnpj.service.CadastrarPessoaFisicaService;
import com.sgnpj.service.CadastrarPessoaJuridicaService;
import com.sgnpj.service.CadastrarTelefoneService;
import com.sgnpj.service.CadastrarTriagemService;
import com.sgnpj.service.EstagiarioService;
import com.sgnpj.util.jsf.FacesUtil;
import com.sgnpj.util.report.ExecutorRelatorio;

@Named
@ViewScoped
public class CadastroAssistidoBean extends Dao implements Serializable {

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
	private CadastrarTelefoneService telefoneService;

	@Inject
	private CadastrarAtendimentoService cadastrarAtendimentoService;

	@Inject
	private EstagiarioService estagiarioService;

	@Produces
	@AssistidoEdicao
	private Assistido assistido;

	@Inject
	private static Assistido assistidoRelatorio;

	@Inject
	private Advogado advogado;

	@Inject
	private Estagiario estagiario;

	@Inject
	private Advogados advogados;

	@Inject
	private Atendimento atendimento;

	@Inject
	private Atendimentos atendimentos;

	@Inject
	private Telefones telefones;

	@Inject
	private AssistidoContraParte contraParte;

	@Inject
	private Telefone telefone;

	// Injeção para emitir relatórios do jasper Reports
	@Inject
	private FacesContext facesContext;

	@Inject
	private HttpServletResponse response;

	@Inject
	private EntityManager manager;

	private String tipoAssistido;

	private Date dataAtendimento;

	// Desabilita quando inicia o atendimento, após atendimento salvo ele têm
	// que habilitar
	// o botão finalizar atendimento
	private Boolean controleBotaoFinalizar = true;

	private Boolean atendimentoFinalizado = false;

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

	@Inject
	private RepositoryCidades repositoryCidades;

	private List<Cidade> cidades;

	public CadastroAssistidoBean() {

		this.assistido = new Assistido();
		this.assistido.setTriagem(new Triagem());
		this.usuarioLogado = new Seguranca();
		this.setTipoPessoaFisica(true);
		this.setTipoPessoaFisicaContraParte(true);
		this.setTipoPessoaJuridica(false);
		this.dataAtendimento = new Date();
		this.cidades = new ArrayList<Cidade>();

	}

	public void inicializar() {
		if (FacesUtil.isNotPostBack()) {
			if (this.atendimento.getId() == null) {
				this.atendimento
						.setStatusAtendimento(StatusAtendimento.EM_ATENDIMENTO);
			}
		}

		if (assistido.getId() != null) {

			this.telefone = telefones.porIdAssistido(assistido);

			this.atendimento = atendimentos.PorIdAssistido(this.assistido,
					this.contraParte);
			if (this.atendimento.getId() == null) {
				FacesUtil
						.addInfoMesage("Este assistido não está vinculado a nenhum atendimento!");
			} else {
				if (this.atendimento.getStatusAtendimento().equals(
						StatusAtendimento.EM_APROVACAO)) {
					this.controleBotaoFinalizar = false;
				} else {
					this.atendimentoFinalizado = true;
				}

				if (this.atendimento.getAssistido().getPessoaFisica() == null) {
					this.tipoPessoa = TipoPessoa.JURIDICA;
				} else {
					this.tipoPessoa = TipoPessoa.FISICA;
				}

				if (this.contraParte.getId() != null) {
					if (this.contraParte.getPessoaFisica() != null) {
						this.setTipoPessoaFisicaContraParte(true);
						this.setTipoPessoaJuridicaContraParte(false);
					} else {
						this.setTipoPessoaFisicaContraParte(false);
						this.setTipoPessoaJuridicaContraParte(true);
					}
				}

				this.dataAtendimento = atendimento.getDataAtendimento();
				this.tipoAssistido = atendimento.getAssistido()
						.getTipoAssistido();
				this.advogado = atendimento.getAdvogado();
			}
		}

	}

	public void salvar() {
		UsuarioSistema usuario = this.usuarioLogado.getUsuarioLogadoNoSistema();
		Usuario usu = usuario.getUsuario();

		System.out.println("Usuario sessão: " + usu.getNome());
		System.out.println("Assistido: " + this.assistido.getNome());
		System.out.println("Advogado: " + this.advogado.getUsuario().getNome());

		if (usu.getEstagiario() != null) {
			this.estagiario = estagiarioService.porIdEstagiario(usu
					.getEstagiario().getId());
		}

		if (this.assistido.getId() == null) {
			try {
				this.assistido.setTipoAssistido(tipoAssistido);

				this.contraParte.setTipoAssistido("REU");
				this.contraParte.setTipoEndereco(TipoEndereco.CASA_PROPRIA);

				// this.assistido.adicionarAssistidoContraParte(contraParte);
				if (tipoPessoa.getDescricao().equals("Fisíca")) {
					// Salvando a pessoa fisica
					if (assistido.getPessoaFisica() != null) {
						this.assistido.setPessoaFisica(pessoaFisicaService
								.salvar(assistido.getPessoaFisica()));
						this.assistido.setPessoaJuridica(null);
					}
				} else {
					if (assistido.getPessoaJuridica() != null) {
						this.assistido.setPessoaJuridica(pessoaJuridicaService
								.salvar(assistido.getPessoaJuridica()));
						this.assistido.setPessoaFisica(null);
					}
				}

				if (assistido.getTriagem() != null) {
					this.assistido.setTriagem(triagemService.salvar(assistido
							.getTriagem()));
				}

				if (contraParte.getPessoaFisica().getCpf() != null) {
					this.contraParte.setPessoaFisica(pessoaFisicaService
							.salvar(contraParte.getPessoaFisica()));
					this.contraParte.setPessoaJuridica(null);
				} else {
					this.contraParte.setPessoaJuridica(pessoaJuridicaService
							.salvar(contraParte.getPessoaJuridica()));
					this.contraParte.setPessoaFisica(null);
				}

				// Salvo o assisitido
				this.assistido = assistidoService.salvar(assistido);

				this.telefone.setAssistido(assistido);

				this.telefone = telefoneService.salvar(this.telefone);

				this.contraParte.setAssistidoAutor(assistido);

				// Coloco relaciono os ultimos objetos
				if (this.assistido.getPessoaFisica() != null) {
					this.assistido.getPessoaFisica().setAssitido(assistido);
					this.assistido.setPessoaFisica(pessoaFisicaService
							.salvar(this.assistido.getPessoaFisica()));
				} else {
					this.assistido.getPessoaJuridica().setAssistido(assistido);
					this.assistido.setPessoaJuridica(pessoaJuridicaService
							.salvar(this.assistido.getPessoaJuridica()));
				}
				this.assistido.getTriagem().setAssistidotriagem(assistido);
				this.assistido.setTriagem(triagemService.salvar(this.assistido
						.getTriagem()));

				this.contraParte = parteContrariaService.salvar(contraParte);

				this.assistido.adicionarAssistidoContraParte(contraParte);

				this.assistido = assistidoService.salvar(assistido);

				// String atendimento = atendimento.getAtendimentoRelato();
				this.atendimento.setAdvogado(advogado);

				if (this.estagiario.getNome() == null) {
					this.estagiario = estagiarioService.porIdEstagiario(1L);
					this.atendimento.setEstagiario(estagiario);
				} else {
					this.atendimento.setEstagiario(estagiario);
				}
				this.atendimento.setAssistido(assistido);
				this.atendimento.setContraParte(contraParte);

				if (this.atendimento.getProcessos() == null) {
					List<Processo> p = new ArrayList<Processo>();
					this.atendimento.setProcessos(p);
				}

				this.atendimento.setDataAtendimento(new Date());

				this.atendimento
						.setStatusAtendimento(StatusAtendimento.EM_APROVACAO);

				this.atendimento = cadastrarAtendimentoService
						.salvar(atendimento);

				System.out.println(new SimpleDateFormat("dd/MM/yyyy")
						.format(atendimento.getDataAtendimento()));

				controleBotaoFinalizar = this.atendimento
						.isFinalizarAtendimento();

				FacesUtil.addInfoMesage("O Assistido Sr(a) "
						+ this.assistido.getNome() + " foi salvo com sucesso!");

			} finally {
				// Mostra a mensagem final
				showMessage();
				// limparForm();
			}
		} else {
			try {
				this.assistido.setTriagem(triagemService.salvar(this.assistido
						.getTriagem()));
				// Verifica se é pessoa fisica ou juridica e salva para efetuar
				// a edição
				if (this.assistido.getPessoaFisica().getId() != null) {
					this.assistido.setPessoaFisica(pessoaFisicaService
							.salvar(this.assistido.getPessoaFisica()));
				} else {
					this.assistido.setPessoaJuridica(pessoaJuridicaService
							.salvar(this.assistido.getPessoaJuridica()));
				}

				this.assistido = this.assistidoService.salvar(assistido);
				this.assistido.setTriagem(triagemService.salvar(this.assistido
						.getTriagem()));
				// Verifica se é pessoa fisica ou juridica e salva para efetuar
				// a edição
				if (this.contraParte.getPessoaFisica().getId() != null) {
					this.contraParte.setPessoaFisica(pessoaFisicaService
							.salvar(this.contraParte.getPessoaFisica()));
				} else {
					this.contraParte.setPessoaJuridica(pessoaJuridicaService
							.salvar(this.contraParte.getPessoaJuridica()));
				}
				this.contraParte = parteContrariaService.salvar(contraParte);
				this.atendimento.setAssistido(assistido);
				this.atendimento.setContraParte(contraParte);
				this.telefone = telefoneService.salvar(this.telefone);

				this.atendimento = cadastrarAtendimentoService
						.salvar(atendimento);

				FacesUtil.addInfoMesage("O Assistido Sr(a) "
						+ this.assistido.getNome()
						+ " foi atualizado com sucesso!");
			} finally {
				// limparForm();
			}

		}
	}

	public void finalizarAtendimento() {

		try {
			this.atendimento
					.setStatusAtendimento(StatusAtendimento.EM_ATENDIMENTO);
			this.atendimentoFinalizado = true;
			this.assistido.setSituacao(SituacaoAssitido.APROVADO);
			this.assistido = this.assistidoService.salvar(assistido);
			this.contraParte = parteContrariaService.salvar(contraParte);
			this.atendimento = cadastrarAtendimentoService.salvar(atendimento);

			FacesUtil.addInfoMesage("Atendimento finalizado com sucesso!");
		} finally {

			this.controleBotaoFinalizar = true;
			// limparForm();
		}
	}

	public void gerarRelatorios() throws InterruptedException, IOException {

		Session session = manager.unwrap(Session.class);
		Map<String, Object> parametros = new HashMap<>();

		if (this.assistido != null) {
			if (this.assistido.getId() != null) {
				CadastroAssistidoBean.assistidoRelatorio = assistido;
			}
		}

		if (this.assistido.getId() != null) {
			parametros.put("idAssistido", this.assistido.getId());

			ExecutorRelatorio executor = new ExecutorRelatorio(
					"/relatorios/RelatorioHipossuficiencia.jasper",
					this.response, parametros, "Relatorio Hipossuficiencia.pdf");

			session.doWork(executor);
			// session.doWork(executor2);
			if (executor.isRelatorioGerado()) {
				// segundoRelatorio();
				// facesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8081/SGNPJ/Relatorio/Procuracao.pdf");

				facesContext.getApplication().getStateManager()
						.saveView(facesContext);

				facesContext.renderResponse();

				facesContext.responseComplete();

				// Thread.sleep(10000);
			} else {
				FacesUtil
						.addErrorMesage("A execução do relatório não retornou dados.");
			}
		}

		limparForm();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void segundoRelatorio() {
		try {

			open();
			HashMap parametros = new HashMap();

			parametros.put("idAssistido",
					CadastroAssistidoBean.assistidoRelatorio.getId());

			// Saida Teste
			JasperPrint print = JasperFillManager
					.fillReport(
							"C:/Users/Vitor/workspace/SGNPJ/src/main/resources/relatorios/RelatorioProcuracao.jasper",
							parametros, con);
			JasperExportManager
					.exportReportToPdfFile(print,
							"C:/Users/Vitor/workspace/SGNPJ/src/main/webapp/Relatorio/Procuracao.pdf");

			Thread.sleep(5000);
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							"http://localhost:8081/SGNPJ/Relatorio/Procuracao.pdf");

			// Link de Produção
			// JasperPrint print =
			// JasperFillManager.fillReport("/var/lib/tomcat7/webapps/saidasNogueiraV1/Relatorio/PdfSaidaNogueira.jasper",
			// parametros);
			// JasperExportManager.exportReportToPdfFile(print,
			// "/var/lib/tomcat7/webapps/saidasNogueiraV1/Relatorio/PdfSaidaNogueira.pdf");
			//
			//
			//
			// Thread.sleep(5000);
			// FacesContext.getCurrentInstance().getExternalContext().redirect("http://192.168.15.164:8080/saidasNogueiraV1/Relatorio/PdfSaidaNogueira.pdf");

			close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void limparForm() {
		this.telefone = new Telefone();
		this.advogado = new Advogado();
		this.tipoPessoa = null;
		this.assistido = new Assistido();
		this.contraParte = new AssistidoContraParte();
		this.atendimento = new Atendimento();

	}

	// Atualizar o advogado quando ele for emitido, para o novo status
	// Essa anotation observes do javax é o observador do evento que fará a
	// atualização do novo objeto pedido.
	public void assistidoAlterado(@Observes AssistidoAlteradoEvent event) {
		this.assistido = event.getAssistido();
	}

	public List<Advogado> completarAdvogado(String nome) {
		List<Advogado> advogado = this.advogados.porNome(nome);
		return advogado;
	}

	public void showMessage() {
		FacesMessage message = new FacesMessage(
				FacesMessage.SEVERITY_INFO,
				"Sucesso!",
				"Caso queira finalizar o atendimento, clique no botão finalizar atendimento para finalizar o atendimento!");

		RequestContext.getCurrentInstance().showMessageInDialog(message);
	}

	public List<Cidade> carregarCidades() {
		return this.cidades = repositoryCidades
				.filtradoPorEstado(this.assistido.getEstado());
	}

	public boolean isEditando() {
		return this.assistido.getId() != null;
	}

	public boolean isNaoEditando() {
		return this.assistido.getId() == null;
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

	public Boolean getControleBotaoFinalizar() {
		return controleBotaoFinalizar;
	}

	public void setControleBotaoFinalizar(Boolean controleBotaoFinalizar) {
		this.controleBotaoFinalizar = controleBotaoFinalizar;
	}

	public Boolean getAtendimentoFinalizado() {
		return atendimentoFinalizado;
	}

	public void setAtendimentoFinalizado(Boolean atendimentoFinalizado) {
		this.atendimentoFinalizado = atendimentoFinalizado;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	/*
	 * -------------------------------------------------------- Fim Getters and
	 * Setters ------------------------------------------------------------
	 */
}
