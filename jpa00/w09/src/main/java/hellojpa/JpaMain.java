package hellojpa;

import hellojpa.domian.Book;

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
            Book book = new Book();
            book.setAuthor("헤밍웨이");
            book.setIshn("123456");
            em.persist(book);

            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
