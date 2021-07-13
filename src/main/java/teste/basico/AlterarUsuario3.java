package teste.basico;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.basico.Usuario;

public class AlterarUsuario3 {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercicios-jpa");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Usuario usuario = em.find(Usuario.class, 6L); 
		em.detach(usuario); //DETACH � a fun��o respons�vel por tirar o objeto do estado gerenciado
		
		usuario.setNome("Marcela Arag�o");
		
		
		em.merge(usuario); //merge � respons�vel por pegar um objeto/usu�rio que esta no banco e fazer um UPDATE/ALTERA��O
		
		em.getTransaction().commit();
		
		em.close();
		emf.close();

	}

}
