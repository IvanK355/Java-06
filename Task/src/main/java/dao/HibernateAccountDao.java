package dao;

import entities.Account;
import service.UnknownAccountException;

import javax.persistence.*;

public class HibernateAccountDao implements Dao<Account> {

    private final String UPDATE_QUERY = "UPDATE Account e SET e.accountAmount = :increment WHERE e.id = :id";

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");

    @Override
    public void createNewTable() {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        for (int i = 1; i < 11; i++) {
            Account account = new Account("Holder" + i, 500);
            em.persist(account);
        }
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Account create(Account account) throws UnknownAccountException {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(account);
        em.getTransaction().commit();
        em.close();
        return read(account.getId());
    }

    public Account read(int id) {

        EntityManager em = emf.createEntityManager();
        return em.find(Account.class, id);
    }

    @Override
    public Account update(Account account) throws UnknownAccountException {
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
        return null;
    }
}
