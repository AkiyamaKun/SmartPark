package Core.Entity;

import javax.persistence.*;

@Entity
@Table(name = "camera")
public class Camera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "camera_id")
    private Integer cameraId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="parking_lot_id", referencedColumnName="parking_lot_id")
    private ParkingLot parkingLotId;

    @Column(name = "camera_name")
    private String cameraName;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "is_active",columnDefinition = "TINYINT(1)")
    private boolean isActive;

    public Camera() {
    }

    public Camera(ParkingLot parkingLotId, String cameraName, String ipAddress, boolean isActive) {
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
