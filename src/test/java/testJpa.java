import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class testJpa {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConcertPU");
        EntityManager em = emf.createEntityManager();
        System.out.println("pu chagé");
    }
}
