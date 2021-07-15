package infra;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

//pode usar essa classe para qualquer entidade. Pois é uma classe genérica
public class DAO<E> {
	
	private static EntityManagerFactory emf; //fábrica de conexão. static porque será um único banco de dados.
	private EntityManager em;
	private Class<E> classe;
	
	
	static {
		try {
			emf = Persistence.createEntityManagerFactory("exercicios-jpa"); //unidade de persistência
		} catch (Exception e) {
			
		}
	}
	
	//construtor padrão
	public DAO() {
		this(null);
	}
	
	//construtor que recebe a classe.
	public DAO(Class<E> classe) {
		this.classe = classe;
		em = emf.createEntityManager();
	}
	
	public DAO<E> abrirTransacao() {
		em.getTransaction().begin();
		return this;
	}
	
	public DAO<E> fecharTransacao() {
		em.getTransaction().commit();
		return this; //retorna o próprio DAO.
	}
	
	public DAO<E> incluir(E entidade) {
		em.persist(entidade);
		return this;
	}
	
	//método que vai fazer a inclusão já com as transações. Abertura e fechamento da transação. Encadeamento
	public DAO<E> incluirAtomico(E entidade) {
		return this.abrirTransacao().incluir(entidade).fecharTransacao();
		
	}
	
	//limit - qtd de resgistro que quer a partir da consulta. offset - deslocamento
	public List<E> obterTodos(int limit, int offset) {
		if(classe == null) {
			throw new UnsupportedOperationException("Classe nula.");
		}
		
		String jpql = "select e from " + classe.getName() + " e"; //selecionando o nome da classe
		TypedQuery<E> query = em.createQuery(jpql, classe);
		query.setMaxResults(limit); //qtd máxima de resultados.
		query.setFirstResult(offset); //desclocar o início da consulta.
		
		return query.getResultList();
	}
	
	public List<E> obterTodos() {
		return this.obterTodos(10, 0);
	}
	
	public void fechar() {
		em.close();
	}
	
	
	
	
	
}
