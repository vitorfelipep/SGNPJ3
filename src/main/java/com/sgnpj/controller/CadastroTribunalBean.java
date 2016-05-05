package com.sgnpj.controller;

import java.io.Serializable;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.sgnpj.model.Tribunal;
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

	public CadastroTribunalBean() {
		this.tribunal = new Tribunal();
	}

	public void cadastrar() {
		System.out.println(tribunal.getNome());
		try{
			this.tribunal = cadastroService.salvar(tribunal);
		}finally{
			FacesUtil.addInfoMesage("Tribunal Cadastrado com sucesso!");
			limparForm();
		}
	}
	
	private void limparForm() {
		this.tribunal = new Tribunal();
	}

	//Atualizar o estagiario quando ele for alterado
	public void tribunalAlterado(@Observes TribunalAlteradoEvent event){
		this.tribunal = event.getTribunal();
	}
	
	public Tribunal getTribunal() {
		return tribunal;
	}

	public void setTribunal(Tribunal tribunal) {
		this.tribunal = tribunal;
	}

}
