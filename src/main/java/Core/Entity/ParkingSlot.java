package Core.Entity;

import javax.persistence.*;

/**
 * Parking Slot Entity
 *
 * Author: DangNHH - 19/02/2019
 */
@Entity
@Table(name = "parkingslot")
public class ParkingSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "slot_id")
    private Integer slotId;
    /**
     * Name of slot: A1, A2, A3
     */
    @Column(name = "slot_lane")
    private String slotLane;

    @Column(name = "slot_row")
    private String slotRow;
    /**
     * Free: Slot blank
     * Busy: Slot is used
     * Undefined: Slot can not determine by webcame
     */
    @OneToOne(targetEntity=ParkingSlotStatus.class,cascade=CascadeType.ALL)
    @JoinColumn(name="status_id",referencedColumnName="status_id")
    private ParkingSlotStatus parkingSlotStatus;
    /**
     * Id of Parking Lot
     */
    @OneToOne(targetEntity=ParkingLot.class,cascade=CascadeType.ALL)
    @JoinColumn(name="parking_lot_id",referencedColumnName="parking_lot_id")
    private ParkingLot parkingLot;

    public ParkingSlot() {
    }

    public ParkingSlot(String slotLane, String slotRow, ParkingSlotStatus parkingSlotStatus, ParkingLot parkingLot) {
        this.slotLane = slotLane;
        this.slotRow = slotRow;
        this.parkingSlotStatus = parkingSlotStatus;
        this.parkingLot = parkingLot;
    }

    public String getSlotLane() {
        return slotLane;
    }

    public void setSlotLane(String slotLane) {
        this.slotLane = slotLane;
    }

    public String getSlotRow() {
        return slotRow;
    }

    public void setSlotRow(String slotRow) {
        this.slotRow = slotRow;
    }

    public Integer getSlotId() {
        return slotId;
    }

    public void setSlotId(Integer slotId) {
        this.slotId = slotId;
    }


    public ParkingSlotStatus getParkingSlotStatus() {
        return parkingSlotStatus;
    }

    public void setParkingSlotStatus(ParkingSlotStatus parkingSlotStatus) {
        this.parkingSlotStatus = parkingSlotStatus;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }
}
