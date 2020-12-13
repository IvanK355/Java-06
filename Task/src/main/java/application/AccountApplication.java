package application;

import dao.Dao;
import entities.Account;
import factory.AccountDaoFactory;
import factory.DaoFactory;
import factory.DbType;
import service.BankService;
import service.NotEnoughMoneyException;
import service.UnknownAccountException;
import service.impl.BankServiceImpl;

import java.io.IOException;
import java.sql.SQLException;

public class AccountApplication {

    public static void main(String[] args) throws IOException, SQLException, UnknownAccountException, NotEnoughMoneyException {

        DaoFactory <Account> factory = new AccountDaoFactory();
        Dao <Account> dao = factory.getDao(DbType.HIBERNATE);
        BankService bankService = new BankServiceImpl(dao);
        bankService.createNewTable();
        Account account1 = bankService.balance(1);
        Account account2 = bankService.deposit(1, 100);
        Account account3 = bankService.withdraw(1, 100);
        bankService.transfer(1, 2, 100);
        Account account4 = bankService.balance(1);
        Account account5 = bankService.balance(2);
        System.out.println(account1);
        System.out.println(account2);
        System.out.println(account3);
        System.out.println(account4);
        System.out.println(account5);
        Account account6 = bankService.openNewAccount(new Account(11, "holder11", 600));
        System.out.println(account6);
        Account account7 = bankService.closeAccount(11);
        System.out.println(account7);
    }
}
