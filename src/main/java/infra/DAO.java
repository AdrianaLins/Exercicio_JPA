package infra;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

//pode usar essa classe para qualquer entidade. Pois � uma classe gen�rica
public class DAO<E> {
	
	private static EntityManagerFactory emf; //f�brica de conex�o. static porque ser� um �nico banco de dados.
	private EntityManager em;
	private Class<E> classe;
	
	
	static {
		try {
			emf = Persistence.createEntityManagerFactory("exercicios-jpa"); //unidade de persist�ncia
		} catch (Exception e) {
			
		}
	}
	
	//construtor padr�o
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
		return this; //retorna o pr�prio DAO.
	}
	
	public DAO<E> incluir(E entidade) {
		em.persist(entidade);
		return this;
	}
	
	//m�todo que vai fazer a inclus�o j� com as transa��es. Abertura e fechamento da transa��o. Encadeamento
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
		query.setMaxResults(limit); //qtd m�xima de resultados.
		query.setFirstResult(offset); //desclocar o in�cio da consulta.
		
		return query.getResultList();
	}
	
	public List<E> obterTodos() {
		return this.obterTodos(10, 0);
	}
	
	public void fechar() {
		em.close();
	}
	
	
	
	
	
}
