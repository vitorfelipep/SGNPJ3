package com.sgnpj.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.sgnpj.model.Estado;
import com.sgnpj.repository.EstadoRepository;

@FacesConverter(forClass = Estado.class)
public class EstadoConverter implements Converter {

	private EstadoRepository estadoRepository;

	public EstadoConverter() {
		this.estadoRepository = com.sgnpj.util.cdi.CDIServiceLocator
				.getBean(EstadoRepository.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Estado retorno = null;

		if (value != null) {
			Integer id = new Integer(value);
			retorno = estadoRepository.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if(value != null){
			Estado estado = (Estado) value;
			
			return estado.getId() == null ? null : estado.getId().toString();
		}
		return "";
	}

}
