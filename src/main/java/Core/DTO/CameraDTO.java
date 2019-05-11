package Core.DTO;

import Core.Entity.ParkingLot;

public class CameraDTO {

    private Integer cameraId;

    private ParkingLot parkingLotId;

    private String cameraName;

    private String ipAddress;

    private boolean isActive;

    public CameraDTO() {
    }

    public CameraDTO(Integer cameraId, ParkingLot parkingLotId, String cameraName, String ipAddress, boolean isActive) {
        this.cameraId = cameraId;
        this.parkingLotId = parkingLotId;
        this.cameraName = cameraName;
        this.ipAddress = ipAddress;
        this.isActive = isActive;
    }

    public Integer getCameraId() {
        return cameraId;
    }

    public void setCameraId(Integer cameraId) {
        this.cameraId = cameraId;
    }

    public ParkingLot getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(ParkingLot parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public String getCameraName() {
        return cameraName;
    }

    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
