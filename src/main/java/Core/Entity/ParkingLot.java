package Core.Entity;

import javax.persistence.*;
import java.util.Date;

/**
 * ParkingLot Entity
 *
 * Author: DangNHH - 19/02/2019
 */
@Entity
@Table(name = "ParkingLot")
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer parkingLotId;
    /**
     * Name of ParkingLot
     */
    private String displayName;
    /**
     * Name of owner ParkingLot
     */
    private Integer ownedBy;
    private float longitude;
    private float latitude;
    private Integer totalSlot;
    private String address;
    /**
     * Phone number of owner ParkingLot
     */
    private String phoneNumber;
    /**
     * Time ParkingLot open-close
     */
    private String timeOfOperation;
    /**
     * Active: 1, Deactive: 0
     */
    private boolean isActive;
    /**
     * Id of Admin Account
     */
    private Integer createdBy;
    private Date createDate;
    /**
     * Name of ParkingLot
     */
    //Id of Supervisor/Admin Account
    private Integer editedBy;
    private Date lastEdited;

    public ParkingLot() {
    }

    /**
     * Constructor full arguments
     */
    public ParkingLot(String displayName, Integer ownedBy, float longitude, float latitude,
                      Integer totalSlot, String address, String phoneNumber, String timeOfOperation,
                      boolean isActive, Integer createdBy, Date createDate, Integer editedBy, Date lastEdited) {
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
        this.createDate = createDate;
        this.editedBy = editedBy;
        this.lastEdited = lastEdited;
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

    public Integer getOwnedBy() {
        return ownedBy;
    }

    public void setOwnedBy(Integer ownedBy) {
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
}
