package com.sgnpj.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.sgnpj.model.Usuario;
import com.sgnpj.repository.Usuarios;
import com.sgnpj.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter{
	
	private Usuarios usuarios;
	
	public UsuarioConverter() {
		this.usuarios = (Usuarios) CDIServiceLocator.getBean(Usuarios.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Usuario retorno = null;
		
		if(value != null){
			retorno = this.usuarios.porId(new Long(value)); 
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			Usuario usuario = (Usuario) value;
			return usuario.getId() == null ? null : usuario.getId().toString();
		}
		
		return "";
	}

}
