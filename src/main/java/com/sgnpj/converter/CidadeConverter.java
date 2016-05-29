package com.sgnpj.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.sgnpj.model.Cidade;
import com.sgnpj.repository.RepositoryCidades;

@FacesConverter(forClass = Cidade.class)
public class CidadeConverter implements Converter{
	
	private RepositoryCidades repositoryCidades;
	
	public CidadeConverter() {
		this.repositoryCidades = com.sgnpj.util.cdi.CDIServiceLocator
				.getBean(RepositoryCidades.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Cidade retorno = null;

		if (value != null) {
			Integer id = new Integer(value);
			retorno = repositoryCidades.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if(value != null){
			Cidade cidade = (Cidade) value;
			
			return cidade.getId() == null ? null : cidade.getId().toString();
		}
		return "";
	}

}
