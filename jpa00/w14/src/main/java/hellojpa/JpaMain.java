package hellojpa;

import hellojpa.domain.Address;
import hellojpa.domain.Member;
import hellojpa.domain.Team;

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
            em.flush();
            em.clear();
            //String name = "new Name";
            //TypedQuery<Member> selectMFromMemberM = em.createQuery("SELECT m FROM Member m WHERE m.name = :username", Member.class);
            //TypedQuery<String> selectMFromMemberName = em.createQuery("SELECT m.name FROM Member m", String.class);
            //Query query = em.createQuery("SELECT m.age, m.name FROM Member m");

            //selectMFromMemberM.setParameter("username", name);
//            List<Member> resultList = selectMFromMemberM.getResultList();
//            for(Member mem : resultList){
//                System.out.println(member.getName());
//            }
//            try {
//                Member singleResult = selectMFromMemberM.getSingleResult();
//            } catch (NoResultException ex){
//                System.out.println("Null");
//            }
//
            List<Member> resultList = em.createQuery("select distinct m from Member m ", Member.class).getResultList();
            List<Team> team = em.createQuery("select m.team from Member m join m.team", Team.class).getResultList();
            List<Address> address = em.createQuery("select o.address from Order o", Address.class).getResultList();


            Member findMember = resultList.get(0);
            findMember.setAge(100);


            List<Member> memberList = em.createQuery("select m from Member m order by m.age", Member.class)
                    .setFirstResult(0)
                    .setMaxResults(10)
                    .getResultList();
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
