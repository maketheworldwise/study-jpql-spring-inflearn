package jpql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import jpql.domain.Member;

public class JpaMain {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			Member member = new Member();
			member.setName("member1");

			em.persist(member);

			TypedQuery<Member> typedQuery1 = em.createQuery("SELECT m FROM Member m", Member.class);
			List<Member> resultList = typedQuery1.getResultList();

			TypedQuery<Member> typedQuery2 = em.createQuery("SELECT m FROM Member m WHERE m.id = 1", Member.class);
			Member result = typedQuery2.getSingleResult();

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}

		emf.close();
	}
}
