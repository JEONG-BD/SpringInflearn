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

//        try {
//            Member findMember = em.find(Member.class, 1L);
//            //System.out.println(findMember.getName());
//            //System.out.println(findMember.getId());
//            System.out.println(findMember);
//            //em.remove(findMember);
//            findMember.setName("TestA");
//            //tx.commit();
//        } catch (Exception e){
//            tx.rollback();
//            System.out.println("Exception");
//        } finally {
//            em.close();
//        }
//        emf.close();
//
//
        try{
//            // 비영속 상태
//            Member member = new Member();
//            member.setName("TestA");
//            member.setId(101L);
//
//            //영속 상태
//            System.out.println("======BEFORE======");
//            em.persist(member);
//            System.out.println("======AFTER======");
            //준영속 상태
            //em.detach(member);

            //삭제 상태
            //em.remove(member);
//            System.out.println("======COMMIT======");
//            Member findMember1 = em.find(Member.class, 101L);
//            Member findMember2 = em.find(Member.class, 101L);
//            System.out.println(findMember1 == findMember2);
//            System.out.println("find Member.getName = " + findMember1.getName());
//            System.out.println("find Member.getId =" + findMember1.getId());
//            for (int i=0; i< 5; i++){
//                Member member = new Member(10L + i, "A");
//                em.persist(member);
//                System.out.println("==Insert===");
//            }
//            Member member = em.find(Member.class, 11L);
//            member.setName("B");

//            em.detach(member);
//            em.clear();
//            System.out.println("======================");
//            tx.commit();


        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
