package com.sgnpj.controller;

import com.sgnpj.model.Tribunal;

public class TribunalAlteradoEvent {
	
	private Tribunal tribunal;

	public TribunalAlteradoEvent(Tribunal tribunal) {
		this.tribunal = tribunal;
	}
	
	public Tribunal getTribunal() {
		return tribunal;
	}
	
	
}
