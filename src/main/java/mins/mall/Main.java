package mins.mall;

import mins.mall.domain.member.Member;
import mins.mall.domain.member.Team;

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
        member.setTeam(team);
        em.persist(member);

        // 주인이 아닌 객체의 값은 읽기 전용임. 값을 셋팅해도 하이버네이트에선 flush시점에 업데이트 쿼리를 하지않음.
//        team.getMembers().add(member); // 양방향 연관관계의 경우 연관관계의 주인뿐만 아니라 주인을 정한 엔티티도 값을 셋팅하는 것을 추천.

//        em.flush();
//        em.clear();

        Team teamA = em.find(Member.class, member.getId()).getTeam();
        System.out.println("\n##### " + teamA.getName() + " #####\n");

        System.out.println("================================");
        teamA.getMembers().forEach(t -> System.out.println("##### " + t.getName()));
        System.out.println("================================");

        tx.commit();

        em.close();
        emf.close();
    }
}
