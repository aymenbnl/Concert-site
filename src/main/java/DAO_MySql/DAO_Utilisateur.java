package DAO_MySql;

import pojo_MySql.Utilisateur;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class DAO_Utilisateur extends DAO<Utilisateur>{

    private EntityManager em = null;
    @Override
    public Utilisateur find(int id) throws DAOException {
        Query query = em.createNamedQuery("Utilisateur.findById");
        query.setParameter("id", id);

        return (Utilisateur) query.getSingleResult();
    }

    public List<Utilisateur> getUtilisateurs() throws DAOException {
        Query query = em.createNamedQuery("Utilisateur.findAll");

        return (List<Utilisateur>) query.getResultList();
    }

    @Override
    public void create(Utilisateur utilisateur) throws DAOException {
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(utilisateur);
            trans.commit();
        } catch (Exception e) {
            if (trans != null) trans.rollback();
            throw new DAOException("Probl�me technique (" + e.getMessage() + ")");
        }
    }

    @Override
    public void update(Utilisateur utilisateur) throws DAOException {
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
    public void delete(Utilisateur utilisateur) throws DAOException {
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.remove(utilisateur);
            trans.commit();
        } catch (Exception e) {
            if (trans != null) trans.rollback();
            throw new DAOException("Probl�me technique (" + e.getMessage() + ")");
        }
    }

    public DAO_Utilisateur() throws DAOException {
        super();
        this.em = (EntityManager) Persistence.createEntityManagerFactory("ConcertPU").createEntityManager();
    }
}
