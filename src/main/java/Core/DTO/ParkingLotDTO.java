package Core.DTO;

import java.util.Date;

/**
 * Account DTO
 *
 * Author: DangNHH - 19/02/2019
 */
public class ParkingLotDTO {

    private Integer parkingLotId;
    private String displayName;
    private String ownedBy;
    private String longitude;
    private String latitude;
    private Integer totalSlot;
    private String address;
    private String phoneNumber;
    private String timeOfOperation;
    private boolean isActive;
    private Integer createdBy;
    private Date createDate;
    private Integer editedBy;
    private Date lastEdited;

    public ParkingLotDTO() {
    }

    /**
     * Constructor full arguments
     */
    public ParkingLotDTO(Integer parkingLotId, String displayName, String ownedBy, String longitude,
                         String latitude, Integer totalSlot, String address, String phoneNumber,
                         String timeOfOperation, boolean isActive, Integer createdBy,
                         Date createDate, Integer editedBy, Date lastEdited) {
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

    public String getOwnedBy() {
        return ownedBy;
    }

    public void setOwnedBy(String ownedBy) {
        this.ownedBy = ownedBy;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
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
