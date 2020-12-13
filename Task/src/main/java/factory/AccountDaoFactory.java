package factory;

import dao.Dao;
import dao.HibernateAccountDao;
import dao.JsonAccountDao;
import entities.Account;

/** метод получения данных о типе DAO*/
public class AccountDaoFactory implements DaoFactory<Account>{
    @Override
    public Dao<Account> getDao(DbType type) {
        return switch (type) {
            case HIBERNATE -> new HibernateAccountDao();
            case JSON -> new JsonAccountDao();
            default -> throw new IllegalArgumentException("Wrong DB type:" + type);
        };
    }
}
