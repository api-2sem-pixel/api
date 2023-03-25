package dao;

import javax.persistence.EntityManager;

import model.Teste;

public class TesteDAO {

	private EntityManager em;
	
	public TesteDAO(EntityManager em) {
		this.em = em;
	}
	
	public void insert(Teste teste) {
		this.em.persist(teste);
	}
	
}
