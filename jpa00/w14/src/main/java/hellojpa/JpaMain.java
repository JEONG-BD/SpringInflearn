package hellojpa;

import hellojpa.domain.Address;
import hellojpa.domain.Member;
import hellojpa.domain.MemberType;
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
            Team team = new Team();
            team.setName("LiverFool");
            em.persist(team);
            Member member = new Member();
            member.setName("new Name");
            member.setAge(30);
            member.setTeam(team);
            member.setType(MemberType.ADMIN);
            em.persist(member);
//            for (int i=0; i<=100; i++){
//                Member mem = new Member();
//                mem.setAge(i);
//                mem.setName("member" + i);
//                em.persist(mem);
//            }


            em.flush();
            em.clear();
//            String query = "select m from Member m inner join m.team t";
//            List<Member> memberList1 = em.createQuery(query, Member.class).getResultList();

//            String name = "new Name";
//            TypedQuery<Member> selectMFromMemberM = em.createQuery("SELECT m FROM Member m WHERE m.name = :username", Member.class);
//            TypedQuery<String> selectMFromMemberName = em.createQuery("SELECT m.name FROM Member m", String.class);
//            Query query = em.createQuery("SELECT m.age, m.name FROM Member m");
//
//            selectMFromMemberM.setParameter("username", name);
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
//            List<Member> resultList = em.createQuery("select distinct m from Member m ", Member.class).getResultList();
//            List<Team> team2 = em.createQuery("select m.team from Member m join m.team", Team.class).getResultList();
//            List<Address> address = em.createQuery("select o.address from Order o", Address.class).getResultList();
//            Member findMember = resultList.get(0);
//            findMember.setAge(100);
//            List<Member> memberList = em.createQuery("select m from Member m order by m.age", Member.class)
//                    .setFirstResult(0)
//                    .setMaxResults(10)
//                    .getResultList();
//
//            for(Member row : memberList){
//                System.out.println("Member -- :" + row);
//            }
//
//
            String query = "select m.name, m.type, 'HELLO', TRUE  from Member m where m.type =hellojpa.domain.MemberType.ADMIN";

            List<Object[]> resultList = em.createQuery(query).getResultList();
            for(Object[] objects: resultList){
                System.out.println("object [0] =" + objects[0]);
                System.out.println("object [0] =" + objects[1]);
                System.out.println("object [0] =" + objects[2]);
                System.out.println("object [0] =" + objects[3]);

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
