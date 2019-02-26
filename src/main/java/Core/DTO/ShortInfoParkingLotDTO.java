package Core.DTO;

import Core.Entity.Account;

import java.util.Date;

/**
 * Information Parking Lot DTO
 *
 * Author: DangNHH - 27/02/2019
 */
public class ShortInfoParkingLotDTO {
    private Integer parkingLotId;
    private String displayName;
    private String ownedBy;
    private Integer totalSlot;
    private String address;
    private String phoneNumber;
    private String timeOfOperation;

    public ShortInfoParkingLotDTO() {
    }

    public ShortInfoParkingLotDTO(Integer parkingLotId, String displayName, String ownedBy,
                                  Integer totalSlot, String address, String phoneNumber, String timeOfOperation) {
        this.parkingLotId = parkingLotId;
        this.displayName = displayName;
        this.ownedBy = ownedBy;
        this.totalSlot = totalSlot;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.timeOfOperation = timeOfOperation;
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
}
