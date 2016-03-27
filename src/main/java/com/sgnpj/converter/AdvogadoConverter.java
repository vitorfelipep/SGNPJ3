package com.sgnpj.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.sgnpj.model.Advogado;
import com.sgnpj.repository.Advogados;



@FacesConverter(forClass = Advogado.class)
public class AdvogadoConverter implements Converter {
	
	private Advogados advogados;
	
	public AdvogadoConverter() {
		this.advogados = com.sgnpj.util.cdi.CDIServiceLocator.getBean(Advogados.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		
		Advogado retorno = null;
		
		if(value != null){
			Integer id = new Integer(value);
			retorno = advogados.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			Advogado advogado = (Advogado) value;
			return advogado.getId_advogado() == null ? null : advogado.getId_advogado().toString();
		}
		
		return "";
	}

}
