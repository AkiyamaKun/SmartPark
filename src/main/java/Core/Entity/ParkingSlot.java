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
    private String name;
    /**
     * Free: Slot blank
     * Waiting: Slot had booked
     * Busy: Slot is used
     * Closing: Slot can not used
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

    /**
     * Constructor full arguments
     */
    public ParkingSlot(String name, ParkingSlotStatus parkingSlotStatus, ParkingLot parkingLot) {
        this.name = name;
        this.parkingSlotStatus = parkingSlotStatus;
        this.parkingLot = parkingLot;
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
