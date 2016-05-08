package com.sgnpj.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.sgnpj.model.TipoVara;
import com.sgnpj.repository.Varas;

@FacesConverter(forClass = TipoVara.class)
public class TipoVaraConverter implements Converter{
	
	private Varas varas;
	
	public TipoVaraConverter() {
		varas = com.sgnpj.util.cdi.CDIServiceLocator.getBean(Varas.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		
		TipoVara vara = null;
		if(value != null){
			Long id = new Long(value);
			vara = varas.porId(id);
		}
		return vara;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		
		if(value != null){
			TipoVara vara = (TipoVara) value;
			return vara.getId() == null ? null : vara.getId().toString();
		}
		
		return "";
	}

}
