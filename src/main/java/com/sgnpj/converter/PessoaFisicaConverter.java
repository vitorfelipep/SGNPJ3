package com.sgnpj.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.sgnpj.model.PessoaFisica;
import com.sgnpj.repository.PessoasFisicas;

@FacesConverter(forClass = PessoaFisica.class)
public class PessoaFisicaConverter implements Converter{
	
	private PessoasFisicas pessoasFisicas;
	
	public PessoaFisicaConverter() {
		this.pessoasFisicas = com.sgnpj.util.cdi.CDIServiceLocator.getBean(PessoasFisicas.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		
		PessoaFisica retorno = null;
		
		if(value != null){
			Long id = new Long(value);
			retorno = pessoasFisicas.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		
		if(value != null){
			PessoaFisica pessoaFisica = (PessoaFisica) value;
			
			return pessoaFisica.getId() == null ? null : pessoaFisica.getId().toString();
		}
		return "";
	}

}
