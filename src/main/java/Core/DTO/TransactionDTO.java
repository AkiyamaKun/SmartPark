package Core.DTO;

import Core.Entity.Account;

import java.util.Date;

public class TransactionDTO {

    private Integer transactionId;

    private Integer accountId;

    private Date rechargeDate;

    private Integer money;

    private String transactionStatus;

    public TransactionDTO() {
    }

    public TransactionDTO(Integer transactionId, Integer accountId, Date rechargeDate, Integer money, String transactionStatus) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.rechargeDate = rechargeDate;
        this.money = money;
        this.transactionStatus = transactionStatus;
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

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
}
