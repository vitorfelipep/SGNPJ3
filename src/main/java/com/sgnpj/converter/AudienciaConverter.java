package com.sgnpj.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.sgnpj.model.Audiencia;
import com.sgnpj.repository.Audiencias;

@FacesConverter(forClass = Audiencia.class)
public class AudienciaConverter implements Converter{
	
	private Audiencias audiencias;
	
	public AudienciaConverter() {
		this.audiencias = com.sgnpj.util.cdi.CDIServiceLocator.getBean(Audiencias.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		
		Audiencia audiencia = null;
		
		if(value != null){
			Long id = new Long(value);
			audiencia = audiencias.porId(id);
		}
		
		return audiencia;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		
		if(value != null){
			Audiencia audiencia = (Audiencia) value;
			return audiencia.getId() == null ? null : audiencia.getId().toString();
		}
		return "";
	}

}
