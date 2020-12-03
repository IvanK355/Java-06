import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class HiberAccDao {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");


    public void createNew() {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.createQuery("from Account", Account.class)
                    .getResultList()
                    .forEach(g -> System.out.printf("Account # %s, %s, %s, %s, %s, %s%n", g.getId(), g.getHolder(), g.getBeginSum(), g.getAmountOperation(), g.getEndSum(), g.getOperation()));
            em.getTransaction().commit();
            em.close();

            System.out.println();
        } catch (HibernateException hibernateEx) {
            hibernateEx.printStackTrace();
        } finally {
            em.close();

        }
    }

    public void deposit(Account account) {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("UPDATE Account e SET e.beginSum = e.endSum WHERE e.id = :id");
        Query query1 = em.createQuery("UPDATE Account e SET e.endSum = e.endSum + :increment WHERE e.id = :id");
        Query query2 = em.createQuery("UPDATE Account e SET e.amountOperation = :increment WHERE e.id = :id");
        Query query3 = em.createQuery("UPDATE Account e SET e.operation = 'deposit' WHERE e.id = :id");
        query.setParameter("id", account.getId());
        query1.setParameter("id", account.getId());
        query1.setParameter("increment", account.getAmountOperation());
        query2.setParameter("increment", account.getAmountOperation());
        query2.setParameter("id", account.getId());
        query3.setParameter("id", account.getId());
        try {
            query.executeUpdate();
            query1.executeUpdate();
            query2.executeUpdate();
            query3.executeUpdate();


            em.createQuery("from Account as c where c.id = ?1", Account.class)
                    .setParameter(1, account.getId())
                    .getResultList()
                    .forEach(g -> System.out.printf("Deposit account # %s, %s, %s, %s, %s, %s%n", g.getId(), g.getHolder(), g.getBeginSum(), g.getAmountOperation(), g.getEndSum(), g.getOperation()));
            em.getTransaction().commit();
            em.close();
            System.out.println();
        } catch (HibernateException hibernateEx) {
            hibernateEx.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void withdraw(Account account) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("UPDATE Account e SET e.beginSum = e.endSum WHERE e.id = :id");
        Query query1 = em.createQuery("UPDATE Account e SET e.endSum = e.endSum - :increment WHERE e.id = :id");
        Query query2 = em.createQuery("UPDATE Account e SET e.amountOperation = :increment WHERE e.id = :id");
        Query query3 = em.createQuery("UPDATE Account e SET e.operation = 'withdraw' WHERE e.id = :id");
        query.setParameter("id", account.getId());
        query1.setParameter("id", account.getId());
        query1.setParameter("increment", account.getAmountOperation());
        query2.setParameter("increment", account.getAmountOperation());
        query2.setParameter("id", account.getId());
        query3.setParameter("id", account.getId());
        try {
            query.executeUpdate();
            query1.executeUpdate();
            query2.executeUpdate();
            query3.executeUpdate();

            em.createQuery("from Account as c where c.id = ?1", Account.class)
                    .setParameter(1, account.getId())
                    .getResultList()
                    .forEach(g -> System.out.printf("Withdraw account # %s, %s, %s, %s, %s, %s%n", g.getId(), g.getHolder(), g.getBeginSum(), g.getAmountOperation(), g.getEndSum(), g.getOperation()));
            em.getTransaction().commit();
            em.close();
            System.out.println();
        } catch (HibernateException hibernateEx) {
            hibernateEx.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void balance(Account account) {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.createQuery("from Account as c where c.id = ?1", Account.class)
                    .setParameter(1, account.getId())
                    .getResultList()
                    .forEach(g -> System.out.printf("Balance account # %s, %s, %s, %s, %s, %s%n", g.getId(), g.getHolder(), g.getBeginSum(), g.getAmountOperation(), g.getEndSum(), g.getOperation()));
            em.getTransaction().commit();
            em.close();
            System.out.println();
        } catch (HibernateException hibernateEx) {
            hibernateEx.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void transfer(Account account1, Account account2) {
        balance(account1);
        balance(account2);
        withdraw(account1);
        deposit(account2);
        balance(account1);
        balance(account2);
    }
}
