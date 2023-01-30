package jpql;

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

			// 반환 타입 명확할 때
			TypedQuery<Member> typedQuery1 = em.createQuery("SELECT m FROM Member m", Member.class);
			TypedQuery<String> typedQuery2 = em.createQuery("SELECT m.name FROM Member m", String.class);

			// 반환 타입 불명확할 때
			Query query = em.createQuery("SELECT m.name FROM Member m");

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
