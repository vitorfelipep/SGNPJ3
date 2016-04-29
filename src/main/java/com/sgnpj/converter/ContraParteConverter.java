package com.sgnpj.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.sgnpj.model.AssistidoContraParte;
import com.sgnpj.repository.AssistidoPartesContrarias;

@FacesConverter(forClass = AssistidoContraParte.class)
public class ContraParteConverter implements Converter {

	private AssistidoPartesContrarias partesContrarias;

	public ContraParteConverter() {
		this.partesContrarias = com.sgnpj.util.cdi.CDIServiceLocator
				.getBean(AssistidoPartesContrarias.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {

		AssistidoContraParte retorno = null;

		if (value != null) {
			Long id = new Long(value);
			retorno = this.partesContrarias.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		
		if(value != null){
			AssistidoContraParte contraParte = (AssistidoContraParte) value;
			
			return contraParte.getId() == null ? null : contraParte.getId().toString();
		}
		
		return "";
	}

}
