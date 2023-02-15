package DAO_MySql;

import pojo_MySql.Artiste;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class DAO_Artiste extends DAO<Artiste>{

    private EntityManager em = null;

    @Override
    public Artiste find(int id) throws DAOException {
        Query query = em.createNamedQuery("Artiste.FindById");
        query.setParameter("id", id);

        return (Artiste) query.getSingleResult();
    }

    public List<Artiste> getArtistes() throws DAOException {
        Query query = em.createNamedQuery("Artiste.FindAll");

        return (List<Artiste>) query.getResultList();
    }

    @Override
    public void create(Artiste artiste) throws DAOException {
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(artiste);
            trans.commit();
        } catch (Exception e) {
            if (trans != null) trans.rollback();
            throw new DAOException("Probl�me technique (" + e.getMessage() + ")");
        }
    }

    @Override
    public void update(Artiste artiste) throws DAOException {
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            trans.commit();
        } catch (Exception e) {
            if (trans != null) trans.rollback();
            throw new DAOException("Probl�me technique (" + e.getMessage() + ")");
        }
    }

    @Override
    public void delete(Artiste artiste) throws DAOException {
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.remove(artiste);
            trans.commit();
        } catch (Exception e) {
            if (trans != null) trans.rollback();
            throw new DAOException("Probl�me technique (" + e.getMessage() + ")");
        }
    }

    public DAO_Artiste() throws DAOException {
        super();
        this.em = (EntityManager) Persistence.createEntityManagerFactory("ConcertPU").createEntityManager();
    }
}
