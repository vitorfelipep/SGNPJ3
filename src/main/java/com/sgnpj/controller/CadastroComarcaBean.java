package com.sgnpj.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.sgnpj.model.Comarca;
import com.sgnpj.model.Tribunal;
import com.sgnpj.repository.Tribunais;
import com.sgnpj.service.CadastroComarcaService;
import com.sgnpj.util.jsf.FacesUtil;

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
	@EdicaoComarca
	private Comarca comarca;

	public CadastroComarcaBean() {
		this.comarca = new Comarca();
	}
	
	public void inicializar() {
		if(FacesUtil.isNotPostBack()){
			
		}
	}
	
	
	public void cadastrar() {
		
		if(this.comarca.getId() == null){
			try{
				this.comarca.setTribunal(tribunal);
				this.comarca = cadastroService.salvar(comarca);
			}finally{
				FacesUtil.addInfoMesage("Comarca cadastrada com sucesso!");
				limparForm();
			}
		}else{
			try{
				this.comarca.setTribunal(tribunal);
				this.comarca = cadastroService.salvar(comarca);
			}finally{
				FacesUtil.addInfoMesage("Comarca alterada com sucesso!");
				limparForm();
			}
		}
	}
	
	public void limparForm(){
		this.tribunal = new Tribunal();
		this.comarca = new Comarca();
	}
	
	// Atualizar a comarca quando ela for alterado
	public void comarcaAletrado(@Observes ComarcaAlteradoEvent event) {
		this.comarca = event.getComarca();
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
