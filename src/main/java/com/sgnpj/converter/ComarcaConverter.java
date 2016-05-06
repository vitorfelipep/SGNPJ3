package com.sgnpj.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.sgnpj.model.Comarca;
import com.sgnpj.repository.Comarcas;

@FacesConverter(forClass = Comarca.class)
public class ComarcaConverter implements Converter{
	
	private Comarcas comarcas;
	
	public ComarcaConverter() {
		this.comarcas = com.sgnpj.util.cdi.CDIServiceLocator.getBean(Comarcas.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		
		Comarca comarca = null;
		
		if(value != null){
			Long id = new Long(value);
			comarca = this.comarcas.porId(id);
		}
		return comarca;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if(value != null){
			Comarca comarca = (Comarca) value;
			return comarca.getId() == null ? null : comarca.getId().toString();
		}
		return "";
	}

}
