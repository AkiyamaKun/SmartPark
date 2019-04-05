package Core.Entity;

import javax.persistence.*;

/**
 * Parking Slot Status Entity
 *
 * Author: DangNHH - 20/02/2019
 */
@Entity
@Table(name = "parkingslotstatus")
public class ParkingSlotStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    private Integer statusId;

    @Column(name = "status_name")
    private String statusName;

    public ParkingSlotStatus() {
    }

    public ParkingSlotStatus(String statusName) {
        this.statusName = statusName;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }


    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
