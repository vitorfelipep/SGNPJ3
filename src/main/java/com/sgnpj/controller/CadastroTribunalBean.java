package com.sgnpj.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.sgnpj.model.Cidade;
import com.sgnpj.model.Tribunal;
import com.sgnpj.repository.RepositoryCidades;
import com.sgnpj.service.CadastrarTribunalService;
import com.sgnpj.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroTribunalBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastrarTribunalService cadastroService;

	@Produces
	@EdicaoTribunal
	private Tribunal tribunal;

	@Inject
	private RepositoryCidades repositoryCidades; //Este objeto é a classe responsavel por acessar o banco e metrazer as coisas.

	private List<Cidade> cidades;

	public CadastroTribunalBean() {
		this.tribunal = new Tribunal();
	}

	public void inicializar() {
		if (FacesUtil.isNotPostBack()) {

		}
	}

	public void cadastrar() {
		System.out.println(tribunal.getNome());

		if (tribunal.getId() == null) {
			try {
				this.tribunal = cadastroService.salvar(tribunal);
			} finally {
				FacesUtil.addInfoMesage("Tribunal Cadastrado com sucesso!");
				limparForm();
			}
		} else {
			try {
				this.tribunal = cadastroService.salvar(tribunal);
			} finally {
				FacesUtil.addInfoMesage("Tribunal Alterado com sucesso!");
				limparForm();
			}
		}
	}

	/**
	 * Métedo abaixo é resposavel por auto completar a lista de cidades Após o
	 * click do select One menu de estado.
	 * 
	 * @return this.cidades
	 */
	public List<Cidade> carregarCidades() {

		if (this.cidades != null) {
			this.cidades = new ArrayList<Cidade>();//Lista de cidades...Uma lista de Objeto
		}

		return this.cidades = repositoryCidades
				.filtradoPorUf(this.tribunal.getUf());//Faço o ajax setar no objeto este atributo que é o UF (String)
	}

	private void limparForm() {
		this.tribunal = new Tribunal();
	}

	// Atualizar o estagiario quando ele for alterado
	public void tribunalAlterado(@Observes TribunalAlteradoEvent event) {
		this.tribunal = event.getTribunal();
	}
	
	public boolean isEditando() {
		return this.tribunal.getId() != null;
	}
	
	/* getters and Setters */

	public Tribunal getTribunal() {
		return tribunal;
	}

	public void setTribunal(Tribunal tribunal) {
		this.tribunal = tribunal;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

}
