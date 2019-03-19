package ro.sda.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
    private static final EntityManagerFactory emf = Persistence
            .createEntityManagerFactory("SDAORM");

    private static EntityManager em = emf.createEntityManager();

    public static EntityManager getEM() {

        if(em == null || !em.isOpen()){
            em = emf.createEntityManager();
        }

        return em;
    }


    public static void closeEMF() {

        if(em != null && em.isOpen()) {
            em.close();
        }

        if(emf != null && emf.isOpen()) {
            emf.close();
        }
    }

}
