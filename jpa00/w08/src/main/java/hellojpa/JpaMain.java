package hellojpa;

import hellojpa.domain.Movie;

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
            Movie movie = new Movie();
            movie.setDirector("directorA");
            movie.setActor("bbb");
            movie.setName("바람");
            movie.setPrice(100000);
            em.persist(movie);
            em.flush();
            em.clear();

            Movie findMovie = em.find(Movie.class, movie.getId());
            System.out.println(findMovie + "find Movie");
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
