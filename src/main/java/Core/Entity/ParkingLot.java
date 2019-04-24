package Core.Entity;

import Core.Constant.Const;
import Core.Repository.ParkingSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ParkingLot Entity
 *
 * Author: DangNHH - 19/02/2019
 */
@Entity
@Table(name = "parkinglot")
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parking_lot_id")
    private Integer parkingLotId;
    /**
     * Name of ParkingLot
     */
    @Column(name = "display_name")
    private String displayName;

    /**
     * Id of Admin Account
     */
    @OneToOne(targetEntity=Account.class,cascade=CascadeType.ALL)
    @JoinColumn(name="created_by")
    private Account createdBy;

    //Id of Supervisor/Admin Account
    @OneToOne(targetEntity=Account.class,cascade=CascadeType.ALL)
    @JoinColumn(name = "edited_by")
    private Account editedBy;

    @Column(name = "longitude")
    private float longitude;

    @Column(name = "latitude")
    private float latitude;

    @Column(name = "total_slot")
    private Integer totalSlot;

    @Column(name = "booking_slot")
    private Integer bookingSlot;

    @Column(name = "address")
    private String address;
    /**
     * Phone number of owner ParkingLot
     */
    @Column(name = "phone_number")
    private String phoneNumber;
    /**
     * Time ParkingLot open-close
     */
    @Column(name = "time_of_operation")
    private String timeOfOperation;
    /**
     * Active: 1, Deactive: 0
     */
    @Column(name = "is_active",columnDefinition = "TINYINT(1)")
    private boolean isActive;

    /**
     * Late Edit Date
     */
    @Column(name = "last_edited")
    private Date lastEdited;

    /**
     * Create Date
     */
    @Column(name = "created_date")
    private Date createdDate;

    /**
     * Parking Lot Image
     */
    @Column(name = "parklot_image", columnDefinition = "MEDIUMBLOB")
    private byte[] parklotImage;

    /**
     * Owner of Parking Lot (Unique)
     */
    @OneToOne(targetEntity=Owner.class, cascade=CascadeType.ALL)
    @JoinColumn(name="owner_id", referencedColumnName="owner_id")
    private Owner owner;

    @Column(name = "price")
    private float price;

    public ParkingLot() {
    }

    public ParkingLot(String displayName, Account createdBy, Account editedBy, float longitude,
                      float latitude, Integer totalSlot, String address, String phoneNumber,
                      String timeOfOperation, boolean isActive, Date lastEdited, Date createdDate,
                      Owner owner, byte[] parklotImage, float price, Integer bookingSlot) {
        this.displayName = displayName;
        this.createdBy = createdBy;
        this.editedBy = editedBy;
        this.longitude = longitude;
        this.latitude = latitude;
        this.totalSlot = totalSlot;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.timeOfOperation = timeOfOperation;
        this.isActive = isActive;
        this.lastEdited = lastEdited;
        this.createdDate = createdDate;
        this.owner = owner;
        this.parklotImage = parklotImage;
        this.price = price;
        this.bookingSlot = bookingSlot;
    }

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

    public Account getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Account createdBy) {
        this.createdBy = createdBy;
    }

    public Account getEditedBy() {
        return editedBy;
    }

    public void setEditedBy(Account editedBy) {
        this.editedBy = editedBy;
    }

    public Date getLastEdited() {
        return lastEdited;
    }

    public void setLastEdited(Date lastEdited) {
        this.lastEdited = lastEdited;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Integer getBookingSlot() {
        return bookingSlot;
    }

    public void setBookingSlot(Integer bookingSlot) {
        this.bookingSlot = bookingSlot;
    }

}
