package factory;

import dao.Dao;

public interface DaoFactory<T>{
    Dao<T> getDao(DbType type);
}
