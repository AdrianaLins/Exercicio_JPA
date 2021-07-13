package teste.basico;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.basico.Usuario;

public class NovoUsuario {
	
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercicios-jpa");
		EntityManager em = emf.createEntityManager();
		
		Usuario novoUsuario = new Usuario("Marcela", "marcela@gmail.com");
		
		em.getTransaction().begin();
		em.persist(novoUsuario); //gerando um insert no banco de dados
		em.getTransaction().commit();
		
		System.out.println("O ID gerado foi: " + novoUsuario.getId());
		
		em.close();
		emf.close();
		
	}

}
