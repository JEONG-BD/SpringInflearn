package hellojpa;

import hellojpa.domain.Member;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member member = new Member();
            member.setName("new Name");
            member.setAge(30);
            em.persist(member);
            String name = "new Name";
            TypedQuery<Member> selectMFromMemberM = em.createQuery("SELECT m FROM Member m WHERE m.name = :username", Member.class);
            //TypedQuery<String> selectMFromMemberName = em.createQuery("SELECT m.name FROM Member m", String.class);
            //Query query = em.createQuery("SELECT m.age, m.name FROM Member m");

            selectMFromMemberM.setParameter("username", name);
            List<Member> resultList = selectMFromMemberM.getResultList();
            for(Member mem : resultList){
                System.out.println(member.getName());
            }
            try {
                Member singleResult = selectMFromMemberM.getSingleResult();
            } catch (NoResultException ex){
                System.out.println("Null");
            }
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            ex.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}
