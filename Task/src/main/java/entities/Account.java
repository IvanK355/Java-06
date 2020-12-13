package entities;

import javax.persistence.*;

@Entity
@Table(name = "Account")
public class Account {

    @Id
    private int id;
    private String name;
    private int accountAmount;

    public Account(String name, int accountAmount) {
        this.name = name;
        this.accountAmount = accountAmount;
    }

    public int getId() {
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

    public Account(int id, String name, int accountAmount) {
        this.id = id;
        this.name = name;
        this.accountAmount = accountAmount;
    }

    public String toString() {
        return id + " : " + name + " : "
                + " Сумма на счете: " + accountAmount;
    }

}
