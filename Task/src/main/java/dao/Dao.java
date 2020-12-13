package dao;

import entities.Account;
import service.NotEnoughMoneyException;
import service.UnknownAccountException;

import java.io.IOException;
import java.sql.SQLException;

public interface Dao<T> {


    Account create(T account) throws IOException, SQLException, UnknownAccountException;

    Account read(long id) throws IOException, UnknownAccountException, SQLException;

    Account update(T account) throws SQLException, IOException, NotEnoughMoneyException, UnknownAccountException;

    Account delete(T account) throws SQLException, IOException, NotEnoughMoneyException, UnknownAccountException;

    void createNewTable() throws SQLException, IOException;

}
