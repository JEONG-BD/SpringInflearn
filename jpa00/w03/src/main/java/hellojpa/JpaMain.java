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
        tx.begin();

        try {
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);


            Member member = new Member();
            member.setName("memberA");
            //member.setTeam(team.getId());
            member.setTeam(team);
            em.persist(member);

            tx.commit();

            Member findMember = em.find(Member.class, member.getId());
            //System.out.println(findMember.getTeamId());
            System.out.println(findMember.getName());

            Team findTeam = findMember.getTeam();
            //Team findTeam = em.find(Team.class, teamId);
            System.out.println(findTeam.getName());


        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
