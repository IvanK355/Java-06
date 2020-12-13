package dao;

import entities.Account;
import javax.persistence.*;

public class HibernateAccountDao implements Dao<Account> {

    private final String UPDATE_QUERY = "UPDATE Account e SET e.accountAmount = :increment WHERE e.id = :id";
    private final String DELETE_QUERY = "DELETE Account e WHERE e.id = :id";

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");

    @Override
    public void createNewTable() {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        for (int i = 1; i < 11; i++) {
            Account account = new Account(i, "Holder" + i, 500);
            em.persist(account);
        }
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Account create(Account account) {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(account);
        em.getTransaction().commit();
        em.close();
        return read(account.getId());
    }

    public Account read(long id) {

        EntityManager em = emf.createEntityManager();
        return em.find(Account.class, id);
    }

    @Override
    public Account update(Account account) {
        read(account.getId());
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery(UPDATE_QUERY);
        query.setParameter("increment", account.getAccountAmount());
        query.setParameter("id", account.getId());
        query.executeUpdate();

        em.getTransaction().commit();
        em.close();
        return read(account.getId());
    }

    @Override
    public Account delete(Account account) {
        read(account.getId());
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery(DELETE_QUERY);
        query.setParameter("id", account.getId());
        query.executeUpdate();

        em.getTransaction().commit();
        em.close();
        return null;
    }
}
