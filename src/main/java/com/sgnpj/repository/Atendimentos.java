package com.sgnpj.repository;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

import com.sgnpj.model.Assistido;
import com.sgnpj.model.AssistidoContraParte;
import com.sgnpj.model.Atendimento;
import com.sgnpj.model.Usuario;
import com.sgnpj.model.vo.DataValor;
import com.sgnpj.repository.filter.AtendimentoFilter;

public class Atendimentos implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Atendimento armazenar(Atendimento atendimento){
		return manager.merge(atendimento);
	}
	
	public Atendimento porId(Long id){
		return manager.find(Atendimento.class, id);
	}
	
	@SuppressWarnings({ "unchecked" })
	public  Map<Date, Long> valoresTotaisPorData(Integer numeroDeDias, Usuario criadoPor){
		
		Session session = manager.unwrap(Session.class);
		
		Calendar dataInicial = Calendar.getInstance();
		dataInicial = DateUtils.truncate(dataInicial, Calendar.DAY_OF_MONTH);
		dataInicial.add(Calendar.DAY_OF_MONTH, numeroDeDias * -1);
		
		Map<Date, Long> resultado = criarMapaVazio(numeroDeDias, dataInicial);
		
		Criteria criteria = session.createCriteria(Atendimento.class);
		
		
		//select date(data_criacao) as data, sum(valor_total) as valor
		// from pedido where data_criacao >= :dataInicial and vendedor_id = :criadoPor
		// group by date(data_criacao)
		//O criteria abaixo é baseado nesta restrição.
		criteria.setProjection(Projections.projectionList()
								.add(Projections.sqlGroupProjection("date(data_atendimento) as data", 
										"date(data_atendimento)", new String[] {"data"}, 
										new Type[] {StandardBasicTypes.DATE})
									)
								.add(Projections.count("areaAtendimento").as("valor"))
							  ).add(Restrictions.ge("dataAtendimento", dataInicial.getTime()));
		
		if(criadoPor != null){
			if(criadoPor.getAdvogado() != null){
				criteria.add(Restrictions.eq("advogado", criadoPor));
			}else if(criadoPor.getEstagiario() != null){
				criteria.add(Restrictions.eq("estagiario", criadoPor));
			}
		}
		
		
		List<DataValor> valoresPorData = criteria.
				setResultTransformer(Transformers.aliasToBean(DataValor.class)).list();
		//percorre a lista e substitui os valores do resultado pelo
		//valor da lista.
		for(DataValor dataValor : valoresPorData){
			resultado.put(dataValor.getData(), dataValor.getValor());
		}
		
		return resultado;
	}
	
	private  Map<Date, Long> criarMapaVazio(Integer numeroDeDias,
			Calendar dataInicial) {
		
		dataInicial = (Calendar) dataInicial.clone();
		Map<Date, Long> mapaInicial = new TreeMap<Date, Long>();
		
		for(int i = 0; i < numeroDeDias; i++){
			mapaInicial.put(dataInicial.getTime(), 0L);
			dataInicial.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		return mapaInicial;
	}

	public Atendimento PorIdAssistido(Assistido a, AssistidoContraParte ac) {	
		return manager.createQuery("from Atendimento at where at.assistido.id = :idAssistido and at.statusAtendimento = 'EM_APROVACAO' and at.contraParte.id = :idContraParte", Atendimento.class)
				.setParameter("idAssistido", a.getId())
				.setParameter("idContraParte", ac.getId())
				.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Atendimento> filtrados(AtendimentoFilter atendimentoFilter) {
		Session session = manager.unwrap(Session.class);
		
		Criteria criteria = session.createCriteria(Atendimento.class)
				//INNER JOIN COM 
				.createAlias("assistido", "cliente")
				
				.createAlias("advogado", "ad")
				
				.createAlias("estagiario", "es")
				
				.createAlias("contraParte", "cp");
		
		if (atendimentoFilter.getDataInicial() != null) {
			criteria.add(Restrictions.ge("dataAtendimento", atendimentoFilter.getDataInicial()));
		}
		
		if (atendimentoFilter.getDataFinal() != null) {
			criteria.add(Restrictions.le("dataAtendimento", atendimentoFilter.getDataFinal()));
		}
		
		if(StringUtils.isNotBlank(atendimentoFilter.getAdvogado().getUsuario().getNome())){
			criteria.add(Restrictions.ilike("ad.usuario.nome", atendimentoFilter.getAdvogado().getUsuario().getNome(), MatchMode.ANYWHERE));
		}
		
		if(StringUtils.isNotBlank(atendimentoFilter.getAssistido().getNome())){
			criteria.add(Restrictions.ilike("cliente.nome", atendimentoFilter.getAssistido().getNome()));
		}
		
		if (atendimentoFilter.getStatusAtendimento() != null && atendimentoFilter.getStatusAtendimento().length > 0) {
			// adicionamos uma restrição "in", passando um array de constantes da enum StatusPedido
			criteria.add(Restrictions.in("statusAtendimento", atendimentoFilter.getStatusAtendimento()));
		}
		
		return criteria.addOrder(Order.asc("cliente.nome")).list(); 
	}

}
