package com.sgnpj.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.sgnpj.model.Triagem;
import com.sgnpj.repository.Triagens;

@FacesConverter(forClass = Triagem.class)
public class TriagemConverter implements Converter{
	
	
	private Triagens triagens;
	
	public TriagemConverter() {
		this.triagens = com.sgnpj.util.cdi.CDIServiceLocator.getBean(Triagens.class);
	}
	
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		
		Triagem triagem = null;
		if(value != null){
			Long id = new Long(value);
			triagem = triagens.porId(id);
		}
		return triagem;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		
		if(value != null){
			Triagem triagem = (Triagem) value;
			return triagem.getId() == null ? null : triagem.getId().toString();
		}
		
		return "";
	}

}
