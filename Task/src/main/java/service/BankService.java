package service;

import entities.Account;
import java.io.IOException;
import java.sql.SQLException;

public interface BankService {
    void createNewTable() throws IOException, SQLException, IOException;

    Account openNewAccount(Account account) throws SQLException, IOException, UnknownAccountException;
    Account closeAccount(int id) throws SQLException, IOException, UnknownAccountException, NotEnoughMoneyException;

    Account balance(int id) throws SQLException, IOException, UnknownAccountException;

    Account deposit(int id, int amount) throws SQLException, IOException, UnknownAccountException, NotEnoughMoneyException;

    Account withdraw(int id, int amount) throws SQLException, IOException, NotEnoughMoneyException, UnknownAccountException;

    void transfer(int id1, int id2, int amount) throws SQLException, IOException, NotEnoughMoneyException, UnknownAccountException, SQLException;
}
