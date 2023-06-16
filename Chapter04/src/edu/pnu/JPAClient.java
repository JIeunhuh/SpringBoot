package edu.pnu;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import edu.pnu.domain.Board;

public class JPAClient {

	static void insertData(EntityManager em) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			Board b = new Board();
			for (int i = 0; i < 10; i++) {
				b.setTitle("Title");
				b.setWriter("Writer");
				b.setContent("Content");
			}
		} catch (Exception e) {
			tx.rollback();
		}
	}

	public static void main(String[] args) {
		System.out.println("First JPA");

		// EntityManager 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
		EntityManager em = emf.createEntityManager();
//		
//		EntityTransaction tx = em.getTransaction();
//		tx.begin();
//		
//		Board b = new Board();
//		b.setTitle("Title");
//		b.setWriter("Writer");
//		b.setContent("Content");
//		b.setCreateDate(new Date());
//		b.setCnt(0L);
//		
//		em.persist(b);
//		tx.commit();

		insertData(em);

		em.close();
		emf.close();
	}
}
