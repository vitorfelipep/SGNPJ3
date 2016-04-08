package com.sgnpj.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.sgnpj.model.AreaAtuacao;
import com.sgnpj.model.Estagiario;
import com.sgnpj.model.Perfil;
import com.sgnpj.model.PeriodoLetivo;
import com.sgnpj.model.Situacao;
import com.sgnpj.model.Usuario;
import com.sgnpj.service.CadastrarUsuarioService;
import com.sgnpj.service.EstagiarioService;
import com.sgnpj.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroEstagiarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EstagiarioService estagiarioService;

	@Inject
	private CadastrarUsuarioService usuarioService;
	
	@Produces
	@EstagiarioEdicao
	private Estagiario estagiario;

	private Usuario usuario;

	private Perfil perfil;

	private String verificadorSenha;

	private List<Perfil> perfis;

	public CadastroEstagiarioBean() {
		this.estagiario = new Estagiario();
		this.usuario = new Usuario();
	}
	
	public void inicializar() {
		if (FacesUtil.isNotPostBack()) {
			this.perfis = new ArrayList<Perfil>();
			this.perfis = usuarioService.perfilEstagiario();
		}
	}
	
	public void limparForm() {
		this.estagiario = new Estagiario();
		this.usuario = new Usuario();
		this.perfis = new ArrayList<Perfil>();
		this.perfis = usuarioService.perfis();
	}

	public void cadastrar() {
		try {
			
			this.usuario  = usuarioService.salvar(usuario);
			this.estagiario.setUsuario(usuario);
			this.estagiario.setNome(usuario.getNome());
			this.estagiario = estagiarioService.salvar(estagiario);
			this.usuario.setEstagiario(estagiario);
			this.perfis = new ArrayList<Perfil>();
			this.perfis.add(perfil);
			this.usuario.setPerfis(perfis);
			this.usuario = usuarioService.salvar(this.usuario);
			
			FacesUtil.addInfoMesage("Estagiario salvo com sucesso!");
			
		} finally {
			limparForm();
		}
	}
	
	//Atualizar o advogado quando ele for alterado
	public void estagiarioAlterado(@Observes EstagiarioAlteradoEvent event){
		this.estagiario = event.getEstagiario();
	}
	
	public boolean isEditando() {
		return this.estagiario.getId() != null;
	}
	
	public boolean isNaoEditando(){
		return this.estagiario.getId() == null;
	}
	
	// Lista de enums do tipo area de atuação
	public AreaAtuacao[] getAreasAtuacao() {
		return AreaAtuacao.values();
	}
	
	//Lista enum de situação
	public Situacao[] getSituacao() {
		return Situacao.values();
	}
	
	//Lista enum de periodo letivo
	public PeriodoLetivo[] getPeriodoLetivo() {
		return PeriodoLetivo.values();
	}

	public Estagiario getEstagiario() {
		return estagiario;
	}

	public void setEstagiario(Estagiario estagiario) {
		this.estagiario = estagiario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
		
		if(this.usuario != null){
			
			for(Perfil perfil : usuario.getPerfis()){
				this.perfil = perfil;
			}
			
		}
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public String getVerificadorSenha() {
		return verificadorSenha;
	}

	public void setVerificadorSenha(String verificadorSenha) {
		this.verificadorSenha = verificadorSenha;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

}
