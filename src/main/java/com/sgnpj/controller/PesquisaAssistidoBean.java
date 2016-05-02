package com.sgnpj.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.sgnpj.model.Assistido;
import com.sgnpj.model.AssistidoContraParte;
import com.sgnpj.model.SituacaoAssitido;
import com.sgnpj.repository.Assistidos;
import com.sgnpj.repository.filter.AssistidoFilter;

@Named
@ViewScoped
public class PesquisaAssistidoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Assistidos assistidos;

	private AssistidoFilter filtro;

	private List<Assistido> assistidosFiltrados;

	private Assistido assistidoSelecionado;

	public PesquisaAssistidoBean() {
		this.filtro = new AssistidoFilter();
		this.assistidosFiltrados = new ArrayList<Assistido>();
	}
	
	
	// Lista de enums do tipo area de atuação
	public SituacaoAssitido[] getSituacaoAssistido() {
		return SituacaoAssitido.values();
	}
	
	/* ----------------------------getters and Setters ----------------------------------------------------------------*/
	
	public void pesquisar() {
		this.assistidosFiltrados = assistidos.filtrados(filtro);
		
		for(Assistido a : assistidosFiltrados){
			for(AssistidoContraParte cp : a.getContraPartes()){
				a.setContraParte(cp);
			}
		}
		//System.out.println(this.assistidosFiltrados.get(5));
	}

	public Assistidos getAssistidos() {
		return assistidos;
	}

	public void setAssistidos(Assistidos assistidos) {
		this.assistidos = assistidos;
	}

	public AssistidoFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(AssistidoFilter filtro) {
		this.filtro = filtro;
	}

	public List<Assistido> getAssistidosFiltrados() {
		return assistidosFiltrados;
	}

	public void setAssistidosFiltrados(List<Assistido> assistidosFiltrados) {
		this.assistidosFiltrados = assistidosFiltrados;
	}

	public Assistido getAssistidoSelecionado() {
		return assistidoSelecionado;
	}

	public void setAssistidoSelecionado(Assistido assistidoSelecionado) {
		this.assistidoSelecionado = assistidoSelecionado;
	}

}
