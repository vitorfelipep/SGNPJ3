package com.sgnpj.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.sgnpj.model.Estagiario;
import com.sgnpj.repository.Estagiarios;

@FacesConverter(forClass = Estagiario.class)
public class EstagiarioConverter implements Converter{
	
	
	private Estagiarios estagiarios;
	
	public EstagiarioConverter() {
		this.estagiarios = com.sgnpj.util.cdi.CDIServiceLocator.getBean(Estagiarios.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Estagiario retorno = null;
		
		if(value != null){
			Long id = new Long(value);
			retorno = estagiarios.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		
		if(value != null){
			Estagiario estagiario = (Estagiario) value;
			return estagiario.getId() == null ? null : estagiario.getId().toString(); 
		}
		return "";
	}

}
