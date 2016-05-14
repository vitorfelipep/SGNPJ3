package com.sgnpj.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.sgnpj.model.Processo;
import com.sgnpj.repository.Processos;

@FacesConverter(forClass = Processo.class)
public class ProcessoConverter implements Converter{
	
	private Processos processos;
	
	public ProcessoConverter() {
		this.processos = com.sgnpj.util.cdi.CDIServiceLocator.getBean(Processos.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		
		Processo processo = null;
		
		if(value != null){
			Long id = new Long(value);
			
			processo = processos.porId(id);
		}
		
		return processo;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if(value != null){
			Processo processo = (Processo) value;
			return processo.getId() == null ? null : processo.getId().toString();
		}
		return "";
	}

}
