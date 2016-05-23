package com.sgnpj.security;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.sgnpj.model.Perfil;
import com.sgnpj.model.Usuario;
import com.sgnpj.repository.Usuarios;
import com.sgnpj.service.CadastrarUsuarioService;
import com.sgnpj.util.jsf.FacesUtil;

@Named
@RequestScoped
public class Seguranca implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ExternalContext externalContext;
	
	@Inject
	private CadastrarUsuarioService usuarioService;
	
	@Inject
	private Usuarios usuarios;
	
	@Inject
	private Usuario usuario;

	private String perfil;
	
	@NotEmpty
	private String novaSenha;
	@NotEmpty
	private String novaSenha2;
	
	public Seguranca() {
		this.usuario = new Usuario();
//		UsuarioSistema usu = null;
//		usu = getUsuarioLogado();
//		this.usuario = usu.getUsuario();
	}
	
	public void inicializar() {
		this.usuario = new Usuario();
		UsuarioSistema usu = null;
		usu = getUsuarioLogado();
		this.usuario = usu.getUsuario();
		this.perfil = pegarPerfilUsuario(this.usuario);
		System.out.println(usuario.toString());
		System.out.println(perfil);
	}
	
	
	public void alterarUsuario(){
		boolean verificador = false;
		try{
			verificador = usuarios.verficarSenha(usuario, usuario.getSenha());
			
			if(verificador){
				this.usuario = usuarios.porEmail(this.usuario.getEmail());
				if(novaSenha.equals(novaSenha2)){
					this.usuario.setSenha(novaSenha);
					this.usuario = usuarioService.salvar(usuario);
					
					FacesUtil.addInfoMesage("dados alterados com sucesso!");
					
				}else{
					FacesUtil.addWarningMesage("Senhas não são iguais, por favor verifique as novas senhas!");
				}
			}else{
				//this.usuario = usuarioService.salvar(usuario);
				FacesUtil.addErrorMesage("Senha incorreta, por favor tente novamente!");
			}
			
		}finally{
			atualizarUsuario(usuario);
		}
	}

	private void atualizarUsuario(Usuario usuario2) {
		this.usuario = usuario2;
	}

	private String pegarPerfilUsuario(Usuario usuario2) {
		String p = "";
		if(usuario2 != null){
			for(Perfil perfil : usuario2.getPerfis()){
				p = perfil.getDescricao();
			}
		}
		return p;
	}

	public String getNomeUsuario() {
		String nome = null;

		UsuarioSistema usuarioLogado = getUsuarioLogado();
		if (usuarioLogado != null) {
			nome = usuarioLogado.getUsuario().getNome();
		}

		return nome;
	}

	@Produces
	@UsuarioLogado
	public UsuarioSistema getUsuarioLogado() {
		UsuarioSistema usuario = null;
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) FacesContext
				.getCurrentInstance().getExternalContext().getUserPrincipal();

		if (auth != null && auth.getPrincipal() != null) {
			usuario = (UsuarioSistema) auth.getPrincipal();
		}

		return usuario;
	}
	
	
	public UsuarioSistema getUsuarioLogadoNoSistema(){
		return this.getUsuarioLogado();
	}

	public boolean isEmitirRelatorioPermisaao() {
		return externalContext.isUserInRole("ADMINISTRADOR")
				|| externalContext.isUserInRole("ADVOGADO");
	}

	public boolean isCancelarAudienciaPermissao() {
		return externalContext.isUserInRole("ADMINISTRADOR")
				|| externalContext.isUserInRole("ADVOGADO");
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getNovaSenha2() {
		return novaSenha2;
	}

	public void setNovaSenha2(String novaSenha2) {
		this.novaSenha2 = novaSenha2;
	}

}
