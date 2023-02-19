package DAO_MySql;

import pojo_MySql.Soiree;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class DAO_Soiree extends DAO<Soiree>{

    private EntityManager em = null;
    @Override
    public Soiree find(int id) throws DAOException {
        Query query = em.createNamedQuery("Soiree.findById");
        query.setParameter("id", id);

        return (Soiree) query.getSingleResult();
    }

    public List<Soiree> getSoirees() throws DAOException {
        Query query = em.createNamedQuery("Soiree.findAll");

        return (List<Soiree>) query.getResultList();
    }

    @Override
    public void create(Soiree soiree) throws DAOException {
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(soiree);
            trans.commit();
        } catch (Exception e) {
            if (trans != null) trans.rollback();
            throw new DAOException("Probl�me technique (" + e.getMessage() + ")");
        }
    }

    @Override
    public void update(Soiree soiree) throws DAOException {
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
    public void delete(Soiree soiree) throws DAOException {
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.remove(soiree);
            trans.commit();
        } catch (Exception e) {
            if (trans != null) trans.rollback();
            throw new DAOException("Probl�me technique (" + e.getMessage() + ")");
        }
    }

    public DAO_Soiree() throws DAOException {
        super();
        this.em = (EntityManager) Persistence.createEntityManagerFactory("ConcertPU").createEntityManager();
    }
}
