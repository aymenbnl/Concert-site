package DAO_MySql;

import pojo_MySql.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class DAO_Ticket extends DAO<Ticket>{

    private EntityManager em = null;
    @Override
    public Ticket find(int id) throws DAOException {
        Query query = em.createNamedQuery("Ticket.findById");
        query.setParameter("id", id);

        return (Ticket) query.getSingleResult();
    }

    public List<Ticket> getTickets() throws DAOException {
        Query query = em.createNamedQuery("Ticket.findAll");

        return (List<Ticket>) query.getResultList();
    }

    @Override
    public void create(Ticket ticket) throws DAOException {
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(ticket);
            trans.commit();
        } catch (Exception e) {
            if (trans != null) trans.rollback();
            throw new DAOException("Probl�me technique (" + e.getMessage() + ")");
        }
    }

    @Override
    public void update(Ticket ticket) throws DAOException {
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
    public void delete(Ticket ticket) throws DAOException {
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.remove(ticket);
            trans.commit();
        } catch (Exception e) {
            if (trans != null) trans.rollback();
            throw new DAOException("Probl�me technique (" + e.getMessage() + ")");
        }
    }

    public DAO_Ticket() throws DAOException {
        super();
        this.em = (EntityManager) Persistence.createEntityManagerFactory("ConcertPU").createEntityManager();
    }
}
