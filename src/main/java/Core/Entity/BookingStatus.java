package Core.Entity;

import javax.persistence.*;

@Entity
@Table(name = "bookingstatus")
public class BookingStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_status_id")
    private Integer bookingStatusId;

    @Column(name = "booking_status_name")
    private String bookingStatusName;

    public BookingStatus() {
    }

    public BookingStatus(String bookingStatusName) {
        this.bookingStatusName = bookingStatusName;
    }

    public Integer getBookingStatusId() {
        return bookingStatusId;
    }

    public void setBookingStatusId(Integer bookingStatusId) {
        this.bookingStatusId = bookingStatusId;
    }

    public String getBookingStatusName() {
        return bookingStatusName;
    }

    public void setBookingStatusName(String bookingStatusName) {
        this.bookingStatusName = bookingStatusName;
    }
}
