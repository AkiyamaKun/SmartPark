package Core.DTO;

import java.util.Date;

public class BookingDTO {
    private Integer bookingId;
    private Integer accountId;
    private Integer parkingLotId;
    private Date bookingTime;
    private Date timeStart;
    private Date timeEnd;
    private String tokenInput;
    private String tokenOutput;
    /**
     * Booking Status
     * None: Default status when booking record had created
     * Booked: Booking status when driver go in and waiting them go out
     * Finish: Booking status when driver payment successful.
     */
    private String bookingStatus;
    private String urlAPICheckIn;
    private String urlAPICheckOut;
    private String parkingLotName;
    private float price;

    public BookingDTO() {
    }

    public BookingDTO(Integer bookingId, Integer accountId, Integer parkingLotId,
                      Date bookingTime, String tokenInput) {
        this.bookingId = bookingId;
        this.accountId = accountId;
        this.parkingLotId = parkingLotId;
        this.bookingTime = bookingTime;
        this.tokenInput = tokenInput;
    }

    public BookingDTO(Integer bookingId, Integer accountId, Integer parkingLotId,
                      Date bookingTime, Date timeStart, Date timeEnd, String tokenInput,
                      String tokenOutput, String bookingStatus, String urlAPICheckIn, String urlAPICheckOut,
                      String parkingLotName, float price) {
        this.bookingId = bookingId;
        this.accountId = accountId;
        this.parkingLotId = parkingLotId;
        this.bookingTime = bookingTime;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.tokenInput = tokenInput;
        this.tokenOutput = tokenOutput;
        this.bookingStatus = bookingStatus;
        this.urlAPICheckIn = urlAPICheckIn;
        this.urlAPICheckOut = urlAPICheckOut;
        this.parkingLotName = parkingLotName;
        this.price = price;
    }


    public String getParkingLotName() {
        return parkingLotName;
    }

    public void setParkingLotName(String parkingLotName) {
        this.parkingLotName = parkingLotName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(Integer parkingLotId) {
        this.parkingLotId = parkingLotId;
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

    public String getUrlAPICheckIn() {
        return urlAPICheckIn;
    }

    public void setUrlAPICheckIn(String urlAPICheckIn) {
        this.urlAPICheckIn = urlAPICheckIn;
    }

    public String getUrlAPICheckOut() {
        return urlAPICheckOut;
    }

    public void setUrlAPICheckOut(String urlAPICheckOut) {
        this.urlAPICheckOut = urlAPICheckOut;
    }
}
