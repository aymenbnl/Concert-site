package DAO_MySql;

import pojo_MySql.Groupe;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class DAO_Groupe extends DAO<Groupe> {

    private EntityManager em = null;
    @Override
    public Groupe find(int id) throws DAOException {
        Query query = em.createNamedQuery("Groupe.findById");
        query.setParameter("id", id);

        return (Groupe) query.getSingleResult();
    }

    public List<Groupe> getGroupe() throws DAOException {
        Query query = em.createNamedQuery("Groupe.findAll");

        return (List<Groupe>) query.getResultList();
    }

    @Override
    public void create(Groupe groupe) throws DAOException {
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(groupe);
            trans.commit();
        } catch (Exception e) {
            if (trans != null) trans.rollback();
            throw new DAOException("Probl�me technique (" + e.getMessage() + ")");
        }
    }

    @Override
    public void update(Groupe groupe) throws DAOException {
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
    public void delete(Groupe groupe) throws DAOException {
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.remove(groupe);
            trans.commit();
        } catch (Exception e) {
            if (trans != null) trans.rollback();
            throw new DAOException("Probl�me technique (" + e.getMessage() + ")");
        }
    }

    public DAO_Groupe() throws DAOException {
        super();
        this.em = (EntityManager) Persistence.createEntityManagerFactory("ConcertPU").createEntityManager();
    }
}
