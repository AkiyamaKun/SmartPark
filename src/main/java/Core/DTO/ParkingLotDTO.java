package Core.DTO;

import Core.Constant.Const;
import Core.Entity.Account;
import Core.Entity.Owner;
import Core.Entity.ParkingLot;
import Core.Entity.ParkingSlot;
import Core.Repository.ParkingSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Account DTO
 *
 * Author: DangNHH - 19/02/2019
 */
public class ParkingLotDTO {
    private Integer parkingLotId;
    private String displayName;
    private Account ownedBy;
    private float longitude;
    private float latitude;
    private Integer totalSlot;
    private String address;
    private String phoneNumber;
    private String timeOfOperation;
    private boolean isActive;
    private Integer createdBy;
    private Date createdDate;
    private Integer editedBy;
    private Date lastEdited;
    private Owner owner;
    private byte[] parklotImage;
    private Integer bookingSlot;
    private float price;


    public ParkingLotDTO() {
    }

    public ParkingLotDTO(Integer parkingLotId, String displayName, Account ownedBy, float longitude, float latitude,
                         Integer totalSlot, String address, String phoneNumber, String timeOfOperation, boolean isActive,
                         Integer createdBy, Date createdDate, Integer editedBy, Date lastEdited, Owner owner, byte[] parklotImage,
                         Integer bookingSlot, float price) {
        this.parkingLotId = parkingLotId;
        this.displayName = displayName;
        this.ownedBy = ownedBy;
        this.longitude = longitude;
        this.latitude = latitude;
        this.totalSlot = totalSlot;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.timeOfOperation = timeOfOperation;
        this.isActive = isActive;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.editedBy = editedBy;
        this.lastEdited = lastEdited;
        this.owner = owner;
        this.parklotImage = parklotImage;
        this.bookingSlot = bookingSlot;
        this.price = price;
    }

    /**
     * Constructor full arguments
     */


    public Integer getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(Integer parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Account getOwnedBy() {
        return ownedBy;
    }

    public void setOwnedBy(Account ownedBy) {
        this.ownedBy = ownedBy;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public Integer getTotalSlot() {
        return totalSlot;
    }

    public void setTotalSlot(Integer totalSlot) {
        this.totalSlot = totalSlot;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTimeOfOperation() {
        return timeOfOperation;
    }

    public void setTimeOfOperation(String timeOfOperation) {
        this.timeOfOperation = timeOfOperation;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createDate) {
        this.createdDate = createDate;
    }

    public Integer getEditedBy() {
        return editedBy;
    }

    public void setEditedBy(Integer editedBy) {
        this.editedBy = editedBy;
    }

    public Date getLastEdited() {
        return lastEdited;
    }

    public void setLastEdited(Date lastEdited) {
        this.lastEdited = lastEdited;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public byte[] getParklotImage() {
        return parklotImage;
    }

    public void setParklotImage(byte[] parklotImage) {
        this.parklotImage = parklotImage;
    }

    public Integer getBookingSlot() {
        return bookingSlot;
    }

    public void setBookingSlot(Integer bookingSlot) {
        this.bookingSlot = bookingSlot;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
