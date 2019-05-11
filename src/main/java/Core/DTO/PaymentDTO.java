package Core.DTO;

import Core.Entity.Booking;

import java.util.Date;

public class PaymentDTO {

    private Integer paymentId;

    private Date createdDate;

    private String typeOfMoney;

    private Booking booking;

    private String paymentStatus;

    public PaymentDTO() {
    }

    public PaymentDTO(Integer paymentId, Date createdDate, String typeOfMoney, Booking booking, String paymentStatus) {
        this.paymentId = paymentId;
        this.createdDate = createdDate;
        this.typeOfMoney = typeOfMoney;
        this.booking = booking;
        this.paymentStatus = paymentStatus;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getTypeOfMoney() {
        return typeOfMoney;
    }

    public void setTypeOfMoney(String typeOfMoney) {
        this.typeOfMoney = typeOfMoney;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
