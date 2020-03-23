package mins.mall;

import mins.mall.domain.member.Address;
import mins.mall.domain.member.Member;
import mins.mall.domain.member.Team;
import mins.mall.domain.order.Order;
import mins.mall.domain.order.OrderStatus;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("minsMall");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Team team = new Team("teamA");
        em.persist(team);

        Address address = new Address("seoul", "dapsibri", "11-223");

        Member member = new Member("minssogi", address);
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


        System.out.println("##################################################");
        System.out.println("##################################################");

        // proxy example
        Member testMember = new Member();
        testMember.setName("test123");

        em.persist(testMember);
        em.flush();
        em.clear();

//        Member findTestMember = em.find(Member.class, 2L);
        PersistenceUnitUtil persistenceUnitUtil = emf.getPersistenceUnitUtil();
        Member findTestMember = em.getReference(Member.class, 2L); // proxy object
        System.out.println("### findTestMember isLoaded? " + persistenceUnitUtil.isLoaded(findTestMember));
        System.out.println("### Member class == findTestMember.class ? " + (findTestMember instanceof Member));
        System.out.println("### findTestMember.class = " + findTestMember.getClass());

        Hibernate.initialize(findTestMember); // 강제 초기화
        System.out.println("### findTestMember isLoaded? " + persistenceUnitUtil.isLoaded(findTestMember));
        System.out.println("### Member class == findTestMember.class ? " + (findTestMember instanceof Member));
        System.out.println("### findTestMember.name = " + findTestMember.getName());

        System.out.println("### findTestMember.team.name = " + findTestMember.getTeam().getName());



        System.out.println("##################################################");
        System.out.println("##################################################");

        // cascade + orphanEntity
        Order order1 = new Order();
        order1.setStatus(OrderStatus.ORDER);
        Order order2 = new Order();
        order2.setStatus(OrderStatus.CANCEL);

        Member newMember = new Member();
        newMember.setName("new");

        order1.setMember(newMember);
        order2.setMember(newMember);

        em.persist(newMember);

        em.flush();
        em.clear();

        Member findNewMember = em.find(Member.class, newMember.getId());
        System.out.println("##### persistenceUnitUtil.isLoaded? " + persistenceUnitUtil.isLoaded(findNewMember));
        System.out.println("##### findNewMember.getName = " + findNewMember.getName());
        findNewMember.getOrders().forEach(order -> System.out.println("##### " + order.toString()));

        findNewMember.getOrders().remove(0);
        em.remove(findNewMember);

        tx.commit();

        em.close();
        emf.close();
    }
}
