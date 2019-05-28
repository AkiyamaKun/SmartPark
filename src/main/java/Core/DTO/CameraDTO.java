package Core.DTO;

import Core.Entity.ParkingLot;

public class CameraDTO {

    private Integer cameraId;

    private Integer parkingLotId;

    private String cameraName;

    private String ipAddress;

    private boolean isActive;

    private String urlLiveStream;

    public CameraDTO() {
    }

    public CameraDTO(Integer cameraId, Integer parkingLotId, String cameraName, String ipAddress, boolean isActive, String urlLiveStream) {
        this.cameraId = cameraId;
        this.parkingLotId = parkingLotId;
        this.cameraName = cameraName;
        this.ipAddress = ipAddress;
        this.isActive = isActive;
        this.urlLiveStream = urlLiveStream;
    }

    public Integer getCameraId() {
        return cameraId;
    }

    public void setCameraId(Integer cameraId) {
        this.cameraId = cameraId;
    }

    public Integer getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(Integer parkingLotId) {
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

    public String getUrlLiveStream() {
        return urlLiveStream;
    }

    public void setUrlLiveStream(String urlLiveStream) {
        this.urlLiveStream = urlLiveStream;
    }
}
