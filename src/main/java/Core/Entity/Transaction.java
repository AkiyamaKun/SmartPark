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
    private float money;

    @OneToOne(targetEntity=Booking.class,cascade=CascadeType.ALL)
    @JoinColumn(name="booking_id",referencedColumnName="booking_id")
    private Booking bookingId;

    @Column(name = "type_of_transaction")
    private String typeOfTransaction;

    @Column(name = "card_type")
    private String cardType;

    @Column(name = "card_id")
    private String cardId;

    @Column(name = "transaction_code")
    private String transactionCode;

    public Transaction() {
    }

    public Transaction(Account accountId, Date rechargeDate, float money, Booking bookingId, String typeOfTransaction, String cardType, String cardId, String transactionCode) {
        this.accountId = accountId;
        this.rechargeDate = rechargeDate;
        this.money = money;
        this.bookingId = bookingId;
        this.typeOfTransaction = typeOfTransaction;
        this.cardType = cardType;
        this.cardId = cardId;
        this.transactionCode = transactionCode;
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

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
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

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }
}
