package com.sgnpj.controller;

import com.sgnpj.model.Advogado;

public class AdvogadoAlteradoEvent {

	private Advogado advogado;

	public AdvogadoAlteradoEvent(Advogado advogado) {
		this.advogado = advogado;
	}

	public Advogado getAdvogado() {
		return advogado;
	}

}
