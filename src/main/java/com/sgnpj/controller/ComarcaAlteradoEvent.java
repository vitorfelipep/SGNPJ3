package com.sgnpj.controller;

import java.io.Serializable;

import com.sgnpj.model.Comarca;

public class ComarcaAlteradoEvent implements Serializable {

	private static final long serialVersionUID = 1L;

	private Comarca comarca;

	public ComarcaAlteradoEvent(Comarca comarca) {
		this.comarca = comarca;
	}

	public Comarca getComarca() {
		return comarca;
	}

}
