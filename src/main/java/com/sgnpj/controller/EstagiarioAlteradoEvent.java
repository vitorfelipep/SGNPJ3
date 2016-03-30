package com.sgnpj.controller;

import com.sgnpj.model.Estagiario;

public class EstagiarioAlteradoEvent {

	private Estagiario estagiario;

	public EstagiarioAlteradoEvent(Estagiario estagiario) {
		this.estagiario = estagiario;
	}

	public Estagiario getEstagiario() {
		return estagiario;
	}

}
