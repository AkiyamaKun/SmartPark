package Core.Entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Integer transactionId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="account_id", referencedColumnName="account_id")
    private Account accountId;

    /**
     * Time of create transaction
     */
    @Column(name = "recharge_date")
    private Date rechargeDate;

    @Column(name = "money")
    private Integer money;

    @OneToOne(targetEntity=Booking.class,cascade=CascadeType.ALL)
    @JoinColumn(name="booking_id",referencedColumnName="booking_id")
    private Booking bookingId;

    @Column(name = "type_of_transaction")
    private String typeOfTransaction;

    @Column(name = "transaction_status")
    private String transactionStatus;

    public Transaction() {
    }

    public Transaction(Account accountId, Date rechargeDate, Integer money, Booking bookingId, String typeOfTransaction, String transactionStatus) {
        this.accountId = accountId;
        this.rechargeDate = rechargeDate;
        this.money = money;
        this.bookingId = bookingId;
        this.typeOfTransaction = typeOfTransaction;
        this.transactionStatus = transactionStatus;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Account getAccountId() {
        return accountId;
    }

    public void setAccountId(Account accountId) {
        this.accountId = accountId;
    }

    public Date getRechargeDate() {
        return rechargeDate;
    }

    public void setRechargeDate(Date rechargeDate) {
        this.rechargeDate = rechargeDate;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public Booking getBookingId() {
        return bookingId;
    }

    public void setBookingId(Booking bookingId) {
        this.bookingId = bookingId;
    }

    public String getTypeOfTransaction() {
        return typeOfTransaction;
    }

    public void setTypeOfTransaction(String typeOfTransaction) {
        this.typeOfTransaction = typeOfTransaction;
    }
}
