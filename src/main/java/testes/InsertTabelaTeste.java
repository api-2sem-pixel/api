package testes;

import javax.persistence.EntityManager;

import dao.TesteDAO;
import model.Teste;
import util.JPAUtil;

public class InsertTabelaTeste {

	public static void main(String[] args) {
		Teste teste = new Teste("Teste 1");
		
		EntityManager em = JPAUtil.getEntityManager();
		TesteDAO dao = new TesteDAO(em);
		
		em.getTransaction().begin();
		dao.insert(teste);
		em.getTransaction().commit();
		em.close();

	}

}
