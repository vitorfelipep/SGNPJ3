package com.sgnpj.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sgnpj.util.jsf.FacesUtil;

@Named
@SessionScoped
public class LoginBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String email;
	@Inject
	private FacesContext facesContext; 
	
	@Inject
	private HttpServletRequest request;
	
	@Inject
	private HttpServletResponse response;
	
	public void preRender(){
		if("true".equals(request.getParameter("invalid"))){
			FacesUtil.addErrorMesage("Usuário ou senha inválido!");
		}
	}
	
	public void login() throws ServletException, IOException{
		RequestDispatcher dispacher = request.getRequestDispatcher("/j_spring_security_check");
		dispacher.forward(request, response);
		
		facesContext.responseComplete();
	}
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
