package com.sgnpj.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.velocity.tools.generic.NumberTool;

import com.outjected.email.api.MailMessage;
import com.outjected.email.impl.templating.velocity.VelocityTemplate;
import com.sgnpj.model.Audiencia;
import com.sgnpj.util.jsf.FacesUtil;
import com.sgnpj.util.mail.Mailer;


@Named
@RequestScoped
public class EnvioEmailAudienciaBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Mailer mailer;
	
	@Inject
	@EdicaoAudiencia
	private Audiencia audiencia;
	
	public void enviarAudiencia(){
		if(audiencia.getId() != null){
			MailMessage messager = mailer.novaMensagem();
			messager.to(this.audiencia.getProcesso().getAtendimento().getAssistido().getEmail(), this.audiencia.getAdvogadoResponsavel().getUsuario().getEmail())
				.subject("Sua audiencia nº "+ this.audiencia.getId() + " esta marcada para " + new SimpleDateFormat("dd/MM/yyyy").format(this.audiencia.getDataAudiencia()))
				.bodyHtml(new VelocityTemplate(getClass().getResourceAsStream("/emails/audiencia.template")))
				.put("audiencia", this.audiencia)
				.put("numberTool", new NumberTool())
				.put("locale", new Locale("pt", "BR"))
				.send();
			
			FacesUtil.addInfoMesage("Audiencia enviada por e-mail  com sucesso!");
		}else{
			FacesUtil.addWarningMesage("Não há audiência selecionada para envio de email!");
		}
	}
	
}
