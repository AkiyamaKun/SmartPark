package Core.Entity;

import javax.persistence.*;

/**
 * Parking Slot Status Entity
 *
 * Author: DangNHH - 20/02/2019
 */
@Entity
@Table(name = "ParkingSlotStatus")
public class ParkingSlotStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer statusId;
    private String status;

    public ParkingSlotStatus() {
    }

    public ParkingSlotStatus(String status) {
        this.status = status;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
