package hellojpa;

import hellojpa.domain.Address;
import hellojpa.domain.Member;
import org.hibernate.metamodel.model.domain.internal.MapMember;

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
//            Member member = new Member();
//            member.setAddress(new Address("seoul", "123-456","Seoul-ro1"));
//            member.setName("new MemberA");
            Address address = new Address("seoul", "123-456", "seoul-ro");
            Member member1 = new Member();
            Member member2 = new Member();

            member1.setName("member1");
            member2.setName("member2");

            Address newAddress = new Address(address.getCity(), address.getAddress(), address.getZipCode());
            member1.setAddress(address);
            member2.setAddress(newAddress);
            System.out.println("=========");
            System.out.println(address.equals(newAddress));
            System.out.println("=========");

            em.persist(member1);
            em.persist(member2);

            member1.getAddress().setCity("Busan");

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
