package com.sgnpj.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.sgnpj.model.Assistido;
import com.sgnpj.repository.Assistidos;

@FacesConverter(forClass = Assistido.class)
public class AssistidoConverter implements Converter {
	
	private Assistidos assistidos;
	
	public AssistidoConverter() {
		this.assistidos = com.sgnpj.util.cdi.CDIServiceLocator.getBean(Assistidos.class);
	}
	
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		
		Assistido retorno = null;
		
		if(value != null){
			Long id = new Long(value);
			retorno = assistidos.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if(value != null){
			Assistido assistido = (Assistido) value;
			
			return assistido.getId() == null ? null : assistido.getId().toString();
		}
		
		return "";
	}

}
