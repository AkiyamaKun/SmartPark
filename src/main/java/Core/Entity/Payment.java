package Core.Entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Integer paymentId;

    /**
     * Time of create transaction
     */
    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "type_of_money")
    private String typeOfMoney;

    @OneToOne(targetEntity=Booking.class, cascade=CascadeType.ALL)
    @JoinColumn(name = "booking_id")
    private Booking bookingId;

    @Column(name = "payment_status")
    private String paymentStatus;

    public Payment() {
    }

    public Payment(Date createdDate, String typeOfMoney, Booking bookingId, String paymentStatus) {
        this.createdDate = createdDate;
        this.typeOfMoney = typeOfMoney;
        this.bookingId = bookingId;
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

    public Booking getBookingId() {
        return bookingId;
    }

    public void setBookingId(Booking bookingId) {
        this.bookingId = bookingId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
