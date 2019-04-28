package Core.DTO;

import java.util.Date;

public class CheckOutDTO {
    private Integer bookingId;
    private String email;
    private String parkingLotName;
    private float price;
    private Date bookingTime;
    private Date timeStart;
    private Date timeEnd;
    private long timeUseBySecond;
    private Integer moneyToPay;
    private String plateNumber;

    public CheckOutDTO() {
    }

    public CheckOutDTO(Integer bookingId, String email, String parkingLotName, float price, Date bookingTime,
                       Date timeStart, Date timeEnd, long timeUseBySecond, Integer moneyToPay, String plateNumber) {
        this.bookingId = bookingId;
        this.email = email;
        this.parkingLotName = parkingLotName;
        this.price = price;
        this.bookingTime = bookingTime;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.timeUseBySecond = timeUseBySecond;
        this.moneyToPay = moneyToPay;
        this.plateNumber = plateNumber;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public long getTimeUseBySecond() {
        return timeUseBySecond;
    }

    public void setTimeUseBySecond(long timeUseBySecond) {
        this.timeUseBySecond = timeUseBySecond;
    }

    public Integer getMoneyToPay() {
        return moneyToPay;
    }

    public void setMoneyToPay(Integer moneyToPay) {
        this.moneyToPay = moneyToPay;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }
}
