package com.sgnpj.controller;

/**
 * @author Vitor
 */

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.sgnpj.model.Comarca;
import com.sgnpj.model.TipoVara;
import com.sgnpj.repository.Comarcas;
import com.sgnpj.service.TipoVaraService;
import com.sgnpj.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroTipoVaraBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TipoVaraService varaService;

	@Inject
	private Comarcas comarcas;

	@Produces
	@EdicaoVara
	private TipoVara vara;

	@Inject
	private Comarca comarca;

	public CadastroTipoVaraBean() {
		this.vara = new TipoVara();
	}

	public void inicializar() {
		if (FacesUtil.isNotPostBack()) {

		}
	}

	public void cadastrar() {
		if (this.vara.getId() == null) {
			try {
				this.vara.setDataCadastro(new Date());
				this.vara = varaService.salvar(vara);
			} finally {
				FacesUtil.addInfoMesage("Cadastro da vara: " + vara.getNome()
						+ " efetuado com sucesso!");
				limparForm();
			}
		}else{
			try {
				this.vara = varaService.salvar(vara);
			} finally {
				FacesUtil.addInfoMesage("Cadastro da vara: " + vara.getNome()
						+ " efetuado com sucesso!");
				limparForm();
			}
		}
	}
	
	private void limparForm() {
		this.vara = new TipoVara();
	}

	public List<Comarca> completarComarca(String nome) {
		List<Comarca> comarcas = this.comarcas.porNome(nome);
		return comarcas;
	}
	
	// Atualizar a comarca quando ela for alterado
	public void varaAlterado(@Observes VaraAlteradoEvent event) {
		this.vara = event.getVara();
	}
	
	public boolean isEditando() {
		return this.vara.getId() != null;
	}
	
	
	/* Getters and Setters */
	
	public TipoVara getVara() {
		return vara;
	}

	public void setVara(TipoVara vara) {
		this.vara = vara;
	}

	public Comarca getComarca() {
		return comarca;
	}

	public void setComarca(Comarca comarca) {
		this.comarca = comarca;
	}

}
