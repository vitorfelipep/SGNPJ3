package com.sgnpj.controller;

import com.sgnpj.model.TipoVara;

public class VaraAlteradoEvent {

	private TipoVara vara;

	public VaraAlteradoEvent(TipoVara v) {
		this.vara = v;
	}

	public TipoVara getVara() {
		return vara;
	}

}
