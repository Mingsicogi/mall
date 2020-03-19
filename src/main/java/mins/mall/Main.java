package mins.mall;

import mins.mall.domain.Member;
import mins.mall.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("minsMall");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Team team = new Team("teamA");
        em.persist(team);

        Member member = new Member("minssogi", "seoul", "dapsibri", "11-223");
//        member.setTeamId(team.getId());
        member.setTeam(team);
        em.persist(member);

        Team teamA = em.find(Member.class, member.getId()).getTeam();
        System.out.println("\n##### " + teamA.toString() + " #####\n");

        tx.commit();

        em.close();
        emf.close();
    }
}
