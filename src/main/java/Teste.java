import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.sgnpj.model.AreaAtuacao;
import com.sgnpj.model.Estagiario;
import com.sgnpj.model.Situacao;
import com.sgnpj.model.Usuario;


public class Teste {
	public static void main(String[] args) throws ParseException {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProcessoPU");
		EntityManager manager = factory.createEntityManager();
		
//		Advogado adv = new Advogado();
		Estagiario est = new Estagiario();
		AreaAtuacao areaAtuacao = null;
		Situacao situacao = null;
		EntityTransaction trx = manager.getTransaction();
		trx.begin();
		Usuario usu = new Usuario();
		
//		est.setAreaDesignada(areaAtuacao.CIVIL);
//		est.setCelularContato("98745425");
//		est.setCodOab("45458720");
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//		
//		Date dataNascimento = sdf.parse("05/02/1988");
//		est.setDataNascimento(dataNascimento);
//		Date dataInicio = new Date();
//		est.setDataInicio(dataInicio);
//		est.setMatricula(171210324);
//		est.setNome("Pedro Nunes");
//		est.setObsEstagio("Teste");
//		est.setPeriodo("5º periodo");
//		est.setSituacao(situacao.ATIVO);
//		est.setTurno("Noite");
//		usu = manager.find(Usuario.class, 6L);
//		est.setUsuario(usu);
//		est.setTurma("102");
//		est.setTelefoneContato("27581818");
		
		usu = manager.find(Usuario.class, 6L);
		est = manager.find(Estagiario.class, 1L);
		usu.setEstagiario(est);
		
//		System.out.println(adv.getAreaAtuacao().getDescricao());
		
		manager.merge(est);
		
		trx.commit();
		
		
	}
}
