package service;

import entities.Account;
import java.io.IOException;
import java.sql.SQLException;

public interface BankService {
    void createNewTable() throws SQLException, IOException;

    Account openNewAccount(Account account) throws SQLException, IOException, UnknownAccountException;
    Account closeAccount(long id) throws SQLException, IOException, UnknownAccountException, NotEnoughMoneyException;

    Account balance(long id) throws SQLException, IOException, UnknownAccountException;

    Account deposit(long id, int amount) throws SQLException, IOException, UnknownAccountException, NotEnoughMoneyException;

    Account withdraw(long id, int amount) throws SQLException, IOException, NotEnoughMoneyException, UnknownAccountException;

    void transfer(long id1, long id2, int amount) throws IOException, NotEnoughMoneyException, UnknownAccountException, SQLException;
}
