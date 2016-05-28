package com.sgnpj.util.report;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import org.hibernate.jdbc.Work;

import com.sgnpj.repository.Dao;

public class ExecutorRelatorio extends Dao implements Work {

	private String caminhoRelatorio;
	private HttpServletResponse response;
	private Map<String, Object> parametro;
	private String nomeArquivoSaida;

	private boolean relatorioGerado;

	public ExecutorRelatorio(String caminhoRelatorio,
			HttpServletResponse response, Map<String, Object> parametro,
			String nomeArquivoSaida) {
		this.caminhoRelatorio = caminhoRelatorio;
		this.response = response;
		this.parametro = parametro;
		this.nomeArquivoSaida = nomeArquivoSaida;

		this.parametro.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));

	}

	@Override
	public void execute(Connection connection) throws SQLException {

		try {
			InputStream relatorioStream = this.getClass().getResourceAsStream(
					this.caminhoRelatorio);
			JasperPrint print = JasperFillManager.fillReport(relatorioStream,
					this.parametro, connection);
			
			this.relatorioGerado = print.getPages().size() > 0;
			
			
			if(this.relatorioGerado){
				
				JRExporter exportador = new JRPdfExporter();
				exportador.setParameter(JRExporterParameter.OUTPUT_STREAM,
						response.getOutputStream());
				exportador.setParameter(JRExporterParameter.JASPER_PRINT, print);
	
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", "attachment; filename=\""
						+ this.nomeArquivoSaida + "\"");

				exportador.exportReport();
				
				//segundoRelatorio();
			}
		} catch (Exception e) {
			throw new SQLException("Erro ao executar relatório "
					+ this.caminhoRelatorio, e);
		}
	}
	
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	public void segundoRelatorio(){
//		try{
//			
//			open();
////				HashMap parametros = new HashMap(); 
////				parametros.put("idAssistido", this.assistido.getId());
//				
//				//Saida Teste
//				JasperPrint print = JasperFillManager.fillReport("C:/Users/Vitor/workspace/SGNPJ/src/main/resources/relatorios/RelatorioProcuracao.jasper", this.parametro, con);
//				JasperExportManager.exportReportToPdfFile(print, "C:/Users/Vitor/workspace/SGNPJ/src/main/webapp/Relatorio/Procuracao.pdf");
//				
//				Thread.sleep(5000);													
//		        FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8081/SGNPJ/Relatorio/Procuracao.pdf"); 
//		       
//			
//			//Link de Produção
////			JasperPrint print = JasperFillManager.fillReport("/var/lib/tomcat7/webapps/saidasNogueiraV1/Relatorio/PdfSaidaNogueira.jasper", parametros);
////			JasperExportManager.exportReportToPdfFile(print, "/var/lib/tomcat7/webapps/saidasNogueiraV1/Relatorio/PdfSaidaNogueira.pdf");
////		
////			
////			
////			Thread.sleep(5000);													
////	        FacesContext.getCurrentInstance().getExternalContext().redirect("http://192.168.15.164:8080/saidasNogueiraV1/Relatorio/PdfSaidaNogueira.pdf"); 
//			
//			close();
//			
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	
//	}

	public boolean isRelatorioGerado() {
		return relatorioGerado;
	}

}
