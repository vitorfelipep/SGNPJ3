package com.sgnpj.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.sgnpj.model.Atendimento;
import com.sgnpj.repository.Atendimentos;

@FacesConverter(forClass = Atendimento.class)
public class AtendimentoConverter implements Converter{
	
	private Atendimentos atendimentos;
	
	public AtendimentoConverter() {
		this.atendimentos = com.sgnpj.util.cdi.CDIServiceLocator.getBean(Atendimentos.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		
		Atendimento retorno = null;
		
		if(value != null){
			Long id = new Long(value);
			retorno = atendimentos.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		
		if(value != null){
			Atendimento atendimento = (Atendimento) value;
			
			return atendimento.getId() == null ? null : atendimento.getId().toString();
		}
		
		return "";
	}

}
