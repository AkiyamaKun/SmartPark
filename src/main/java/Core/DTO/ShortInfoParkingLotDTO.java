package Core.DTO;

import Core.Entity.Account;
import Core.Entity.Owner;

import java.util.Date;

/**
 * Information Parking Lot DTO
 *
 * Author: DangNHH - 27/02/2019
 */
public class ShortInfoParkingLotDTO {
    private Integer parkingLotId;
    private String displayName;
    private Owner owner;
    private Integer totalSlot;
    private String address;
    private String phoneNumber;
    private String timeOfOperation;
    private float latitude;
    private float longitude;
    private boolean isActive;

    public ShortInfoParkingLotDTO() {
    }

    public ShortInfoParkingLotDTO(Integer parkingLotId, String displayName, Owner owner,
                                  Integer totalSlot, String address, String phoneNumber,
                                  String timeOfOperation, float latitude, float longitude, boolean isActive) {
        this.parkingLotId = parkingLotId;
        this.displayName = displayName;
        this.owner = owner;
        this.totalSlot = totalSlot;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.timeOfOperation = timeOfOperation;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isActive = isActive;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
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

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}
