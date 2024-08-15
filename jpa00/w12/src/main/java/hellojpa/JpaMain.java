package hellojpa;

import hellojpa.domain.Address;
import hellojpa.domain.AddressEntity;
import hellojpa.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {

            Member member = new Member();
            member.setName("User1");
            member.setAddress(new Address("seoul", "seoul-ro", "123-456"));

            member.getFavoriteFoods().add("Pizza1");
            member.getFavoriteFoods().add("Pizza2");
            member.getFavoriteFoods().add("Pizza3");

//            member.getAddressList().add(new Address("city1", "street2", "zip3"));
//            member.getAddressList().add(new Address("city4", "street5", "zip6"));

            em.persist(member);
            em.flush();
            em.clear();
            // 조회
            System.out.println("Select ==========START==========");
            Member findMember = em.find(Member.class, member.getId());
            List<AddressEntity> addressList = findMember.getAddressList();
            for (AddressEntity addr : addressList){
                System.out.println(addr.getCity());
            }

            Set<String> favoriteFoods = findMember.getFavoriteFoods();
            for(String food : favoriteFoods){
                System.out.println(food);
            }
            System.out.println("Update ==========START==========1");
            //findMember.getAddress().setCity("New City");
            findMember.setAddress(new Address("city1", "streetNew", "1234-5678"));
            findMember.getFavoriteFoods().remove("Pizza1");
            findMember.getFavoriteFoods().add("Rice");

            System.out.println("Update ==========START==========2");

            findMember.getAddressList().remove(new AddressEntity("city1", "street2", "zip3"));
            findMember.getAddressList().add(new AddressEntity("new1", "new street2", "new zip3"));
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
