package com.sgnpj.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DataValor implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Date data;
	private Long valor;
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Long getValor() {
		return valor;
	}
	public void setValor(Long valor) {
		this.valor = valor;
	}
}
