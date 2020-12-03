import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");

        HiberAccDao hiberAccDao = new HiberAccDao();
        ArrayList<Account> accounts = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        for (int i = 1; i < 11; i++) {
            Account account = new Account();
            account.setHolder("Holder" + i);
            account.setBeginSum(0);
            account.setAmountOperation(500);
            account.setEndSum(500);
            account.setOperation("deposit");
            accounts.add(account);

            em.persist(account);

        }
        em.getTransaction().commit();
        em.close();

        hiberAccDao.createNew();


        // *  BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // * String s = reader.readLine();
        // * String[] array = s.split("\\W");
        accounts.get(1).setOperation("balance");
        accounts.get(2).setAmountOperation(100);
        accounts.get(2).setOperation("deposit");
        accounts.get(3).setAmountOperation(100);
        accounts.get(3).setOperation("withdraw");
        accounts.get(4).setAmountOperation(100);
        accounts.get(5).setAmountOperation(100);
        accounts.get(4).setOperation("transfer");
        accounts.get(5).setOperation("transfer");

        for (int i = 1; i < 5; i++) {

            switch (accounts.get(i).getOperation()) {
                case "balance" -> hiberAccDao.balance(accounts.get(i));
                case "withdraw" -> hiberAccDao.withdraw(accounts.get(i));
                case "deposit" -> hiberAccDao.deposit(accounts.get(i));
                case "transfer" -> hiberAccDao.transfer(accounts.get(i), accounts.get(i + 1));

            }
        }

    }
}
