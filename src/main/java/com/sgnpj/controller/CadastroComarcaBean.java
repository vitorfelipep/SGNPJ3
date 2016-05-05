package com.sgnpj.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.sgnpj.model.Comarca;
import com.sgnpj.model.Tribunal;
import com.sgnpj.repository.Tribunais;
import com.sgnpj.service.CadastroComarcaService;

@Named
@ViewScoped
public class CadastroComarcaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroComarcaService cadastroService;

	@Inject
	private Tribunais tribunais;

	@Inject
	private Tribunal tribunal;

	@Produces
	private Comarca comarca;

	public CadastroComarcaBean() {
		this.comarca = new Comarca();
	}

	public void cadastrar() {
		this.comarca = cadastroService.salvar(comarca);
	}
  
	public List<Tribunal> completarTribunal(String nome) {
		List<Tribunal> tribunais = this.tribunais.porNome(nome);
		return tribunais;
	}

	public Comarca getComarca() {
		return comarca;
	}

	public void setComarca(Comarca comarca) {
		this.comarca = comarca;
	}

	public Tribunal getTribunal() {
		return tribunal;
	}

	public void setTribunal(Tribunal tribunal) {
		this.tribunal = tribunal;
	}

}
