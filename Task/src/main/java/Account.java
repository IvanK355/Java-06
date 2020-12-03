import javax.persistence.*;

@Entity
@Table(name = "Account")


public class Account {


    @Id
    @GeneratedValue

    private int id;
    private String holder;
    private int beginSum;
    private int amountOperation;
    private int endSum;
    private String operation;

    public String getOperation() {

        return operation;
    }

    public void setOperation(String operation) {

        this.operation = operation;
    }

    public int getBeginSum() {

        return beginSum;
    }

    public void setBeginSum(int beginSum) {

        this.beginSum = beginSum;
    }

    public int getAmountOperation() {

        return amountOperation;
    }

    public void setAmountOperation(int amountOperation) {

        this.amountOperation = amountOperation;
    }

    public int getEndSum() {

        return endSum;
    }

    public void setEndSum(int endSum) {

        this.endSum = endSum;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getHolder() {

        return holder;
    }

    public void setHolder(String holder) {

        this.holder = holder;
    }
}


