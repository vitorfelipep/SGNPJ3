package com.sgnpj.controller;

import com.sgnpj.model.Assistido;

public class AssistidoAlteradoEvent {

	private Assistido assistido;

	public AssistidoAlteradoEvent(Assistido assistido) {
		this.assistido = assistido;
	}

	public Assistido getAssistido() {
		return assistido;
	}

}
