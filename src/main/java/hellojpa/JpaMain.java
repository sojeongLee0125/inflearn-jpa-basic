package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        // EntityManagerFactory는 단 하나만 생성, 애플리케이션 전체 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // EntityManager는 고객 요청이 올때마다 생성, 쓰레드 간 공유하지 않음
        EntityManager em = emf.createEntityManager();
        // jpa에서는 트랜젝션 안에서 모든 데이터 변경이 실행
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("helloB");
//            em.persist(member);

            Member findMember = em.find(Member.class, 1l);
            findMember.setName("updateName");


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
