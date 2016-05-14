import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.sgnpj.model.Advogado;
import com.sgnpj.model.AreaAtuacao;
import com.sgnpj.model.Perfil;
import com.sgnpj.model.Situacao;
import com.sgnpj.model.Usuario;


public class Teste {
	public static void main(String[] args) throws ParseException {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProcessoPU");
		EntityManager manager = factory.createEntityManager();

		EntityTransaction trx = manager.getTransaction();
		trx.begin();
		
		Advogado ad = new Advogado(null, "454872458", null, new Date(), AreaAtuacao.TRABALHISTA, Situacao.ATIVO, "Rua Palmerim", 22, "APTO 201", "Piam", "Belford Roxo", "RJ", "26115580", "08312074694", "27581818", "967818840");
		
		ad = manager.merge(ad);
		
		List<Perfil> perfis = new ArrayList<Perfil>();
		
		Perfil p = new Perfil(null, "ADMINISTRADOR", "Aministrador");
		Perfil p2 = new Perfil(null, "ADVOGADO", "Advogado");
		Perfil p3 = new Perfil(null, "ESTAGIARIO", "Estagiario");
		
		p = manager.merge(p);
		p2 = manager.merge(p2);
		p3 = manager.merge(p3);
		
		perfis.add(p);
		
		Usuario usuario = new Usuario(null, "Vitor Felipe", "vitorfelipep@gmail.com", "91067132", perfis, null, null);
		
		usuario = manager.merge(usuario);
		
		ad.setUsuario(usuario);
		
		ad = manager.merge(ad);
		
		usuario.setAdvogado(ad);
		
		usuario = manager.merge(usuario);
		
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
		
		
		//Dou comit da transação
		trx.commit();
	}
}
