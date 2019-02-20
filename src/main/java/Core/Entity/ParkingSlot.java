package Core.Entity;

import javax.persistence.*;

/**
 * Parking Slot Entity
 *
 * Author: DangNHH - 19/02/2019
 */
@Entity
@Table(name = "ParkingSlot")
public class ParkingSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer slotId;
    /**
     * Name of slot: A1, A2, A3
     */
    private String name;
    /**
     * Free: Slot blank
     * Waiting: Slot had booked
     * Busy: Slot is used
     * Closing: Slot can not used
     */
    private Integer statusId;
    /**
     * Id of Parking Lot
     */
    private Integer parkingLotId;

    public ParkingSlot() {
    }

    /**
     * Constructor full arguments
     */
    public ParkingSlot(String name, Integer statusId, Integer parkingLotId) {
        this.name = name;
        this.statusId = statusId;
        this.parkingLotId = parkingLotId;
    }

    public Integer getSlotId() {
        return slotId;
    }

    public void setSlotId(Integer slotId) {
        this.slotId = slotId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(Integer parkingLotId) {
        this.parkingLotId = parkingLotId;
    }
}
