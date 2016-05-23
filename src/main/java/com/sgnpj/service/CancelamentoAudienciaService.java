package com.sgnpj.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.sgnpj.model.Audiencia;
import com.sgnpj.model.StatusAudiencia;
import com.sgnpj.repository.Audiencias;
import com.sgnpj.util.jpa.Transactional;

public class CancelamentoAudienciaService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Audiencias audiencias;
	
	
	@Transactional
	public Audiencia cancelar(Audiencia audiencia) {
		audiencia = audiencias.porId(audiencia.getId());
		
		if(audiencia.isNaoCancelavel()){ 
			throw new NegocioException("Audiencia não pode ser excluida se data for igual a "
					+ audiencia.getDataAudiencia() + " em um prazo após 24 horas.");
		}
		
		
		audiencia.setStauAudiencia(StatusAudiencia.CANCELADA);
		
		audiencia = audiencias.armazenar(audiencia);
		
		return audiencia;
	}

}
