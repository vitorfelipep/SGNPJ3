import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.sgnpj.model.Usuario;


public class Teste {
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProcessoPU");
		EntityManager manager = factory.createEntityManager();
		
		EntityTransaction trx = manager.getTransaction();
		trx.begin();
		Usuario usu = new Usuario();
		
		usu.setNome("Clara");
		usu.setEmail("clarinha@mail.com");
		usu.setSenha("54321");
		
	
		manager.persist(usu);
		
		trx.commit();
		
		
	}
}
