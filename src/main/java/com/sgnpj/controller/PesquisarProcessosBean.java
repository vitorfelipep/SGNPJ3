package com.sgnpj.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.sgnpj.model.Atendimento;
import com.sgnpj.model.Processo;
import com.sgnpj.model.StatusAtendimento;
import com.sgnpj.model.StatusProcesso;
import com.sgnpj.repository.Atendimentos;
import com.sgnpj.repository.Processos;
import com.sgnpj.repository.filter.ProcessoFilter;
import com.sgnpj.service.CadastrarProcessoService;
import com.sgnpj.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisarProcessosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Processos processosRepository;
	
	@Inject
	private CadastrarProcessoService processoService;
	
	@Inject
	private Atendimentos atendimentos;

	@Inject
	private ProcessoFilter filtro;

	@Inject
	@ProcessoEdicao
	private Processo processo;

	private List<Processo> processos;

	public PesquisarProcessosBean() {
		this.processos = new ArrayList<Processo>();
	}

	public void pesquisarProcessos() {

		this.processos = processosRepository.filtrados(filtro);
		System.out.println(this.filtro.toString());

	}
	
	
	public void alterarProcesso(){
		if(this.processo.getDataConclusao() != null){
			if(this.processo.getAtendimento().getStatusAtendimento().equals(StatusAtendimento.EM_ATENDIMENTO)){
				this.processo.getAtendimento().setStatusAtendimento(StatusAtendimento.FINALIZADO);
				this.atendimentos.armazenar(this.processo.getAtendimento());
			}
		}
		
		this.processo = processoService.salvar(processo);
		
		FacesUtil.addInfoMesage("Processo de número: " + this.processo.getNumeroProcesso() + " Alterado com sucesso!");
	}

	// Atualizar o processo quando ele for alterado
	public void processoAlterado(@Observes ProcessoAlteradoEvent event) {
		this.processo = event.getProcesso();
	}

	// * auto complete jsf */
	public List<Atendimento> completarAtendimento(String nome) {
		List<Atendimento> assistidos = this.atendimentos.porNome(nome);
		return assistidos;
	}

	// Lista de enums do tipo status processo
	public StatusProcesso[] getStatusProcesso() {
		return StatusProcesso.values();
	}

	/* GETTERS AND SETTERS */

	public List<Processo> getProcessos() {
		return processos;
	}

	public void setProcessos(List<Processo> processos) {
		this.processos = processos;
	}

	public ProcessoFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(ProcessoFilter filtro) {
		this.filtro = filtro;
	}

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

}
