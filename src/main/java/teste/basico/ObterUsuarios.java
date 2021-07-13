package teste.basico;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import modelo.basico.Usuario;

public class ObterUsuarios {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercicios-jpa");
		EntityManager em = emf.createEntityManager();
		
		
		String jpql = "select u from Usuario u"; //quem vai converter isso para SQL � o JPA
		TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class); //criando a query passando o jpql e classe Usu�rio
		query.setMaxResults(5); //set da quantidade m�xima de resultados
		
		List<Usuario> usuarios = query.getResultList(); //Obter a lista de usu�rios a partir do banco de dados
		
		for(Usuario usuario: usuarios) {
			System.out.println("Id: " + usuario.getId() + " E-mail: " + usuario.getEmail());
		
		}
		
		em.close();
		emf.close();

	}

}
