package Core.Entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Booking Entity
 *
 */
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Integer bookingId;

    /**
     * Account Driver had booked
     */
    @OneToOne(targetEntity=Account.class,cascade=CascadeType.ALL)
    @JoinColumn(name="account_id", referencedColumnName="account_id")
    private Account account;

    /**
     * Parking Lot which driver had booked
     */
    @OneToOne(targetEntity=ParkingLot.class,cascade=CascadeType.ALL)
    @JoinColumn(name="parking_lot_id",referencedColumnName="parking_lot_id")
    private ParkingLot parkingLot;

    /**
     * Time of booking
     */
    @Column(name = "booking_time")
    private Date bookingTime;

    /**
     * Time of driver go in parking lot and scan QRCode
     */
    @Column(name = "time_start")
    private Date timeStart;

    /**
     * Time of driver go out parking lot and scan QRCode
     */
    @Column(name = "time_end")
    private Date timeEnd;

    /**
     * Token API for driver when they go in parking lot
     */
    @Column(name = "token_input")
    private String tokenInput;

    /**
     * Token API for driver when they go out parking lot
     */
    @Column(name = "token_output")
    private String tokenOutput;

    /**
     * Booking Status
     * None: Default status when booking record had created
     * Booked: Booking status when driver go in and waiting them go out
     * Finish: Booking status when driver payment successful.
     */
    @Column(name = "booking_status")
    private String bookingStatus;

    public Booking() {
    }

    public Booking(Account account, ParkingLot parkingLot, Date bookingTime) {
        this.account = account;
        this.parkingLot = parkingLot;
        this.bookingTime = bookingTime;
    }

    public Booking(Account account, ParkingLot parkingLot, Date bookingTime,
                   Date timeStart, Date timeEnd, String tokenInput,
                   String tokenOutput, String bookingStatus) {
        this.account = account;
        this.parkingLot = parkingLot;
        this.bookingTime = bookingTime;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.tokenInput = tokenInput;
        this.tokenOutput = tokenOutput;
        this.bookingStatus = bookingStatus;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Date getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(Date bookingTime) {
        this.bookingTime = bookingTime;
    }

    public Date getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getTokenInput() {
        return tokenInput;
    }

    public void setTokenInput(String tokenInput) {
        this.tokenInput = tokenInput;
    }

    public String getTokenOutput() {
        return tokenOutput;
    }

    public void setTokenOutput(String tokenOutput) {
        this.tokenOutput = tokenOutput;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }
}
