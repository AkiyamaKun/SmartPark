package Core.DTO;

import Core.Entity.Account;

import java.util.Date;

public class TransactionDTO {

    private Integer transactionId;

    private Integer accountId;

    private Date rechargeDate;

    private Integer money;

    private Integer bookingId;

    private String cardType;

    private String cardId;

    private String typeOfTransaction;

    private String transactionCode;

    public TransactionDTO() {
    }

    public TransactionDTO(Integer transactionId, Integer accountId, Date rechargeDate, Integer money, Integer bookingId, String cardType, String cardId, String typeOfTransaction, String transactionCode) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.rechargeDate = rechargeDate;
        this.money = money;
        this.bookingId = bookingId;
        this.cardType = cardType;
        this.cardId = cardId;
        this.typeOfTransaction = typeOfTransaction;
        this.transactionCode = transactionCode;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
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

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
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

    public String getTypeOfTransaction() {
        return typeOfTransaction;
    }

    public void setTypeOfTransaction(String typeOfTransaction) {
        this.typeOfTransaction = typeOfTransaction;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }
}
