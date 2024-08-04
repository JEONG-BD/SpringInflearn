package hellojpa;

import hellojpa.domain.MemberSimple;
import hellojpa.domain.TeamSimple;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            TeamSimple teamSimple = new TeamSimple();
            teamSimple.setName("TeamA");
            em.persist(teamSimple);


            MemberSimple memberSimple = new MemberSimple();
            memberSimple.setName("memberA");
            //member.setTeam(team.getId());
            memberSimple.setTeamSimple(teamSimple);
            em.persist(memberSimple);
            em.flush();
            em.clear();


            MemberSimple findMemberSimple = em.find(MemberSimple.class, memberSimple.getId());
            //System.out.println(findMember.getTeamId());
            System.out.println(findMemberSimple.getName());

            TeamSimple findTeamSimple = findMemberSimple.getTeamSimple();
            //Team findTeam = em.find(Team.class, teamId);
            System.out.println(findTeamSimple.getName());
            System.out.println("===========================");

            List<MemberSimple> memberSimples = memberSimple.getTeamSimple().getMemberSimples();
            for (MemberSimple mem : memberSimples){
                System.out.println(mem.getName());
            }

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
