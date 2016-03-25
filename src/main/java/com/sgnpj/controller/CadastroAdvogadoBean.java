package com.sgnpj.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.sgnpj.model.Advogado;
import com.sgnpj.model.AreaAtuacao;
import com.sgnpj.model.Perfil;
import com.sgnpj.model.Situacao;
import com.sgnpj.model.Usuario;
import com.sgnpj.service.CadastrarAdvogadoService;
import com.sgnpj.service.CadastrarUsuarioService;
import com.sgnpj.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroAdvogadoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastrarAdvogadoService advogadoService;

	@Inject
	private CadastrarUsuarioService usuarioService;

	private Advogado advogado;
	private Usuario usuario;

	private String verificadorSenha;
	
	private List<Perfil> perfis;

	public CadastroAdvogadoBean() {
		
		this.advogado = new Advogado();
		this.usuario = new Usuario();
	}
	
	public void inicializar(){
		if (FacesUtil.isNotPostBack()) {
			this.perfis = new ArrayList<Perfil>();
			this.perfis = usuarioService.perfis();
		}
	}

	public void limparForm() {
		this.advogado = new Advogado();
		this.usuario = new Usuario();
	}

	public void cadastrar() {

		this.usuario = usuarioService.salvar(usuario);
		this.advogado.setUsuario(usuario);
		Date dataCadastro = new Date();
		this.advogado.setDataCadastro(dataCadastro);
		this.advogado = advogadoService.salvar(advogado);
		this.usuario.setAdvogado(advogado);

		limparForm();

		FacesUtil.addInfoMesage("Advogado salvo com sucesso!");
	}

	// Lista de enums do tipo area de atuação
	public AreaAtuacao[] getAreasAtuacao() {
		return AreaAtuacao.values();
	}

	public Situacao[] getSituacao() {
		return Situacao.values();
	}

	public void verificarSenhas() {
		if (!usuario.getSenha().equals(verificadorSenha)) {
			FacesUtil
					.addWarningMesage("As senhas não estão iguais, por favor tente novamente!");
		}
	}

	public Advogado getAdvogado() {
		return advogado;
	}

	public void setAdvogado(Advogado advogado) {
		this.advogado = advogado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public String getVerificadorSenha() {
		return verificadorSenha;
	}

	public void setVerificadorSenha(String verificadorSenha) {
		this.verificadorSenha = verificadorSenha;
	}

}
