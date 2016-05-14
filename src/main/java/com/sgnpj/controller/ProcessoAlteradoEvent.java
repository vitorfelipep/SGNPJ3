package com.sgnpj.controller;

import com.sgnpj.model.Processo;

public class ProcessoAlteradoEvent {

	private Processo processo;

	public ProcessoAlteradoEvent(Processo processo) {
		this.processo = processo;
	}

	public Processo getProcesso() {
		return processo;
	}

}
