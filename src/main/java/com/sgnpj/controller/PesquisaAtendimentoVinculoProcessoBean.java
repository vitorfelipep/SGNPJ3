package com.sgnpj.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.sgnpj.model.Atendimento;
import com.sgnpj.model.Processo;
import com.sgnpj.model.StatusProcesso;
import com.sgnpj.model.TipoVara;
import com.sgnpj.repository.Atendimentos;
import com.sgnpj.repository.Processos;
import com.sgnpj.repository.Varas;
import com.sgnpj.repository.filter.AtendimentoFilter;
import com.sgnpj.service.CadastrarProcessoService;
import com.sgnpj.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaAtendimentoVinculoProcessoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Atendimentos atendimentosRepository;

	@Inject
	private CadastrarProcessoService processoService;

	@Inject
	private Varas varas;
	
	@Inject
	private Processos processos;
	
	private List<Processo> processosJaExistente;

	@Produces
	@ProcessoEdicao
	private Processo processo;

	@Inject
	private AtendimentoFilter atendimentoFilter;

	@Inject
	private Atendimento atendimento;

	private List<Atendimento> atendimentos;

	private Boolean btnVincularProcesso = false;

	public PesquisaAtendimentoVinculoProcessoBean() {
		this.processo = new Processo();
		this.atendimentos = new ArrayList<Atendimento>();
		this.processosJaExistente = new ArrayList<Processo>();
	}

	public void pesquisar() {
		this.atendimentos = atendimentosRepository
				.filtradosParaProcesso(atendimentoFilter);
	}

	public void vincularProcesso() {

		if (this.processo.getNumeroProcesso() != null) {
			try {
				this.processo.setAtendimento(atendimento);
				System.out.println(this.processo.getNumeroProcesso());
				this.processo = processoService.salvar(processo);
			} finally {
				btnVincularProcesso = true;
				FacesUtil.addInfoMesage("Processo vinculado ao atendimento "
						+ this.atendimento.getId() + " com sucesso!");
			}
		} else {
			FacesUtil.addWarningMesage("Numero do processo é obrigatório!");
		}
	}
	
	//Verifica se o atendimento já possui processo.
	public void verificarAtendimento(Long id){
		this.processosJaExistente = processos.findAll();
		if(processosJaExistente.size() > 0){
			for(Processo p : processosJaExistente){
				if(p.getId().equals(id)){
					this.processo = p;
				}else{
					this.processo = new Processo();
				}
			}
			
//			if(this.processo.getAtendimento().getId() != id){
//				this.processo = processoService.porId(id);
//			}
		}
	}
	
	
	// Atualizar a comarca quando ela for alterado
	public void processoAlterado(@Observes ProcessoAlteradoEvent event) {
		this.processo = event.getProcesso();
	}

	// Lista de enums do tipo area de atuação
	public StatusProcesso[] getStatusProcesso() {
		return StatusProcesso.values();
	}

	public List<TipoVara> completarTipoVara(String nome) {
		List<TipoVara> varas = this.varas.porNome(nome);
		return varas;
	}

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public AtendimentoFilter getAtendimentoFilter() {
		return atendimentoFilter;
	}

	public void setAtendimentoFilter(AtendimentoFilter atendimentoFilter) {
		this.atendimentoFilter = atendimentoFilter;
	}

	public List<Atendimento> getAtendimentos() {
		return atendimentos;
	}

	public void setAtendimentos(List<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	public Boolean getBtnVincularProcesso() {
		return btnVincularProcesso;
	}

	public void setBtnVincularProcesso(Boolean btnVincularProcesso) {
		this.btnVincularProcesso = btnVincularProcesso;
	}

}
