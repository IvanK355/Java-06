package service.impl;

import dao.Dao;
import entities.Account;
import service.BankService;
import service.NotEnoughMoneyException;
import service.UnknownAccountException;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Сервис слой - имплементация запросов Presentation слоя
 */
public class BankServiceImpl implements BankService {

    private Dao<Account> dao;

    /**
     * В конструкторе - выбранный тип DAO
     */
    public BankServiceImpl(Dao<Account> dao) {
        this.dao = dao;
    }

    @Override
    public void createNewTable() throws SQLException, IOException {
        dao.createNewTable();
    }

    @Override
    public Account openNewAccount(Account account) throws SQLException, IOException, UnknownAccountException {
        dao.create(account);
        System.out.println("Account " + account.getId() + " open: ");
        return balance(account.getId());
    }

    @Override
    public Account closeAccount(long id) throws SQLException, IOException, UnknownAccountException, NotEnoughMoneyException {
        Account account = dao.read(id);
        dao.delete(account);
        System.out.println("Account " + id + " close: ");
        return null;
    }

    @Override
    public Account balance(long id) throws UnknownAccountException, IOException, SQLException {
        try {
            Account account = dao.read(id);
            if (account == null) {
                throw new UnknownAccountException("Неверный счет " + id);
            }
        } catch (UnknownAccountException | IOException | SQLException e) {
            e.printStackTrace();
        }
        return dao.read(id);
    }

    @Override
    public Account deposit(long id, int amount) throws SQLException, IOException, UnknownAccountException, NotEnoughMoneyException {

        Account account = balance(id);
        int newAmount = account.getAccountAmount() + amount;
        account.setAccountAmount(newAmount);
        return dao.update(account);
    }

    @Override
    public Account withdraw(long id, int amount) throws UnknownAccountException, IOException, SQLException {
        try {
            Account account = balance(id);
            int newAmount = account.getAccountAmount() - amount;

            if (newAmount < 0) {
                throw new NotEnoughMoneyException("Недостаточно средств");
            }
            account.setAccountAmount(newAmount);
            return dao.update(account);
        } catch (NotEnoughMoneyException e) {
            e.printStackTrace();
        } catch (UnknownAccountException e) {
            throw new UnknownAccountException("Счет " + id + " неверный");
        }
        return dao.read(id);
    }

    @Override
    public void transfer(long id1, long id2, int amount) throws NotEnoughMoneyException {
        try {
            Account account1 = dao.read(id1);
            Account account2 = dao.read(id2);
            if (account1.getName() == null) {
                throw new UnknownAccountException("Неверный счет " + id1);
            } else if (account2.getName() == null) {
                throw new UnknownAccountException("Неверный счет " + id2);
            }
            withdraw(id1, amount);
            deposit(id2, amount);
        } catch (SQLException | UnknownAccountException | IOException e) {
            e.printStackTrace();
        }
    }
}
