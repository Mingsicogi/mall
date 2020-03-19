package mins.mall;

import mins.mall.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("minsMall");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        Member member = new Member("minssogi", "seoul", "dapsibri", "11-223");

        tx.begin();
        em.persist(member);
        tx.commit();

        em.close();
        emf.close();
    }
}
