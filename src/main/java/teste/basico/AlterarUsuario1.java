package teste.basico;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.basico.Usuario;

public class AlterarUsuario1 {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercicios-jpa");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Usuario usuario = em.find(Usuario.class, 6L);
		usuario.setNome("Marcela Aragão");
		usuario.setEmail("marcelaaragao@gmail.com");
		
		em.merge(usuario); //merge é responsável por pegar um objeto/usuário que esta no banco e fazer um UPDATE/ALTERAÇÃO
		
		em.getTransaction().commit();
		
		em.close();
		emf.close();

	}

}
