import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.sgnpj.model.AreaAtuacao;
import com.sgnpj.model.Assistido;
import com.sgnpj.model.AssistidoContraParte;
import com.sgnpj.model.Atendimento;
import com.sgnpj.model.EstadoCivilAssistido;
import com.sgnpj.model.PessoaFisica;
import com.sgnpj.model.Processo;
import com.sgnpj.model.SituacaoAssitido;
import com.sgnpj.model.StatusAtendimento;
import com.sgnpj.model.TipoEndereco;
import com.sgnpj.model.Triagem;


public class Teste {
	public static void main(String[] args) throws ParseException {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProcessoPU");
		EntityManager manager = factory.createEntityManager();

		
		EntityTransaction trx = manager.getTransaction();
//		trx.begin();
//		
//		//Inicio a pessoa Fisica
//		PessoaFisica pf = new PessoaFisica("08312074694", new Date(), "215648504", "dicrj", "21245454", "215454484", "Fulano", "Cliclana", EstadoCivilAssistido.CASADO, "ANALISTA", "Brasileiro", "RJ", "M", "N", null);
//		//Cadastro a pessoa fisíca
//		pf = manager.merge(pf);
//		
//		
//		//Inicio a triagem
//		Triagem tr = new Triagem(0, 0, 0, BigDecimal.valueOf(2500.), 4000., "SIM", "NÂO", "NA", "TESTE", null);
//		//Cadastro a triagem
//		tr = manager.merge(tr);
//		//inicio a pessoa fisica ou juridica da parte contraria
//		PessoaFisica pfc = new PessoaFisica("08312074694", new Date(), "214545448", "dicrh", "45212555", "452821444", "NA", "NA", EstadoCivilAssistido.SOLTEIRO, "Vendedor", "Brasileiro", "Rio de Janeiro", "M", "N", null);
//		//Salvo a pessoa da parte contraria
//		pfc = manager.merge(pfc);
//		
//		//adiciono em uma lista
//		List<AssistidoContraParte> contraPartes = new ArrayList<AssistidoContraParte>();
//		
//		//inicio a parte contraria
//		AssistidoContraParte ac = new AssistidoContraParte(null,"asasd", "REU", "26458110", "RJ", "bELFORD Roxo", "xaxa", "Rua XX", 45, "apto 34", TipoEndereco.ALUGADO, "xx@gmail.com", pfc, null, null);
//		Assistido a = new Assistido(null,"Vitor", "Autor", "26115580", "RJ", "Belford Roxo", "Piam", "Rua Palmerim", 22, "", TipoEndereco.ALUGADO, "vitor@gmail.com", SituacaoAssitido.EM_ASSISTENCIA, pf, null, tr, contraPartes);
//		
//		//salvo o assistido com sucesso!
//		a = manager.merge(a);
//		
//			
//		tr.setAssistidotriagem(a);
//		
//		pf = manager.merge(pf);
//		tr = manager.merge(tr);
//		
//		ac.setAssistidoAutor(a);
//		
//		ac = manager.merge(ac);
//		
//		//inicio o assistido e adiciono as partes a ele
//		
//		a.adicionarAssistidoContraParte(ac);
//		
//		//salvo o assistido com sucesso!
//		a = manager.merge(a);
//		
//		List<Processo> process = new ArrayList<Processo>();
//		Atendimento at = new Atendimento(null, a, null, null,StatusAtendimento.EM_ANDAMENTO, "Teste de atendimento", new Date(), AreaAtuacao.CIVIL.getDescricao(), process);
//		
//		at = manager.merge(at);
//		//Dou comit da transação
//		trx.commit();
		
		
	}
}
