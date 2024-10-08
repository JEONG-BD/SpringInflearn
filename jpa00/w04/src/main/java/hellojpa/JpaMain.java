package hellojpa;

import hellojpa.domain.Member;
import hellojpa.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            Member member = new Member();
            member.setUserName("memberA");

            em.persist(member);

            Team team = new Team();
            team.setName("teamA");
            team.getMembers().add(member) ;
            em.persist(team);

            tx.commit();

        }catch (Exception ex){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
