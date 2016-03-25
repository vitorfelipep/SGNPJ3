package com.sgnpj.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.sgnpj.model.Perfil;
import com.sgnpj.model.Usuario;
import com.sgnpj.repository.Perfis;
import com.sgnpj.repository.Usuarios;
import com.sgnpj.util.jpa.Transactional;

public class CadastrarUsuarioService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Usuarios usuarios;
	
	@Inject
	private Perfis perfis;
	
	@Transactional
	public Usuario salvar(Usuario usuario){
		
		Usuario usuarioExistente = new Usuario();
		
		if(usuario.getId() != null){
			 usuarioExistente =  usuarios.porId(usuario.getId());
		}
		
		if(usuarioExistente != null && !usuarioExistente.equals(usuario)){
			throw new UsuarioCadastroException("Já existe um usuario advogado com este id.");
		}
		
		return usuarios.armazenar(usuario);
	}

	public List<Perfil> perfis() {
		return perfis.listarPerfis();
	}

}
