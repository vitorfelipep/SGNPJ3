package com.sgnpj.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sgnpj.model.Perfil;
import com.sgnpj.model.Usuario;
import com.sgnpj.repository.Usuarios;
import com.sgnpj.util.cdi.CDIServiceLocator;

public class AppUserDetailsService implements UserDetailsService{

	@Override 
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuarios usuarios = CDIServiceLocator.getBean(Usuarios.class);
		Usuario usuario = usuarios.porEmail(email);
		UsuarioSistema user = null;
		
		if(usuario != null){
			user = new UsuarioSistema(usuario, getGrupos(usuario));
		}
		
		return user;
	}

	private Collection<? extends GrantedAuthority> getGrupos(Usuario usuario) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		for(Perfil perfil : usuario.getPerfis()) {
			authorities.add(new SimpleGrantedAuthority(perfil.getNome().toUpperCase()));
		}
		
		return authorities;
	}

	
}
