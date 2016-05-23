package com.sgnpj.controller;

import com.sgnpj.model.Audiencia;

public class AudienciaAlteradoEvent {

	private Audiencia audiencia;

	public AudienciaAlteradoEvent(Audiencia audiencia) {
		this.audiencia = audiencia;
	}

	public Audiencia getAudiencia() {
		return audiencia;
	}

}
