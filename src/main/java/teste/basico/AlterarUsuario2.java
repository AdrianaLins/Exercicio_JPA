package teste.basico;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.basico.Usuario;

public class AlterarUsuario2 {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercicios-jpa");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Usuario usuario = em.find(Usuario.class, 6L); //entidade no estado gerenciado pq esta dentro do transaction
		//objeto não gerenciado vai precisar do merge para fazer o UPDATE
		//objeto gerenciado não precisa do merge
		usuario.setNome("Marcela Aragão");
		
		//em.merge(usuario); //merge é responsável por pegar um objeto/usuário que esta no banco e fazer um UPDATE/ALTERAÇÃO
		
		em.getTransaction().commit();
		
		em.close();
		emf.close();

	}

}
