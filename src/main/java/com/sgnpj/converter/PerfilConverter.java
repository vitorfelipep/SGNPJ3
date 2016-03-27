package com.sgnpj.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.sgnpj.model.Perfil;
import com.sgnpj.repository.Perfis;
import com.sgnpj.util.cdi.CDIServiceLocator;

@FacesConverter(value = "perfilConverter")
public class PerfilConverter implements Converter{
	
private Perfis perfis;
	
	public PerfilConverter() {
		this.perfis = (Perfis) CDIServiceLocator.getBean(Perfis.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Perfil retorno = null;
		
		if(value != null){
			retorno = this.perfis.porId(new Long(value)); 
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if(value != null){
			return ((Perfil) value).getId().toString();
		}
		return "";
	}

}
