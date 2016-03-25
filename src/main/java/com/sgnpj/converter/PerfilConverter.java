package com.sgnpj.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.sgnpj.model.Perfil;

@FacesConverter(value = "perfilConverter")
public class PerfilConverter implements Converter{
	
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Perfil retorno = null;
		
		if(value != null){
			retorno = (Perfil) component.getAttributes().get(value); 
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if(value != null){
			return (String) value.toString();
		}
		
		return "";
	}

}
