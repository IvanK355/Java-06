package entities;

import service.UnknownAccountException;

import javax.persistence.*;

@Entity
@Table(name = "Account")
public class Account {

    @Id
    @Column(unique = true)
    private long id;
    private String name;
    private int accountAmount;

    public Account() {

    }
    public Account(long id, String name, int accountAmount) {
        this.id = id;
        this.name = name;
        this.accountAmount = accountAmount;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccountAmount() {
        return accountAmount;
    }

    public void setAccountAmount(int accountAmount) {
        this.accountAmount = accountAmount;
    }

    public String toString() {
        return id + " : " + name + " : "
                + " Сумма на счете: " + accountAmount;
    }

}
