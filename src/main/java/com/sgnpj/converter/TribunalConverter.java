package com.sgnpj.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.sgnpj.model.Tribunal;
import com.sgnpj.repository.Tribunais;

@FacesConverter(forClass = Tribunal.class)
public class TribunalConverter implements Converter{
	
	private Tribunais tribunais;
	
	public TribunalConverter() {
		this.tribunais =  com.sgnpj.util.cdi.CDIServiceLocator.getBean(Tribunais.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		
		Tribunal tribunal = null;
		
		if(value != null){
			Long id = new Long(value);
			tribunal = this.tribunais.porId(id);
		}
		return tribunal;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		
		if(value != null){
			Tribunal tribunal = (Tribunal) value;
			return tribunal.getId() == null ? null : tribunal.getId().toString();
		}
		return "";
	}

}
