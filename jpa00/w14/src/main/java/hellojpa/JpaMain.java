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
            Team teamA = new Team();
            teamA.setName("teamA");
            Team teamB = new Team();
            teamB.setName("teamB");

            em.persist(teamA);
            em.persist(teamB);

            Member memberA = new Member();
            memberA.setAge(30);
            memberA.setName("member A");
            memberA.setTeam(teamA);
            memberA.setType(MemberType.ADMIN);

            Member memberB = new Member();
            memberB.setName("member B");
            memberB.setAge(30);
            memberB.setTeam(teamB);
            memberB.setType(MemberType.ADMIN);

            Member memberC = new Member();
            memberC.setName("member B");
            memberC.setAge(30);
            memberC.setTeam(teamB);
            memberC.setType(MemberType.ADMIN);

            em.persist(memberA);
            em.persist(memberB);
            em.persist(memberC);

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
//            String query = "select m.name, m.type, 'HELLO', TRUE  from Member m where m.type =hellojpa.domain.MemberType.ADMIN";
//
//            List<Object[]> resultList = em.createQuery(query).getResultList();
//            for(Object[] objects: resultList){
//                System.out.println("object [0] =" + objects[0]);
//                System.out.println("object [0] =" + objects[1]);
//                System.out.println("object [0] =" + objects[2]);
//                System.out.println("object [0] =" + objects[3]);
//
//            }
//
//            String query = "select " +
//                    "case when m.age <= 10 then '학생요금'" +
//                    "     when m.age >= 60 then '경로요금'" +
//                    "     else '일반요금' " +
//                    "end " +
//                    " from Member m";
//            List<String> resultList1 = em.createQuery(query, String.class).getResultList();
//
//            String query2 = "select coalesce(m.name, '이름없는 회원') as username from Member m ";
//            List<String> resultList2 = em.createQuery(query2, String.class).getResultList();
//
//
//            String query3 = "select nullif(m.name, '관리자') as username from Member m ";
//            List<String> resultList3 = em.createQuery(query3, String.class).getResultList();

//            String query = "select m.team.name from Member m ";
//           List<String> resultList = em.createQuery(query, String.class).getResultList();
            String memberQuery = "select m from Member m";

            List<Member> resultList = em.createQuery(memberQuery, Member.class).getResultList();
            for(Member row : resultList){
                System.out.println(row.getName() + " : " + row.getTeam().getName());
            }

            String memberFetchQuery = "select m from Member m join fetch m.team";
            List<Member> resultList2 = em.createQuery(memberFetchQuery, Member.class).getResultList();
            for(Member row : resultList2) {
                System.out.println(row.getName() + " : " + row.getTeam().getName());
            }

            String teamFetchQuery = "select distinct t from Team t join fetch t.memberList";
            List<Team> resultList3 = em.createQuery(teamFetchQuery, Team.class).getResultList();
            for(Team row : resultList3) {
                System.out.println(row.getName() + " : " + row.getMemberList());
                for (Member teamMember : row.getMemberList()){
                    System.out.println(teamMember.getName());
                }
            }

            List<Member> resultList4 = em.createNamedQuery("Member.findByUserName", Member.class)
                    .setParameter("username", "member B")
                    .getResultList();

            for(Member row : resultList4){
                System.out.println(row);
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
