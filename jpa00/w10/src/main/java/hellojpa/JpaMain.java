package hellojpa;

import hellojpa.domain.Child;
import hellojpa.domain.Member;
import hellojpa.domain.Parent;
import hellojpa.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transaction;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();


        tx.begin();


        try {

//            Member member = new Member();
//            member.setName("ProxyName2");
//            Team team = new Team();
//            team.setTeamname("TestTeam3");
//            member.setTeam(team);
//            em.persist(member);
//
//            Member refMember = em.getReference(Member.class, member.getId());
//
//
//            System.out.println("refMember" + refMember.getClass());
//            System.out.println(refMember.getName());
            Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            em.persist(parent);
            //em.persist(child1);
            //em.persist(child2);
            em.flush();
            em.clear();

            Parent findParent = em.find(Parent.class, parent.getId());
            findParent.getChildList().remove(0);
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
