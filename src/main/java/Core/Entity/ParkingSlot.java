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
    @Column(name = "slotId")
    private Integer slotId;
    /**
     * Name of slot: A1, A2, A3
     */
    @Column(name = "lane", columnDefinition = "NVARCHAR(50)")
    private String lane;

    @Column(name = "row", columnDefinition = "NVARCHAR(50)")
    private String row;
    /**
     * Free: Slot blank
     * Busy: Slot is used
     * Undefined: Slot can not determine by webcame
     */
    @OneToOne(targetEntity=ParkingSlotStatus.class,cascade=CascadeType.ALL)
    @JoinColumn(name="statusId",referencedColumnName="statusId")
    private ParkingSlotStatus parkingSlotStatus;
    /**
     * Id of Parking Lot
     */
    @OneToOne(targetEntity=ParkingLot.class,cascade=CascadeType.ALL)
    @JoinColumn(name="parkingLotId",referencedColumnName="parkingLotId")
    private ParkingLot parkingLot;

    public ParkingSlot() {
    }

    public ParkingSlot(String lane, String row, ParkingSlotStatus parkingSlotStatus, ParkingLot parkingLot) {
        this.lane = lane;
        this.row = row;
        this.parkingSlotStatus = parkingSlotStatus;
        this.parkingLot = parkingLot;
    }

    public Integer getSlotId() {
        return slotId;
    }

    public void setSlotId(Integer slotId) {
        this.slotId = slotId;
    }

    public String getLane() {
        return lane;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
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
