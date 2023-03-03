package DAO_MySql;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import pojo_MySql.Admin;

public class DAO_Admin extends DAO<Admin> {
	private EntityManager em = null;
	
    @Override
    public Admin find(int id) throws DAOException {
        Query query = em.createNamedQuery("Admin.findById");
        query.setParameter("id", id);

        return (Admin) query.getSingleResult();
    }
    
    public Admin findByLoginAndPassword(String login, String mdp) throws DAOException {
        System.err.println(login + " " + mdp);
        Query query = em.createNamedQuery("Admin.findByLoginAndPassword");
        query.setParameter("login", login);
        query.setParameter("mdp", mdp);

        return (Admin) query.getSingleResult();
    }
    
    @Override
    public void create(Admin admin) throws DAOException {
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(admin);
            trans.commit();
        } catch (Exception e) {
            if (trans != null) trans.rollback();
            throw new DAOException("Probl�me technique (" + e.getMessage() + ")");
        }
    }

    @Override
    public void update(Admin admin) throws DAOException {
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
    public void delete(Admin admin) throws DAOException {
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.remove(admin);
            trans.commit();
        } catch (Exception e) {
            if (trans != null) trans.rollback();
            throw new DAOException("Probl�me technique (" + e.getMessage() + ")");
        }
    }
	
    public DAO_Admin() throws DAOException {
        super();
        this.em = (EntityManager) Persistence.createEntityManagerFactory("ConcertPU").createEntityManager();
    }
}
