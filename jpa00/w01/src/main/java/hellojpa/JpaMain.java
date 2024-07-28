package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
//
//        try {
//            Member member = new Member();
//            member.setId(1L);
//            member.setName("hello");
//            em.persist(member);
//            tx.commit();
//
//        } catch (Exception e) {
//            tx.rollback();
//        } finally {
//            em.close();
//        }
//        emf.close();
//
        try {
            Member findMember = em.find(Member.class, 1L);
            //System.out.println(findMember.getName());
            //System.out.println(findMember.getId());
            System.out.println(findMember);
            //em.remove(findMember);
            findMember.setName("TestA");
            //tx.commit();
        } catch (Exception e){
            tx.rollback();
            System.out.println("Exception");
        } finally {
            em.close();
        }
        emf.close();
    }
}
